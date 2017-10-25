val sqlContext = new org.apache.spark.sql.SQLContext(sc)

import sqlContext.implicits._
import org.apache.spark.sql.functions._
import com.databricks.spark.avro._

sqlContext.setConf("spark.sql.avro.compress.codec", "snappy")

val df = sqlContext.read.avro("/user/cloudera/products/exercise1/avrodata/")

//solucion ejercicio 3
df.select("price", "category_id").where($"price" > 50).groupBy("category_id").agg(avg("price").as("avg_price"), min("price").alias("min_price"), max("price").as("max_price")).orderBy($"avg_price".desc).show()

df.select("price", "category_id").where($"price" > 50).groupBy("category_id").agg(avg("price").as("avg_price"), min("price").alias("min_price"), max("price").as("max_price")).orderBy($"avg_price".desc).map(x => x(0)+","+x(1)+","+x(2)+","+x(3)).saveAsTextFile("/user/cloudera/products/exercise1/resultdata/")


//solucion ejercicio 4
df.registerTempTable("products")

val result = sqlContext.sql("SELECT category_id, round(avg(price), 2) as avg_price, min(price) as min_price, max(price) as max_price FROM products WHERE price > 50 GROUP BY category_id ORDER BY avg_price desc")

result.show()
