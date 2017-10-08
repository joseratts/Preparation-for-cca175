//Version mejorada de la version 2.1 de la solucion donde todas las consultas se realizan sobre el dataframe
//sin declarar tablas temporales

// sc is an existing SparkContext.
val sqlContext = new org.apache.spark.sql.SQLContext(sc)
// this is used to implicitly convert an RDD to a DataFrame.
import sqlContext.implicits._
import org.apache.spark.sql.functions._

case class OlympicData (name: String, age: Int, country: String, year: Int, date: String, discipline: String, gold: Int, silver: Int, bronze: Int, medals: Int);
val textFile = sc.textFile("file:////home/cloudera/Preparation-for-cca175/spark/ejemplos/OlympicAthletes/data/OlympicAthletes.csv")
val df = textFile.map{line => val values = line.split(",")
OlympicData(values(0), values(1).toInt, values(2), values(3).toInt, values(4), values(5), values(6).toInt, values(7).toInt, values(8).toInt, values(9).toInt)}.toDF()


//Ejercicio 1
val result = df.select("age", "medals").groupBy("age").sum("medals").show()

//Ejercicio 2
val result2 = df.select("name", "gold", "silver", "bronze").groupBy("name").agg((sum("gold")*3 + sum("silver")*2 + sum("bronze")).as("POINTS")).orderBy($"POINTS".desc).limit(10).show()

//Ejercicio 3
val result3 = df.select("country", "medals", "discipline").where("discipline = 'Swimming'").groupBy("country").sum("medals").orderBy($"sum(medals)".desc).limit(20).show()

//Ejercicio 4
val result4 = df.select("year", "medals", "country").where("country = 'India'").groupBy("year").sum("medals").orderBy($"year".asc).show()

//Ejercicio 5
val result5 = df.select("country", "medals").groupBy("country").sum("medals").orderBy($"sum(medals)".desc).show()

//Ejercicio 6
val result6 = df.select("country", "gold", "silver", "bronze").groupBy("country").agg(sum("gold").as("TOTAL_GOLD"), sum("silver").as("TOTAL_SILVER"), sum("bronze").as("TOTAL_BRONZE")).orderBy($"TOTAL_GOLD".desc, $"TOTAL_SILVER".desc, $"TOTAL_BRONZE".desc).show()