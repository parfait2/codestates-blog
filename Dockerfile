# base-image
FROM openjdk:11
#COPY에서 사용 경로변수
ARG JAR_FILE=build/libs/*.jar
#jar빌드 파일 도커 컨테이너로 복사
COPY ${JAR_FILE} app.jar
#jar 파일 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]