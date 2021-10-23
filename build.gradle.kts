import org.gradle.api.tasks.testing.logging.TestExceptionFormat.*
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    // Apply the java-library plugin to add support for Java Library
    `java-library`
    id("com.github.spotbugs") version "4.6.0"
    jacoco
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    mavenCentral()
}

dependencies {
    api("org.apache.logging.log4j:log4j-api:2.14.1")
    api("org.apache.logging.log4j:log4j-core:2.14.1")

    implementation("org.telegram:telegrambots:5.3.0")
    implementation("org.telegram:telegrambotsextensions:5.3.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.assertj:assertj-core:3.21.0")
}

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("gradle.plugin.com.github.spotbugs.snom:spotbugs-gradle-plugin:4.6.0")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

apply(plugin = "com.github.spotbugs")

tasks.test {
    useJUnitPlatform {}
    testLogging {
        events(FAILED, STANDARD_ERROR, SKIPPED)
        exceptionFormat = FULL
        showExceptions = true
        showCauses = true
    }

    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    dependsOn(tasks.jacocoTestCoverageVerification)
}


jacoco {
    toolVersion = "0.8.7"
    reportsDirectory.set(file("$buildDir/customJacocoReportDir"))
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(file("${buildDir}/jacocoHtml"))
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.95".toBigDecimal()
            }
        }
    }
}

//configurations.all {
//    resolutionStrategy {
//// Fail eagerly on version conflict (includes transitive dependencies)
//// e.g. multiple different versions of the same dependency (group and name are equal)
//        failOnVersionConflict()
//
//    }
//}
