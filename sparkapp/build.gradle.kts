plugins {
    id("com.ticket.api.java-application-conventions")
}

dependencies {
    implementation(project(":artist-service"))
    implementation(project(":venue-service"))
    implementation(project(":event-service"))
}

application {
    mainClass.set("com.ticket.sparkapp.SparkApp")
}
