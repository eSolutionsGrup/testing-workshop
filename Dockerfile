FROM eclipse-temurin:17.0.6_10-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=80"
ENTRYPOINT ["java","-jar","/app.jar"]
