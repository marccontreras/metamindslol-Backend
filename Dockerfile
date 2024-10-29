# Usa una imagen oficial de Java
FROM openjdk:17-jdk-alpine

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR generado en el contenedor
COPY build/libs/*.jar app.jar

# Expone el puerto (Render le asignará uno dinámicamente)
EXPOSE 8080

# Configura el comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
