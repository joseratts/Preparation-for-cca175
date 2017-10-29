*Problem Statement*

1. This step comprises of three substeps. Please perform tasks under each subset completely

    using sqoop pull data from MYSQL orders table into /user/cloudera/problem7/prework as AVRO data file using only one mapper
    Pull the file from \user\cloudera\problem7\prework into a local folder named flume-avro
    create a flume agent configuration such that it has an avro source at localhost and port number 11112,  a jdbc channel and an hdfs file sink at /user/cloudera/problem7/sink
    Use the following command to run an avro client flume-ng avro-client -H localhost -p 11112 -F <<Provide your avro file path here>>
