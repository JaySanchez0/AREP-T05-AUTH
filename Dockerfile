FROM openjdk:8
WORKDIR /authapp
ENV PORT 6000
COPY . /authapp
CMD ["java","-cp","./target/classes:./target/dependency/*","com.authApp.App"]