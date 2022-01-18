# syntax=docker/dockerfile:1

FROM openjdk:17-alpine3.13

VOLUME /services

COPY /RegistryService/target/*.jar registry.jar
COPY /APIGatewayService/target/*.jar gateway.jar
COPY /AuthService/target/*.jar authen.jar
COPY /CrudService/target/*.jar crud.jar
COPY /PostService/target/*.jar post.jar
COPY /CommentService/target/*.jar comment.jar

CMD ["java","-jar","/registry.jar"]
# CMD ["java","-jar","/gateway.jar", "&"]


