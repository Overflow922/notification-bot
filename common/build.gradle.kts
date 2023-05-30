plugins {
    `java-library`
    id("java")
}

group = "com.iyuriy.notification"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

val versions: Map<String, String> by rootProject.extra
dependencies {
    compileOnly("org.projectlombok:lombok:${versions["lombok"]}")
    annotationProcessor("org.projectlombok:lombok:${versions["lombok"]}")
    testCompileOnly("org.projectlombok:lombok:${versions["lombok"]}")
    testAnnotationProcessor("org.projectlombok:lombok:${versions["lombok"]}")

    implementation("jakarta.validation:jakarta.validation-api:${versions["jakarta"]}")
    implementation("javax.persistence:javax.persistence-api:${versions["javax"]}")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${versions["junit"]}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${versions["junit"]}")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:${versions["junit"]}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${versions["junit"]}")
    testImplementation("org.assertj:assertj-core:${versions["assertj"]}")
    testImplementation("org.mockito:mockito-all:${versions["mockito"]}")
    testImplementation("org.mockito:mockito-junit-jupiter:${versions["mockito-junit"]}")

    implementation("org.modelmapper:modelmapper:${versions["model-mapper"]}")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}