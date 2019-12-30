FROM openjdk:13

LABEL maintainer="Rodrigo Dantas"

USER root

EXPOSE 8080

# Update Container
RUN yum update -y
RUN yum install curl git -y

RUN mkdir /app