plugins {
    alias(app.plugins.android.application) apply false
    alias(app.plugins.android.library) apply false
    alias(app.plugins.google.play.services) apply false
    alias(app.plugins.kotlin.android) apply false
    alias(app.plugins.imgly.sdk) apply false
}

allprojects {
    project.configurations.all {
        resolutionStrategy {
            force("androidx.work:work-runtime:2.7.0")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}