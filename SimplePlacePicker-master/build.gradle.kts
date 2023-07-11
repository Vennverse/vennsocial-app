plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    compileSdkVersion(32)
    buildToolsVersion = "30.0.3"

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(32)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
        }
    }
    namespace = "com.essam.simpleplacepicker"
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.appcompat:appcompat:1.4.1")
    //noinspection GradleDependency
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.google.android.gms:play-services-maps:18.0.2")
    implementation("com.google.android.gms:play-services-location:19.0.1")
    //noinspection GradleDependency
    implementation("com.google.android.libraries.places:places:2.5.0")
    implementation("com.github.mancj:MaterialSearchBar:0.8.2")
    implementation("com.skyfishjy.ripplebackground:library:1.0.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

}