val sqlContext = new org.apache.spark.sql.SQLContext(sc)

import sqlContext.implicits._
import org.apache.spark.sql.functions._

val data = sqlContext.read.parquet("file:///home/cloudera/Preparation-for-cca175/spark/ejemplos/Padron/data/padron.parquet")

//replace blank for 0
val dataNoBlank = data.na.replace("EspanolesHombres", Map("" -> "0")).na.replace("EspanolesMujeres", Map("" -> "0")).na.replace("ExtranjerosHombres", Map("" -> "0")).na.replace("ExtranjerosMujeres", Map("" -> "0"))

//trim values
val dataNoBlankTrimmed = dataNoBlank.withColumn("DESC_DISTRITO", trim($"DESC_DISTRITO")).withColumn("DESC_BARRIO", trim($"DESC_BARRIO"))

dataNoBlankTrimmed.show()

dataNoBlankTrimmed.select("ExtranjerosHombres", "ExtranjerosMujeres", "EspanolesHombres", "EspanolesMujeres").agg(avg("ExtranjerosHombres"), avg("ExtranjerosMujeres"), avg("EspanolesHombres"), avg("EspanolesMujeres")).show()

dataNoBlankTrimmed.select("ExtranjerosHombres", "ExtranjerosMujeres", "EspanolesHombres", "EspanolesMujeres", "DESC_DISTRITO").where($"DESC_DISTRITO" === "CENTRO").agg(avg("ExtranjerosHombres"), avg("ExtranjerosMujeres"), avg("EspanolesHombres"), avg("EspanolesMujeres")).show()

val dataCentro = dataNoBlankTrimmed.select("ExtranjerosHombres", "ExtranjerosMujeres", "EspanolesHombres", "EspanolesMujeres", "DESC_DISTRITO").where($"DESC_DISTRITO" === "CENTRO")
dataCentro.write.mode("overwrite").json("file:///home/cloudera/Preparation-for-cca175/spark/ejemplos/Padron/result")
