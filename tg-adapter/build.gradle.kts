plugins {
    `java-library`
    id("java")
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.iyuriy.notification"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {

    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    api("org.apache.logging.log4j:log4j-api:2.19.0")
    api("org.apache.logging.log4j:log4j-core:2.19.0")

    implementation("org.liquibase:liquibase-core")
    runtimeOnly("org.postgresql:postgresql")

    implementation("org.modelmapper:modelmapper:3.1.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("org.mockito:mockito-all:1.10.19")
    testImplementation("org.mockito:mockito-junit-jupiter:4.8.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")

    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    testCompileOnly("org.projectlombok:lombok:1.18.24")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.24")

    implementation("org.telegram:telegrambots:6.5.0")
    implementation("org.telegram:telegrambotsextensions:6.5.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}