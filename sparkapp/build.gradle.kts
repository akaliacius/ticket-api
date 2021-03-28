plugins {
    id("com.ticket.api.java-application-conventions")
}

dependencies {
    implementation(project(":artist-service"))
}

application {
    mainClass.set("com.ticket.sparkapp.SparkApp")
}
