#Elegimos una imagen como base para levantar el conenedor
FROM openjdk:17-jdk-alpine as builder
#Configuramos el la direccion de trabajo, este el contexto de la ruta
WORKDIR /app/msvc-gateway
COPY ./pom.xml /app

COPY ./msvc-gateway/.mvn ./.mvn
COPY ./msvc-gateway/mvnw .
COPY ./msvc-gateway/pom.xml .

#ESTE COMANDO SOLO HA SIDO NECESARIO PARA CONVERTIR EL FORMATO DEL FICHERO MVNW DE WINDOWS A LINUX, SINO FALLABA
RUN sed -i -e 's/\r$//' ./mvnw

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline

COPY ./msvc-gateway/src ./src

RUN ./mvnw clean package -DskipTests
#------------------------------------------------------------------------------------------------------------------

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/msvc-gateway/target/msvc-gateway-0.0.1-SNAPSHOT.jar .
EXPOSE 8002

# ENTRYPOINT [ "java", "-jar", "msvc-gateway-0.0.1-SNAPSHOT.jar"]
CMD [ "java", "-jar", "msvc-gateway-0.0.1-SNAPSHOT.jar"]