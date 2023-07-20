plugins {
    `kotlin-dsl`
}

group = "app.build.logic"

java {
    sourceCompatibility = JavaVersion.toVersion(libs.versions.javaCompatibility.get())
    targetCompatibility = JavaVersion.toVersion(libs.versions.javaCompatibility.get())
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("buildModule") {
            id = "build.module"
            implementationClass = "BuildConventionPlugin"
        }
        register("kotlinModule") {
            id = "kotlin.module"
            implementationClass = "KotlinModuleConventionPlugin"
        }
        register("androidModule") {
            id = "android.module"
            implementationClass = "AndroidModuleConventionPlugin"
        }
    }
}