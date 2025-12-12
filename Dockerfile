FROM openjdk:17-jdk-slim

WORKDIR /app

COPY Numbergame11.java .
COPY input.txt .

RUN javac Numbergame11.java

CMD ["java", "Numbergame11"]

