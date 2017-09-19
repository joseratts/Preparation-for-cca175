//Modificar el path del fichero texto.txt para que no arroje error
//wordcount usando regex
val textFile = sc.textFile("file:///home/cloudera/Documents/texto.txt").cache()
val words = textFile.flatMap(linea => linea.split("\\s")).filter(word => !word.isEmpty)
val wordCount = words.map(word => (word.toLowerCase, 1)).reduceByKey((x, y) => x + y)
val wordCountSorted = wordCount.sortBy(pair => pair._2, false, 1)
wordCountSorted.take(10).foreach(println)

val words = textFile.flatMap(linea => linea.split("\\W+")).filter(word => !word.isEmpty)
val wordCount = words.map(word => (word.toLowerCase, 1)).reduceByKey((x, y) => x + y)
val wordCountSorted = wordCount.sortBy(pair => pair._2, false, 1)
wordCountSorted.take(10).foreach(println)

//wordcount sin regex
val words = textFile.flatMap(linea => linea.split(" ")).filter(word => !word.isEmpty)
val wordCount = words.map(word => (word, 1)).reduceByKey((x, y) => x + y)
val wordCountSorted = wordCount.sortBy(pair => pair._2, false, 1)
wordCountSorted.take(10).foreach(println)

//Conclusión para hacer un wordcount realmente fiable es necesario utilizar una expresión regex mucho más elaborada