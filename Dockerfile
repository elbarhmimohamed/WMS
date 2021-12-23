FROM openjdk:8
EXPOSE 8080
ADD target/wms.jar wms.jar
ENTRYPOINT ["java","-jar","/wms.jar"]