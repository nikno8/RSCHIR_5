# Используйте базовый образ Java
FROM openjdk:17-jdk-alpine

# Установите рабочую директорию
WORKDIR /app

# Скопируйте JAR-файл вашего приложения в контейнер
COPY build/libs/RschirPractice5-0.0.1-SNAPSHOT.jar app.jar

# Экспонируйте порт, на котором работает ваше приложение
EXPOSE 8081

# Установите переменные окружения для подключения к базе данных MySQL
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/market
ENV SPRING_DATASOURCE_USERNAME=nikno8
ENV SPRING_DATASOURCE_PASSWORD=nikitalaguT03

# Запустите приложение при запуске контейнера
CMD ["java", "-jar", "app.jar"]
