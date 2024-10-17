# Строим приложение
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /opt/app

# Копируем все файлы проекта
COPY . .

# Устанавливаем зависимости и собираем приложение
RUN chmod +x mvnw && ./mvnw clean install -DskipTests

# Запускаем приложение
FROM eclipse-temurin:21-jre-jammy
WORKDIR /opt/app
EXPOSE 8084

# Копируем собранный jar файл
COPY --from=builder /opt/app/target/*.jar /opt/app/app.jar

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]
