En este ejemplo vamos a desencadenar un flujo de Flume, mediante un demonio que va a estar escuchando la entrada de fichero en un determinado directorio:

Agente Flume:

- source: spooldir
- channel: file
- sink: hdfs

* Ejecución

 flume-ng agent --conf-file conf/pool-to-hdfs.properties --name agent2 -Dflume.root.logger=DEBUG,INFO,console

* Salida Esperada
 >INFO *hdfs.HDFSDataStream: Serializer* = TEXT, UseRawLocalFileSystem = false
 >INFO avro.ReliableSpoolingFileEventReader: Preparing to move file /tmp/spooldir/ex1/data.log to /tmp/spooldir/ex1/data.log.*COMPLETED*
 >INFO hdfs.HDFSDataStream: Serializer = TEXT, UseRawLocalFileSystem = false
 >INFO hdfs.BucketWriter: Creating /tmp/flume/ex1/_events.1506697988145.log.tmp
 >INFO hdfs.BucketWriter: Closing /tmp/flume/ex1/_events.1506697988145.log.tmp
 >INFO hdfs.BucketWriter: Renaming /tmp/flume/ex1/_events.1506697988145.log.tmp to /tmp/flume/ex1/events.1506697988145.log
 >INFO hdfs.HDFSEventSink: Writer callback called.

Una vez terminado el proceso, se marcará el fichero como finalizado añadiendo la extension *COMPLETED*, para que no vuelva a ser procesado