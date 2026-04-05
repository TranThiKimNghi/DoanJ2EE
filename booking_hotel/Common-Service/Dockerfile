FROM eclipse-temurin:21-jdk


RUN mkdir /app
#Create user group
#RUN adduser --system --group ubuntu

#Copy app
COPY Common-Service-0.0.1-SNAPSHOT.jar /app/app.jar

#RUN chown -R ubuntu:ubuntu /app
#USER ubuntu

WORKDIR /app
ENTRYPOINT ["java", "-jar","/app/app.jar"]

EXPOSE 8080