val sqlContext = new org.apache.spark.sql.SQLContext(sc)

import sqlContext.implicits._
import org.apache.spark.sql.functions._
import com.databricks.spark.avro._

val orderDF = sqlContext.read.avro("/user/cloudera/problem1/orders")
val orderItemsDF = sqlContext.read.avro("/user/cloudera/problem1/orders-items")

//orderDF.show()
//orderItemsDF.show()

val orderJoinDF = orderDF.join(orderItemsDF, $"order_id" === $"order_item_order_id", "inner")
orderJoinDF.show();

val result = orderJoinDF.groupBy(to_date(from_unixtime($"order_date"/1000)).as("order_formatted_date"), $"order_status").agg(round(sum($"order_item_subtotal"),2).as("total_amount"), countDistinct("order_id").alias("total_orders")).orderBy($"order_formatted_date".desc, $"order_status".asc, $"total_amount".desc)

sqlContext.conf("spark.sql.parquet.compression.codec", "gzip")
result.write.parquet("/user/cloudera/problem1/result4a-gzip")

sqlContext.conf("spark.sql.parquet.compression.codec", "snappy")
result.write.parquet("/user/cloudera/problem1/result4a-snappy")
