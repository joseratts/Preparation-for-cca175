Problem Statement

1. Import orders table from mysql as text file to the destination /user/cloudera/problem4/text. Fields should be terminated by a tab character (“\t”) character and lines should be terminated by new line character (“\n”).
2. Import orders table from mysql  into hdfs to the destination /user/cloudera/problem4/avro. File should be stored as avro file.
3. Import orders table from mysql  into hdfs  to folders /user/cloudera/problem4/parquet. File should be stored as parquet file.
4. Transform/Convert data-files at /user/cloudera/problem4/avro and store the converted file at the following locations and file formats
* save the data to hdfs using snappy compression as parquet file at /user/cloudera/problem4/parquet-snappy-compress
* save the data to hdfs using gzip compression as text file at /user/cloudera/problem4/text-gzip-compress
* save the data to hdfs using no compression as sequence file at /user/cloudera/problem4/sequence
* save the data to hdfs using snappy compression as text file at /user/cloudera/problem4/text-snappy-compress
5. Transform/Convert data-files at /user/cloudera/problem4/parquet-snappy-compress and store the converted file at the following locations and file formats
* save the data to hdfs using no compression as parquet file at /user/cloudera/problem4/parquet-no-compress
* save the data to hdfs using snappy compression as avro file at /user/cloudera/problem4/avro-snappy
6. Transform/Convert data-files at /user/cloudera/problem4/avro-snappy and store the converted file at the following locations and file formats
* save the data to hdfs using no compression as json file at /user/cloudera/problem4/json-no-compress
* save the data to hdfs using gzip compression as json file at /user/cloudera/problem4/json-gzip
7. Transform/Convert data-files at  /user/cloudera/problem4/json-gzip and store the converted file at the following locations and file formats
* save the data to as comma separated text using gzip compression at   /user/cloudera/problem4/csv-gzip
8. Using spark access data at /user/cloudera/problem4/sequence and stored it back to hdfs using no compression as ORC file to HDFS to destination /user/cloudera/problem4/orc