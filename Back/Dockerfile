# Usar la imagen base de Ubuntu
FROM ubuntu:latest

# Información del autor
LABEL authors="jagui"

# Actualizar el repositorio de paquetes y luego instalar Java 17 y Maven
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven \
    && apt-get clean

# Establecer la variable de entorno JAVA_HOME
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

# Crear un directorio en la imagen para la aplicación
WORKDIR /app

# Copiar los archivos de la aplicación al contenedor
COPY . /app

# Compilar el proyecto usando Maven
RUN mvn clean package -DskipTests

# Copiar el archivo JAR generado por Maven al directorio de la imagen
COPY target/api-0.0.1-SNAPSHOT.jar /app/api.jar

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/api.jar"]