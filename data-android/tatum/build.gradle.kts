plugins {
    id("android.module")
}

dependencies {
    implementation(app.ktor.client.core)
    implementation(app.ktor.client.android)
    implementation(app.ktor.client.content.negotiation)
    implementation(app.ktor.serialization.kotlinx.json)
}