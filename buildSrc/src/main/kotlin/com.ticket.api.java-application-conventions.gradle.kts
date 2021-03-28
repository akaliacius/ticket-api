plugins {
    id("com.ticket.api.java-common-conventions")
    application
}

application {
    applicationDefaultJvmArgs = listOf("--enable-preview")
}
