pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
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
