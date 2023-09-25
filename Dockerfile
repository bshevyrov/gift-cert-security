FROM openjdk:22-oraclelinux8
LABEL maintainer="bshevyrov@gmail.com"
COPY build/libs/gift-cert-advanced-0.0.1-SNAPSHOT.jar /home/sgift-cert-advanced-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","/home/gift-cert-advanced-0.0.1-SNAPSHOT.jar"]