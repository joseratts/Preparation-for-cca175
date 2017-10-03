// sc is an existing SparkContext.
val sqlContext = new org.apache.spark.sql.SQLContext(sc)
// this is used to implicitly convert an RDD to a DataFrame.
import sqlContext.implicits._

val textFile = sc.textFile("file:///home/cloudera/Documents/OlympicAthletes.csv")
val df = textFile.map{line => val values = line.split(",")
(values(0), values(1).toInt, values(2), values(3).toInt, values(4), values(5), values(6).toInt, values(7).toInt, values(8).toInt, values(9).toInt)}.toDF()

df.registerTempTable("OlympicAthletes");

val dfWithHeader = sqlContext.sql("SELECT _1 as NAME, _2 as AGE, _3 as COUNTRY, _4 as YEAR, _5 as DATE, _6 as DISCIPLINE, _7 as GOLD, _8 as SILVER, _9 as BRONZE, _10 as MEDALS FROM OlympicAthletes").toDF()

dfWithHeader.registerTempTable("OlympicAthletes")


//Ejercicio 1
val result = sqlContext.sql("SELECT AGE, SUM(MEDALS) AS TOTAL FROM OlympicAthletes GROUP BY AGE ORDER BY TOTAL DESC");
result.coalesce(1).foreach(println)

//Ejercicio 2
val result2 = sqlContext.sql("SELECT NAME, SUM(GOLD*3 + SILVER*2 + BRONZE) AS POINTS FROM OlympicAthletes GROUP BY NAME ORDER BY POINTS DESC LIMIT 10");
result2.coalesce(1).foreach(println)

//Ejercicio 3
val result3 = sqlContext.sql("SELECT COUNTRY, COUNT(MEDALS) AS TOTAL_MEDALS FROM OlympicAthletes WHERE DISCIPLINE = 'Swimming' GROUP BY COUNTRY ORDER BY TOTAL_MEDALS DESC LIMIT 20");
result3.coalesce(1).foreach(println)

//Ejercicio 4
val result4 = sqlContext.sql("SELECT YEAR, COUNT(MEDALS) AS TOTAL_MEDALS FROM OlympicAthletes WHERE COUNTRY = 'India' GROUP BY YEAR ORDER BY YEAR ASC");
result4.coalesce(1).foreach(println)

//Ejercicio 5
val result5 = sqlContext.sql("SELECT COUNTRY, COUNT(MEDALS) AS TOTAL_MEDALS FROM OlympicAthletes GROUP BY COUNTRY ORDER BY TOTAL_MEDALS DESC");
result5.coalesce(1).foreach(println)

//Ejercicio 6
val result6 = sqlContext.sql("SELECT COUNTRY, SUM(GOLD) AS TOTAL_GOLD, SUM(SILVER) AS TOTAL_SILVER, SUM(BRONZE) AS TOTAL_BRONZE FROM OlympicAthletes GROUP BY COUNTRY ORDER BY TOTAL_GOLD DESC, TOTAL_SILVER DESC, TOTAL_BRONZE DESC");
result6.coalesce(1).foreach(println)