plugins {
    java
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "de.dasshorty"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {

    // Database Integration
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    // Mails
    implementation("org.springframework.boot:spring-boot-starter-mail")

    // RestFul
    implementation("org.springframework.boot:spring-boot-starter-web")

    // WebSockets
    implementation("org.springframework.boot:spring-boot-starter-websocket")

    // Spring Tests
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // JDA
    implementation("net.dv8tion:JDA:5.0.0-beta.24")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
