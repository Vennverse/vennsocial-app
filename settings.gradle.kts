pluginManagement {
    includeBuild("app-build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://artifactory.img.ly/artifactory/imgly")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven("https://www.jitpack.io")
        maven("https://artifactory.img.ly/artifactory/imgly")
        maven("https://github.com/jitsi/jitsi-maven-repository/raw/master/releases")
        flatDir {
            dirs("deepar")
        }
    }
    versionCatalogs {
        create("app") {
            from(files("gradle/app.versions.toml"))
        }
    }
}
rootProject.name = "Myfriend"

include(":Stipop")
include(":nativetemplates")
include(":deepar")
include(":app")
include(":SimplePlacePicker-master")

include(":data-android-coinbase")
project(":data-android-coinbase").projectDir = file("data-android/coinbase")
