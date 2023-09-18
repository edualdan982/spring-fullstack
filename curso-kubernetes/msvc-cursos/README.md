# PARA CONSTRUIR EL CONTENEDOR
- Asegurarse que esta en la carpeta raiz 'curso-kubernetes', el uso para separar la rutas varia segun si esta en linux o windows
* $> docker build -t msvc-cursos:{version} . -f ./msvc-cursos/Dockerfiles

# PARA DESPLEGAR EL CONTENEDOR
- Es posible que tenga que realacionar una red, tenga que poner una bandera de: '--netwoerk nombre_red' y se debe hacer referencia a la imagen a desplegar
* $> docker run -p PORT:PORT_CONTAINER --name cursos msvc-cursos:{version}