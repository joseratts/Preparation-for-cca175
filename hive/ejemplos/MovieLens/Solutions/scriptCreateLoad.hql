set hive.cli.print.header=true;

CREATE TABLE IF NOT EXISTS movies (
	movieId INT,
	title STRING,
	genres ARRAY<STRING>
)
ROW FORMAT DELIMITED
	FIELDS TERMINATED BY ','
	COLLECTION ITEMS TERMINATED BY '|';
	
LOAD DATA LOCAL INPATH '/home/cloudera/Preparation-for-cca175/hive/ejemplos/MovieLens/data/movies.csv' OVERWRITE INTO TABLE movies;


SELECT genres FROM movies WHERE array_contains(genres,"Drama");


CREATE TABLE IF NOT EXISTS ratings (
	userId INT,
	movieId INT,
	rating DECIMAL(3,2)
	timestamp TIMESTAMP
)
ROW FORMAT DELIMITED
	FIELDS TERMINATED BY ',';

LOAD DATA LOCAL INPATH '/home/cloudera/Preparation-for-cca175/hive/ejemplos/MovieLens/data/ratings.csv' OVERWRITE INTO TABLE ratings;
