dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/app.versions.toml"))
        }
    }
}

rootProject.name = "app-build-logic"
include(":convention")
