plugins {
    id("com.ticket.api.java-common-conventions")
    application
}

dependencies {
    implementation("com.sparkjava:spark-core:2.9.3")
}

application {
    applicationDefaultJvmArgs = listOf("--enable-preview")
}
