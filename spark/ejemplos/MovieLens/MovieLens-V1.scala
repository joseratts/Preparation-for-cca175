val sqlContext = new org.apache.spark.sql.SQLContext(sc)

import sqlContext.implicits._
import org.apache.spark.sql.functions._

case class Rating (userId: String, movieId: String, rating: Float)
case class Movie (movieId: String, title: String, genres: Array[String])

val ratingsFile = sc.textFile("file:///home/cloudera/Preparation-for-cca175/spark/ejemplos/MovieLens/data/ratings.csv")
val header = ratingsFile.first() //extract header
val ratingsFileNoHeader = ratingsFile.filter(row => row != header)   //filter out header
val ratingDF = ratingsFileNoHeader.map{ line => val values = line.split(",")
				Rating(values(0), values(1), values(2).toFloat)}.toDF()

val moviesFile = sc.textFile("file:///home/cloudera/Preparation-for-cca175/spark/ejemplos/MovieLens/data/movies.csv")
val moviesHeader = moviesFile.first() //extract header
val moviesFileNoHeader = moviesFile.filter(row => row != moviesHeader)   //filter out header
val moviesDF = moviesFileNoHeader.map{ line => val values = line.split(",")
				Movie(values(0), values(1), values(2).split("\\|"))}.toDF()

val movieRatingDF = ratingDF.join(moviesDF, "movieId")

//movieRatingDF.show()

//Ejercicio 1
movieRatingDF.select("movieId", "title", "rating").groupBy("movieId", "title")
.agg((avg("rating")).as("AVG-RATING"), (count("rating")).as("REVIEWS")).filter($"REVIEWS" > 10)
.orderBy($"AVG-RATING".desc).show()

//Ejercicio 2
movieRatingDF.select("movieId", "title", "rating", "genres").where(array_contains($"genres", "Comedy"))
.groupBy("movieId", "title").agg((avg("rating")).as("AVG-RATING"), (count("rating")).as("REVIEWS"))
.filter($"REVIEWS" > 10).orderBy($"AVG-RATING".desc).show()

//Ejercicio 3
movieRatingDF.select("movieId", "title", "rating", "genres").where(array_contains($"genres","Drama"))
.groupBy("movieId", "title").agg((avg("rating")).as("AVG-RATING"), (count("rating")).as("REVIEWS"))
.filter($"REVIEWS" > 10).orderBy($"AVG-RATING".desc).show()

//Ejercicio 4
ratingDF.select("userId", "rating").groupBy("userId").agg((avg("rating")).as("AVG-RATING"), (count("rating")).as("REVIEWS"))
.orderBy($"REVIEWS".desc, $"AVG-RATING".desc).limit(20).show()