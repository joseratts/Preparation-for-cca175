Dado los ficheros data1.avro y data2.avro creados utilizando avro-tools a partir de los ficheros data1.json y data2.json, crear una tabla en hive que nos permita realizar consultas sobre estos datos, teniendo en cuenta que el campo "id" presente en data1.avro no está presente en data2.avro y no será utilizado y además el campo "profesion" presente en data1.avro por alguna razón ha sido renombrado a "ocupacion" en data2.avro.

Una vez definida la tabla la cualllamaremos "personas" hallar:

1.- La media de edad de todas las personas.

2.- La media de edad de todas las personas cuya clase sea 'B'.

3.- La lista de profesiones ordenada por el número de apariciones.

4.- A partir de los ficheros JSON (data1, data2) crear los ficheros AVRO comprimidos con SNAPPY, recrear la tabla en hive tomando en cuenta la compresión utilizada, llamarla personas2 y realizar las consultas anteriores (1, 2, y 3) para corroborar que los resultados sean los mismos. 

