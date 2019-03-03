plugins {
    java
}

group = "be.inniger"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "5.2.1"
    distributionType = Wrapper.DistributionType.ALL
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}
