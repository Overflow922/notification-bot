plugins {
    `java-library`
    id("java")
}
group = "com.iyuriy.notification"
version = "0.0.1=SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    api("org.apache.logging.log4j:log4j-api:2.19.0")
    api("org.apache.logging.log4j:log4j-core:2.19.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("org.mockito:mockito-all:1.10.19")
    testImplementation("org.mockito:mockito-junit-jupiter:4.8.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")

    implementation("org.telegram:telegrambots:6.1.0")
    implementation("org.telegram:telegrambotsextensions:6.1.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}