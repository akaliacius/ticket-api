plugins {
    id("com.ticket.api.java-application-conventions")
}

dependencies {
    implementation(project(":artist-service"))
    implementation(project(":venue-service"))
}

application {
    mainClass.set("com.ticket.sparkapp.SparkApp")
}
