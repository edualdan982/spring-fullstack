#Elegimos una imagen como base para levantar el conenedor
FROM openjdk:17.0.2
#Configuramos el la direccion de trabajo, este el contexto de la ruta
WORKDIR /app/msvc-usuarios
COPY ./pom.xml /app

COPY ./msvc-usuarios .

#ESTE COMANDO SOLO HA SIDO NECESARIO PARA CONVERTIR EL FORMATO DEL FICHERO MVNW DE WINDOWS A LINUX, SINO FALLABA
RUN sed -i -e 's/\r$//' ./mvnw
RUN ./mvnw clean package -DskipTests

EXPOSE 8001

ENTRYPOINT [ "java", "-jar", "./target/msvc-usuarios-0.0.1-SNAPSHOT.jar"]