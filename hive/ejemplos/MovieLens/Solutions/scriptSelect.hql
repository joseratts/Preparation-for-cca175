-- 1. Hallar el top 20 de películas mejor valoradas (rating) y que hayan sido valoradas al menos 10 veces.

-- 2. Hallar el top 10 de películas con mejor rating cuyo genero sea comedia.

INSERT OVERWRITE LOCAL DIRECTORY '/home/cloudera/hive/movies_result2'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
SELECT m.title, r.rating FROM movies m JOIN ratings r ON (m.movieid = r.movieid) where array_contains(m.genres,"Comedy") ORDER BY r.rating DESC LIMIT 10;

-- 3. Hallar el top 10 de películas con mejor rating cuyo genero sea Drama.

INSERT OVERWRITE LOCAL DIRECTORY '/home/cloudera/hive/movies_result3'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
SELECT m.title, r.rating FROM movies m JOIN ratings r ON (m.movieid = r.movieid) where array_contains(m.genres,"Drama") ORDER BY r.rating DESC LIMIT 10;

-- 4. Hallar el promedio de rating otorgado a las películas por cada usuario, pero únicamente para los 20 usuarios que mas valoraciones (ratings) han dado.