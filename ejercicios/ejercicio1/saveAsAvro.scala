val sqlContext = new org.apache.spark.sql.SQLContext(sc)

import sqlContext.implicits._
import org.apache.spark.sql.functions._
import com.databricks.spark.avro._

case class product(id: Int, category_id: Int, name: String, description: String, price: Float, image: String)

val df = sc.textFile("/user/cloudera/products/exercise1/rawdata/").map{x => val fields = x.split("\\|"); product(fields(0).toInt, fields(1).toInt, fields(2), fields(3), fields(4).toFloat, fields(5))}.toDF

df.show()

sqlContext.setConf("spark.sql.avro.compression.codec", "snappy")

df.write.partitionBy("category_id").mode("overwrite").avro("/user/cloudera/products/exercise1/avrodata/")

