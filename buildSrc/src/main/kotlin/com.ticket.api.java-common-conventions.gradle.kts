plugins {
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_15
}

tasks.withType<JavaCompile>{
    options.compilerArgs.add("--enable-preview")
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("io.micronaut.rxjava3:micronaut-rxjava3:1.1.1")
    implementation("io.reactivex.rxjava3:rxjava:3.0.11")
    implementation("org.slf4j:slf4j-simple:1.7.26")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("--enable-preview")
}
