plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
}

android {
    compileSdkVersion(32)
    buildToolsVersion = "30.0.3"

    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(32)


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }

    namespace = "io.stipop"
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    //noinspection GradleDependency
    implementation(kotlin("stdlib"))
    //noinspection GradleDependency,GradleDependency
    implementation(kotlin("stdlib"))
    //noinspection GradleDependency,GradleDependency
    implementation("androidx.core:core-ktx:1.5.0")
    //noinspection GradleDependency,GradleDependency
    implementation("androidx.appcompat:appcompat:1.3.0")
    //noinspection GradleDependency
    implementation("com.google.android.material:material:1.3.0")
    //noinspection GradleDynamicVersion
    testImplementation("junit:junit:4.+")
    //noinspection GradleDependency
    //noinspection GradleDependency
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    //noinspection GradleDependency
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    implementation("com.github.bumptech.glide:glide:4.12.0")

}