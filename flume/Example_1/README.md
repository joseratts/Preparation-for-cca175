En este ejemplo vamos a desencadenar un flujo de Flume, mediante un demonio que va a estar escuchando la entrada de fichero en un determinado directorio:

Agente Flume:

- source: spooldir
- channel: file
- sink: hdfs

Ademas vamos usar un interceptor para añadir un Header basado en el timestamp
