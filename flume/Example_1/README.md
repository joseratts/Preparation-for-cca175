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

* Como añadido podemos añadir cierto procesamiento a los eventos antes de pasarlos al canal, mediante los interceptores.

Uno de los interceptores más usados es el del tipo timestamp, para lo cual debemos añadir la siguiente configuración:

 >agent1.sources.source1.interceptors.i1.type = timestamp
 >agent1.sources.source1.interceptors.i1.preserveExisting = true
 >agent1.sinks.sink1.hdfs.path = /tmp/flume/events/%y-%m-%d/%H%M/%S
 >agent1.sources.source1.interceptors = i1

* Con lo que tendremos la siguiente salida:

 >17/09/29 09:16:34 INFO hdfs.BucketWriter: Closing /tmp/flume/events/17-09-29/0915/59/_events.1506701760251.log.tmp
 >17/09/29 09:16:34 INFO hdfs.BucketWriter: Renaming /tmp/flume/events/17-09-29/0915/59/_events.1506701760251.log.tmp to /tmp/flume/events/17-09-29/0915/59/events.1506701760251.log

 