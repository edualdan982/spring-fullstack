## Guia de despliegue
#### PARA CONSTRUIR EL CONTENEDOR

Asegurarse que esta en la carpeta raiz 'curso-kubernetes', el uso para separar la rutas varia segun si esta en linux o windows

```bash
docker build -t msvc-usuarios:{version} . -f ./msvc-usuarios/Dockerfile
```

#### PARA DESPLEGAR EL CONTENEDOR

Es posible que tenga que realacionar una red, tenga que poner una bandera de: '--netwoerk nombre_red' y se debe hacer referencia a la imagen a desplegar

```bash
docker run -p PORT:PORT_CONTAINER --name usuarios msvc-usuarios:{version}
```

#### Para levantar con un archio .env para variables de entorno

```bash
docker run -p 8001:8000 --env-file ./msvc-usuarios/.env -d --rm --name msvc-usuarios --network spring usuarios
```

```bash
docker run -p 8001:8001 --env-file ./msvc-usuarios/.env -d --rm --name msvc-usuarios --network spring usuarios
```

#### Para construir con el docker-compose solo debe ejecutar el comando:

```bash
docker-compose up -d
```
Puede variar si no quiere que se ejecute en segundo plano quitando el parametro **-d**

#### Para subir una images a un repositorio debe tener el nombre especifico con la combinación del ${username}/${repositorio}

```bash
docker push ${username}/${repositorio}
```


#### Para eliminar todas las images
```bash
docker image prune -a 
```