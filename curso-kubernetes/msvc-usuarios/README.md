# PARA CONSTRUIR EL CONTENEDOR
- Asegurarse que esta en la carpeta raiz 'curso-kubernetes', el uso para separar la rutas varia segun si esta en linux o windows
* $> docker build -t msvc-usuarios:{version} . -f ./msvc-usuarios/Dockerfile

# PARA DESPLEGAR EL CONTENEDOR
- Es posible que tenga que realacionar una red, tenga que poner una bandera de: '--netwoerk nombre_red' y se debe hacer referencia a la imagen a desplegar
* $> docker run -p PORT:PORT_CONTAINER --name usuarios msvc-usuarios:{version}


# Para levantar con un archio .env para variables de entorno
- $> docker run -p 8001:8000 --env-file .\msvc-usuarios\.env -d --rm --name msvc-usuarios --network spring usuarios