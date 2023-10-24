# spring-cloud

Revisar las versiones de los cores para poder levantar los volumenes.

Indagando un poco, probe con la images mysql:8.0 pero al contrario que de la imagen mysql:8 este lanzaba un error y al parecer el problema es que los archivos del persistence volume del minikube tenian problemas tal vez con las pruebas que realice. De esa manera ingrese al minikube con:

```bash
minikube ssh
```

Me fui a la ruta /var/lib/mysql y borré el contenido. Solo cree un directorio vacío con el mismo nombre, volví a aplicar el deployment y probe si se persistía los datos y funciono, el problema está en que si en la primera configuración no le mandas los parámetros correctos estos se persistirán y no se cambiara la contraseña del root.