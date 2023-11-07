#Se declara un varibale global para reutulizarlo en varios stages.
ARG MSVC_NAME=msvc-auth
#Elegimos una imagen como base para levantar el conenedor
FROM openjdk:17-jdk-alpine as builder

#Debemos declarar solo el nombre de la variable para darle uso en este stage
ARG MSVC_NAME

#Configuramos el la direccion de trabajo, este el contexto de la ruta
WORKDIR /app/$MSVC_NAME
COPY ./pom.xml /app

COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .

#ESTE COMANDO SOLO HA SIDO NECESARIO PARA CONVERTIR EL FORMATO DEL FICHERO MVNW DE WINDOWS A LINUX, SINO FALLABA
RUN sed -i -e 's/\r$//' ./mvnw

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline

COPY ./$MSVC_NAME/src ./src

RUN ./mvnw clean package -DskipTests

#------------------------------------------------------------------------------------------------------------------
#-----------------SEGUNDO STAGE------------------
FROM openjdk:17-jdk-alpine

#Debemos declarar solo el nombre de la variable para darle uso en este stage
ARG MSVC_NAME

WORKDIR /app
RUN mkdir ./logs

ARG TARGET_FOLDER=/app/$MSVC_NAME/target

COPY --from=builder $TARGET_FOLDER/msvc-auth-0.0.1-SNAPSHOT.jar .

ARG PORT_APP=9000
ENV PORT $PORT_APP
EXPOSE $PORT

# ENTRYPOINT [ "java", "-jar", "msvc-auth-0.0.1-SNAPSHOT.jar"]
CMD [ "java", "-jar", "msvc-auth-0.0.1-SNAPSHOT.jar"]