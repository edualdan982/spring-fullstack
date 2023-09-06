#Elegimos una imagen como base para levantar el conenedor
FROM openjdk:17.0.2
#Configuramos el la direccion de trabajo, este el contexto de la ruta
WORKDIR /app

COPY ./target/msvc-cursos-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

ENTRYPOINT [ "java", "-jar", "msvc-cursos-0.0.1-SNAPSHOT.jar"]
