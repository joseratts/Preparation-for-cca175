val sqlContext = new org.apache.spark.sql.SQLContext(sc)

import sqlContext.implicits._
import org.apache.spark.sql.functions._

case class Product (product_id: Int, product_category_id: Int, product_name: String, product_description: String, product_price: Float, product_image: String)

val textFile = sc.textFile("/user/cloudera/problem2/products/")
val df = textFile.map{x => val values = x.split('|'); Product(values(0).toInt, values(1).toInt, values(2), values(3), values(4).toFloat, values(5))}.toDF()
df.show()

val filteredDF = df.filter($"product_price" < 100).orderBy("product_category_id")

//solucion 4a
val result4a = filteredDF.select("product_category_id", "product_price", "product_id").groupBy($"product_category_id").agg(max("product_price").as("max_price"), countDistinct("product_id").as("total_product"), avg("product_price").as("avg_product_price"), min("product_price").as("min_price"))
result4a.show()

//solucion 4b
filteredDF.registerTempTable("products_filtered")
val result4b = sqlContext.sql("SELECT product_category_id, count(distinct(product_id)) as total_products, max(product_price) as max_price, min(product_price) as min_price, avg(product_price) as avg_price FROM products_filtered GROUP BY product_category_id ORDER BY product_category_id")
result4b.show()

//solucion 5a
import com.databricks.spark.avro._

sqlContext.setConf("spark.sql.avro.compression.codec", "snappy")
result4a.write.mode("overwrite").avro("/user/cloudera/problem2/products/result-df")

//solucion 5b
result4b.write.mode("overwrite").avro("/user/cloudera/problem2/products/result-sql")
