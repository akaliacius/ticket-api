plugins {
    id("com.ticket.api.java-library-conventions")
    id("io.micronaut.library") version "1.0.5"
}

micronaut {
    version("2.4.1")
}

dependencies {
    implementation(project(":common"))
}