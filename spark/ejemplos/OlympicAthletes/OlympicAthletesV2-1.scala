//Version mejorada de la version 2 de la solucion donde se registra la tabla una unica vez 
//aprovechando el uso de una case class

// sc is an existing SparkContext.
val sqlContext = new org.apache.spark.sql.SQLContext(sc)
// this is used to implicitly convert an RDD to a DataFrame.
import sqlContext.implicits._

case class OlympicData (name: String, age: Int, country: String, year: Int, date: String, discipline: String, gold: Int, silver: Int, bronze: Int, medals: Int);
val textFile = sc.textFile("file:///home/cloudera/Documents/OlympicAthletes.csv")
val df = textFile.map{line => val values = line.split(",")
OlympicData(values(0), values(1).toInt, values(2), values(3).toInt, values(4), values(5), values(6).toInt, values(7).toInt, values(8).toInt, values(9).toInt)}.toDF()

df.registerTempTable("OlympicAthletes");

//Ejercicio 1
val result = sqlContext.sql("SELECT age, SUM(medals) AS TOTAL FROM OlympicAthletes GROUP BY age ORDER BY TOTAL DESC");
result.coalesce(1).foreach(println)

//Ejercicio 2
val result2 = sqlContext.sql("SELECT name, SUM(gold*3 + silver*2 + bronze) AS POINTS FROM OlympicAthletes GROUP BY name ORDER BY POINTS DESC LIMIT 10");
result2.coalesce(1).foreach(println)

//Ejercicio 3
val result3 = sqlContext.sql("SELECT country, COUNT(medals) AS TOTAL_MEDALS FROM OlympicAthletes WHERE discipline = 'Swimming' GROUP BY country ORDER BY TOTAL_MEDALS DESC LIMIT 20");
result3.coalesce(1).foreach(println)

//Ejercicio 4
val result4 = sqlContext.sql("SELECT year, COUNT(medals) AS TOTAL_MEDALS FROM OlympicAthletes WHERE country = 'India' GROUP BY year ORDER BY year ASC");
result4.coalesce(1).foreach(println)

//Ejercicio 5
val result5 = sqlContext.sql("SELECT country, COUNT(medals) AS TOTAL_MEDALS FROM OlympicAthletes GROUP BY country ORDER BY TOTAL_MEDALS DESC");
result5.coalesce(1).foreach(println)

//Ejercicio 6
val result6 = sqlContext.sql("SELECT country, SUM(gold) AS TOTAL_GOLD, SUM(silver) AS TOTAL_SILVER, SUM(bronze) AS TOTAL_BRONZE FROM OlympicAthletes GROUP BY country ORDER BY TOTAL_GOLD DESC, TOTAL_SILVER DESC, TOTAL_BRONZE DESC");
result6.coalesce(1).foreach(println)