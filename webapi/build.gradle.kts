plugins {
    id("com.ticket.api.java-application-conventions")
    id("io.micronaut.application") version "1.0.5"
}

micronaut {
    version("2.4.1")
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.ticket.*")
    }
}

dependencies {
    implementation("io.micronaut:micronaut-runtime")
    implementation("javax.annotation:javax.annotation-api")
    implementation(project(":artist-service"))
    implementation(project(":venue-service"))
    implementation(project(":event-service"))
}

application {
    mainClass.set("com.ticket.webapi.WebApi")
}
