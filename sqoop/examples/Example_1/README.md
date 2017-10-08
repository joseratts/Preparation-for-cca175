# Sqoop

* Conexion a MySQL

  >mysql -u retail_dba -p
  >mysql> use retail_dba

* Creación Tabla de Datos a Importar a HDFS

  >mysql> CREATE TABLE widgets(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, widget_name VARCHAR(64) NOT NULL,price DECIMAL(10,2),design_date DATE,version INT,design_comment VARCHAR(100));

* Insertamos datos de prueba

  >INSERT INTO widgets VALUES (NULL, 'sprocket', 0.25, '2010-02-10',1, 'Connects two gizmos');
  >INSERT INTO widgets VALUES (NULL, 'gizmo', 4.00, '2009-11-30',4,NULL);
  >INSERT INTO widgets VALUES (NULL, 'gadget', 99.99, '1983-08-13',13, 'Our flagship product');
  >INSERT INTO widgets VALUES (NULL, 'tomtom', 50.00, '2000-08-15',1, 'My Tomtom');
  >INSERT INTO widgets VALUES (NULL, 'soporte tomtom', 19.00, '2010-01-01',2, 'Tomtom support');

* Ejecutamos importacion de datos con el comando Sqoop

  >sqoop import --table widgets -m 1 --connect jdbc:mysql://quickstart:3306/retail_db --username=retail_dba --password=cloudera

  Y esto nos debe de haber generado dos cosas:

  - HDFS:
    >hadoop fs -cat widgets/part-m-00000
    >1,sprocket,0.25,2010-02-10,1,Connects two gizmos
    >2,gizmo,4.00,2009-11-30,4,null
    >3,gadget,99.99,1983-08-13,13,Our flagship product
    >4,tomtom,50.00,2000-08-15,1,My Tomtom
    >..

  - Filesystem Local

    Un fichero java llamado igual que la tabla que hemos importado, es decir "widgets.java"

    *NOTA*: El parametro -m nos indica el número de tareas map que se llevarán a cabo, en nuestro ejemplo será 1. Lo que conlleva a que se creara un único archivo en HDFS. El string de conexión posee como host “localhost”, pero si estuviésemos en el caso de un cluster hadoop distribuido, no debemos utilizar localhost ya que las tareas map se llevan a cabo en máquinas distintas a donde este la BD darán error al conectar Incluso si sqoop se ejecuta en la misma máquina donde este el servidor de base de datos, debe especificarse el hostname completo.

    *Revisar como se haría en un Cluster*
