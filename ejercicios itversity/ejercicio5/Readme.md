*SQOOP SKILLS*

1. Using sqoop, import products_replica table from MYSQL into hdfs such that fields are separated by a '|' and lines are separated by '\n'. Null values are represented as -1 for numbers and "NOT-AVAILABLE" for strings. Only records with product id greater than or equal to 1 and less than or equal to 1000 should be imported and use 3 mappers for importing. The destination file should be stored as a text file to directory  /user/cloudera/problem5/products-text.   

2. Using sqoop, import products_replica table from MYSQL into hdfs such that fields are separated by a ‘*’ and lines are separated by ‘\n’. Null values are represented as -1000 for numbers and “NA” for strings. Only records with product id less than or equal to 1111 should be imported and use 2 mappers for importing. The destination file should be stored as a text file to directory 

3. Using sqoop, import products_replica table from MYSQL into hdfs such that fields are separated by a ‘*’ and lines are separated by ‘\n’. Null values are represented as -1000 for numbers and “NA” for strings. Only records with product id greater than 1111 should be imported and use 5 mappers for importing. The destination file should be stored as a text file to directory  /user/cloudera/problem5/products-text-part2.  

4. Using sqoop merge data available in /user/cloudera/problem5/products-text-part1 and /user/cloudera/problem5/products-text-part2 to produce a new set of files in /user/cloudera/problem5/products-text-both-parts  

5. Using sqoop do the following. Read the entire steps before you create the sqoop job.

    create a sqoop job Import Products_replica table as text file to directory /user/cloudera/problem5/products-incremental. Import all the records.
    insert three more records to Products_replica from mysql
    run the sqoop job again so that only newly added records can be pulled from mysql
    insert 2 more records to Products_replica from mysql
    run the sqoop job again so that only newly added records can be pulled from mysql
    Validate to make sure the records have not be duplicated in HDFS

6. Using sqoop do the following. Read the entire steps before you create the sqoop job.

    create a hive table in database named problem5 using below command
    create table products_hive  (product_id int, product_category_id int, product_name string, product_description string, product_price float, product_imaage string,product_grade int,  product_sentiment string);
    create a sqoop job Import Products_replica table as hive table to database named problem5. name the table as products_hive.
    insert three more records to Products_replica from mysql
    run the sqoop job again so that only newly added records can be pulled from mysql
    insert 2 more records to Products_replica from mysql
    run the sqoop job again so that only newly added records can be pulled from mysql
    Validate to make sure the records have not been duplicated in Hive table

