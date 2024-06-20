# Utiliza la imagen base de Ubuntu
FROM ubuntu:latest

# Etiqueta de los autores
LABEL authors="jagui"

# Actualiza el sistema e instala dependencias necesarias
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven \
    && apt-get clean

# Establece la variable de entorno JAVA_HOME
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia los archivos de la aplicación al contenedor
COPY . /app

# Compila la aplicación utilizando Maven
RUN mvn clean install

# Expone el puerto en el que la aplicación se ejecutará
EXPOSE 8081

# Define el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["mvn", "spring-boot:run"]
