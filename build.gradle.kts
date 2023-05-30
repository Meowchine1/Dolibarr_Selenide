plugins {
    id("java")
    id("io.qameta.allure") version "2.9.6"
}

group = "org.example"
version = "1.0-SNAPSHOT"
val allureVersion = "2.21.0"
val junitVersion = "5.1.0"
val lombokVersion = "1.18.24"
val excelVersion = "5.2.3"


repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.projectlombok:lombok:1.18.22")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.testng:testng:7.7.1")
    //SELENIDE
    implementation("com.codeborne:selenide:6.11.2")

    //LOMBOK
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")

    // DATABASE CONNECTION
    implementation("mysql:mysql-connector-java:8.0.32")

    // EXCEL
    testImplementation("org.apache.poi:poi:$excelVersion")
    testImplementation("org.apache.poi:poi-ooxml:$excelVersion")

    //ALLURE
    runtimeOnly("org.aspectj:aspectjweaver:1.9.5")
    implementation("io.qameta.allure:allure-selenide:$allureVersion")
    implementation("io.qameta.allure:allure-java-commons:$allureVersion")
    implementation("io.github.bonigarcia:webdrivermanager:5.3.3")
    testImplementation("io.qameta.allure:allure-junit5:$allureVersion")
    testImplementation("io.qameta.allure:allure-testng:$allureVersion")
    testImplementation("io.qameta.allure:allure-comandline:$allureVersion")
    testImplementation("io.qameta.allure:allure-assertj:$allureVersion")


    //JAVAFAKER (RANDOM DATA)
    testImplementation("com.github.javafaker:javafaker:1.0.2")

    //FIXTURE FACTORY (DATA GENERATION)
    testImplementation("br.com.six2six:fixture-factory:3.1.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}