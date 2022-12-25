## Two-stage image builds (stage 1: exctracting spring boot fat jar into builder image)
FROM openjdk:11-jre-slim as build
USER root
WORKDIR /workspace/app/extracted
COPY /build/libs/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract --destination .

## Two-stage image builds (stage 2: deployment image)
FROM openjdk:11-jre-slim
EXPOSE 8082
WORKDIR ./app
ARG EXTRACTED=/workspace/app/extracted
COPY --from=build ${EXTRACTED}/dependencies/ ./
COPY --from=build ${EXTRACTED}/spring-boot-loader/ ./
COPY --from=build ${EXTRACTED}/snapshot-dependencies/ ./
COPY --from=build ${EXTRACTED}/application/ ./
ENTRYPOINT exec java $JAVA_OPTS org.springframework.boot.loader.JarLauncher
