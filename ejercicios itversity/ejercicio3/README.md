Problem

1.- Import all tables from mysql database into hdfs as avro data files. use compression and the compression codec should be snappy. data warehouse directory should be retail_stage.db

2.- Create a metastore table that should point to the orders data imported by sqoop job above. Name the table orders_sqoop.

3.- Write query in hive that shows all orders belonging to a certain day. This day is when the most orders were placed. select data from orders_sqoop.

4.- query table in impala that shows all orders belonging to a certain day. This day is when the most orders were placed. select data from order_sqoop.

5.- Now create a table named retail.orders_avro in hive stored as avro, the table should have same table definition as order_sqoop. Additionally, this new table should be partitioned by the order month i.e -> year-order_month.(example: 2014-01)

6.- Load data into orders_avro table from orders_sqoop table.

7.- Write query in hive that shows all orders belonging to a certain day. This day is when the most orders were placed. select data from orders_avro

8.- evolve the avro schema related to orders_sqoop table by adding more fields named (order_style String, order_zone Integer)

9.- insert two more records into orders_sqoop table.