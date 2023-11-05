FROM openjdk:22-jdk-slim
ADD target/demo.spring.security-0.0.1.jar demo.spring.security-0.0.1.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","demo.spring.security-0.0.1.jar"]