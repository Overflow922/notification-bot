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

val versions: Map<String, String> by rootProject.extra
dependencies {
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    api("org.apache.logging.log4j:log4j-api:${versions["log4j"]}")
    api("org.apache.logging.log4j:log4j-core:${versions["log4j"]}")

    implementation("org.modelmapper:modelmapper:${versions["model-mapper"]}")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${versions["junit"]}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${versions["junit"]}")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:${versions["junit"]}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${versions["junit"]}")
    testImplementation("org.assertj:assertj-core:${versions["assertj"]}")
    testImplementation("org.mockito:mockito-all:${versions["mockito"]}")
    testImplementation("org.mockito:mockito-junit-jupiter:${versions["mockito-junit"]}")

    compileOnly("org.projectlombok:lombok:${versions["lombok"]}")
    annotationProcessor("org.projectlombok:lombok:${versions["lombok"]}")
    testCompileOnly("org.projectlombok:lombok:${versions["lombok"]}")
    testAnnotationProcessor("org.projectlombok:lombok:${versions["lombok"]}")

    implementation("org.telegram:telegrambots:${versions["telegrambots"]}")
    implementation("org.telegram:telegrambotsextensions:${versions["telegrambots"]}")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}