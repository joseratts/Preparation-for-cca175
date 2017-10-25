1.- Dada la tabla retail_db.products de mysql que viene por defecto en la VM de cloudera exportar la tabla a hdfs a la ruta /user/cloudera/products/exercise1/rawdata como ficheros de texto cuyos campos estén delimitados por “|”.

2.- Cargar los datos de la ruta en hdfs /user/cloudera/products/exercise1/rowdata y guardarlos en formato Avro particionado por el tipo de producto y comprimido en snappy en la ruta /user/cloudera/products/exercise1/avrodata/

3.- Usando DataFrame o Dataset calcular el precio promedio, el precio máximo y mínimo de los productos agrupados por tipo con la condición de que los productos a tener en cuenta deben tener un precio mayor a 50. Ordenarlos de forma descendente por el precio promedio.

4.- hacer el mismo cálculo del ejercicio 3 usando SparkSQL.

5.- exportar a MySQL el resultado del ejercicio 3 a una tabla llamada products_aggregation