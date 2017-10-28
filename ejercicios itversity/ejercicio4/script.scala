val sqlContext = new org.apache.spark.sql.SQLContext(sc)

import sqlContext.implicits._
import org.apache.spark.sql.functions._
import com.databricks.spark.avro._

//solucion problema 4
val avroDF = sqlContext.read.avro("/user/cloudera/problem4/avro")
avroDF.show();

sqlContext.setConf("spark.sql.parquet.compression.codec", "snappy")
avroDF.write.mode("overwrite").parquet("/user/cloudera/problem4/parquet-snappy-compress")

val textRDD = avroDF.map(x=> (x(0) + "\t" +  x(1) + "\t" + x(2) + "\t" + x(3)))
textRDD.saveAsTextFile("/user/cloudera/problem4/text-gzip-compress", classOf[org.apache.hadoop.io.compress.GzipCodec])
//itversity lo hacen mediante sqoop
//De la siguiente manera me da error
//textRDD.saveAsTextFile("/user/cloudera/problem4/text-snappy-compress", classOf[org.apache.hadoop.io.compress.SnappyCodec])

//Escribe el sequenceFile que no escribe los datos como tipo Text sino que lo hace como Object o ByteWritable
//avroDF.map(x=> (x(0).toString, x(0) + "\t" + x(1) + "\t" + x(2) + "\t" + x(3))).saveAsObjectFile("/user/cloudera/problem4/sequence")
avroDF.map(x=> (x(0).toString, x(0) + "\t" + x(1) + "\t" + x(2) + "\t" + x(3))).saveAsSequenceFile("/user/cloudera/problem4/sequence")

//solucion problema 5
val parquetSnappyDF = sqlContext.read.parquet("/user/cloudera/problem4/parquet-snappy-compress")
parquetSnappyDF.show()

sqlContext.setConf("spark.sql.parquet.compression.codec", "uncompressed")
parquetSnappyDF.write.mode("overwrite").parquet("/user/cloudera/problem4/parquet-no-compress")

sqlContext.setConf("spark.sql.avro.compression.codec", "snappy")
parquetSnappyDF.write.mode("overwrite").avro("/user/cloudera/problem4/avro-snappy")

//solucion problema 6
val avroSnappyDF = sqlContext.read.avro("/user/cloudera/problem4/avro-snappy");
avroSnappyDF.show()

avroSnappyDF.toJSON.saveAsTextFile("/user/cloudera/problem4/json-no-compress")
avroSnappyDF.toJSON.saveAsTextFile("/user/cloudera/problem4/json-gzip", classOf[org.apache.hadoop.io.compress.GzipCodec])

//solucion problema 7
val jsonGzipDF = sqlContext.read.json("/user/cloudera/problem4/json-gzip")
jsonGzipDF.show()

jsonGzipDF.map(x=> x(0) + "," + x(1) + "," + x(2) + "," + x(3)).saveAsTextFile("/user/cloudera/problem4/csv-gzip", classOf[org.apache.hadoop.io.compress.GzipCodec])

//solucion problema 8
val hiveContext = new org.apache.spark.sql.hive.HiveContext(sc)
val sequenceFileRDD = sc.sequenceFile("/user/cloudera/problem4/sequence", classOf[org.apache.hadoop.io.Text], classOf[org.apache.hadoop.io.Text])
val mappedRDD = sequenceFileRDD.map(x => {
    var fields = x._2.toString.split("\t"); (fields(0),fields(1),fields(2),fields(3))
  })
val sequenceFileDF = hiveContext.createDataFrame(mappedRDD)
sequenceFileDF.write.mode("overwrite").orc("/user/cloudera/problem4/orc");

//Esta forma que es la utilizada por la gente de itversity me da error
//java.lang.AssertionError: assertion failed: The ORC data source can only be used with HiveContext.
/*val sequenceFileRDD = sc.sequenceFile("/user/cloudera/problem4/sequence", classOf[org.apache.hadoop.io.Text], classOf[org.apache.hadoop.io.Text])
val sequenceFileDF = sequenceFileRDD.map(x => {
    var fields = x._2.toString.split("\t"); (fields(0),fields(1),fields(2),fields(3))
  }).toDF
sequenceFileDF.write.orc("/user/cloudera/problem4/orc");*/
