# Spark
Mi estrategia en el desarrollo de spark de cara al examen de certificación será la creación de scripts en gedit o vi y ejecutarlos en la spark-shell con los siguientes comandos:

* Si me encuentro fuera de la consola de spark invocando el comando spark-shell -i script.scala
* Si me encuentro dentro de la consola de spark (por ejemplo suponiendo que inmediatamente después de ejecutar el script con el comando anterior y necesito ejecutarlo de nuevo) con el comando :load path_to_script_folder/script.scala

La razón de esta estrategia radica en que dado el supuesto de que la imagen traerá consigo instalado eclipse, aunque me sentiría mas comodo programando con eclipse y su autocompletar, creo que el lidiar con el maven o el sbt y todo el proceso build eso me restaría tiempo y no hay que olvidar que son 2 horas para realizar toda la prueba
