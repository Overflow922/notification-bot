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
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    implementation("org.liquibase:liquibase-core")
    runtimeOnly("org.postgresql:postgresql")

    compileOnly("org.projectlombok:lombok:${versions["lombok"]}")
    annotationProcessor("org.projectlombok:lombok:${versions["lombok"]}")
    testCompileOnly("org.projectlombok:lombok:${versions["lombok"]}")
    testAnnotationProcessor("org.projectlombok:lombok:${versions["lombok"]}")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${versions["junit"]}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${versions["junit"]}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("org.hibernate.validator:hibernate-validator:${versions["hibernate"]}")
    implementation("org.modelmapper:modelmapper:${versions["model-mapper"]}")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}