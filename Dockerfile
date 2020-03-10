### STAGE 1: Build ###
FROM openjdk:8-alpine
ADD target/backend-MS.jar backend-MS.jar
ENTRYPOINT ["java","-jar","/backend-MS.jar"]
