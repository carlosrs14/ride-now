# Imagen base con Tomcat
FROM tomcat:11-jdk21

# Elimina las apps por defecto de Tomcat (opcional)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia tu WAR al directorio de despliegue de Tomcat
COPY dist/microservicio_usuario_RN.war /usr/local/tomcat/webapps/ROOT.war

# Expone el puerto por el que se accede a Tomcat
EXPOSE 8081

# Comando de arranque (ya viene por defecto, pero se puede añadir)
CMD ["catalina.sh", "run"]


# docker build -t microservicio-usuario .
# docker run -d -p 8081:8080 --name usuario-service microservicio-usuario
