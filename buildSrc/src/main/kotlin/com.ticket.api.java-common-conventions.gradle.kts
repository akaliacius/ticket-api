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
    jcenter()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("--enable-preview")
}
