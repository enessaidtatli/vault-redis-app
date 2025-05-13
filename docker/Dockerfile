# Build aşaması
FROM maven:3.8.4-openjdk-17-slim AS build

# UTF-8 ve lokal ayarları (FROM'dan sonra gelmeli)
ENV LANG C.UTF-8
ENV LC_ALL C.UTF-8

WORKDIR /app

# 1. Önce pom.xml'i kopyala ve bağımlılıkları indir
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 2. Kaynak kodları kopyala
COPY src ./src

# 3. Paketleme işlemi
RUN mvn clean package \
    -DskipTests \
    -Dmaven.test.skip=true \
    -Dmaven.resources.skip=true \
    -Dstart-class=io.github.enessaidtatli.vaultredisapp.VaultRedisAppApplication

# Runtime aşaması
FROM openjdk:17-slim

# UTF-8 ayarlarını runtime için de ekleyin
ENV LANG C.UTF-8
ENV LC_ALL C.UTF-8

WORKDIR /app

# 4. JAR dosyasını kopyala
COPY --from=build /app/target/*.jar app.jar

# 5. Sağlık kontrolü ve port ayarları
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]