FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY Numbergame11.class .

CMD ["java", "Numbergame11"]

