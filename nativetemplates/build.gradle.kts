plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdkVersion(32)

    defaultConfig {
        minSdkVersion(16)
        targetSdkVersion(32)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pgcfg"))
            consumerProguardFiles("proguard-rules.pgcfg")
        }
    }

    lintOptions {
        resourcePrefix("gnt_")
    }
    namespace = "com.google.android.ads.nativetemplates"
}



dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //noinspection GradleDependency
    implementation("androidx.appcompat:appcompat:1.2.0")
    //noinspection GradleDependency
    testImplementation("junit:junit:4.13.1")
    //noinspection GradleDependency
    //noinspection GradleDependency
    androidTestImplementation("androidx.test:runner:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    //noinspection GradleDependency
    implementation("com.google.android.gms:play-services-ads:19.7.0")
    //noinspection GradleDependency
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
}