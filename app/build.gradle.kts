//noinspection GradleDependency
//noinspection GradleDependency
//noinspection GradleDependency
//noinspection GradleDependency
plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("kotlin-android")
    id("ly.img.android.sdk")
}

val kotlin_version = "1.8.20"


imglyConfig {

    vesdk {
        enabled = true
        licencePath("vesdk_android_license")
    }

    pesdk {
        enabled = true
        licencePath("pesdk_android_license")
    }

    // TODO supportLibVersion("28.0.0")

    // Define the modules you are need
    modules {
        // Add all the UI modules you are need
        include("ui:video-trim")
        include("ui:core")
        include("ui:text")
        include("ui:focus")
        include("ui:frame")
        include("ui:brush")
        include("ui:filter")
        include("ui:camera")
        include("ui:sticker")
        include("ui:overlay")
        include("ui:transform")
        include("ui:adjustment")
        include("ui:text-design")

        // Add the serializer if you need
        include("backend:serializer")

        // Allow Background Encoding [Optional]
        include("backend:headless")

        // Add asset packs if you need
        include("assets:font-basic")
        include("assets:frame-basic")
        include("assets:filter-basic")
        include("assets:overlay-basic")
        include("assets:sticker-shapes")
        include("assets:sticker-emoticons")
        include("assets:sticker-animated")

        // Add animated sticker support
        include("backend:sticker-animated")

        // Add smart sticker support
        include("backend:sticker-smart")
    }

    modules {
        // Add all the UI modules you are need
        include("ui:core")
        include("ui:text")
        include("ui:focus")
        include("ui:frame")
        include("ui:brush")
        include("ui:filter")
        include("ui:camera")
        include("ui:sticker")
        include("ui:overlay")
        include("ui:transform")
        include("ui:adjustment")

        // Add the serializer if you need
        include("backend:serializer")

        // Add asset packs if you need
        include("assets:font-basic")
        include("assets:frame-basic")
        include("assets:filter-basic")
        include("assets:overlay-basic")
        include("assets:sticker-shapes")
        include("assets:sticker-emoticons")
    }

}

android {
    compileSdkVersion(33)
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.spacester.myfriend"
        minSdkVersion(23)
        targetSdkVersion(33)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )

            ndk {
                abiFilters.addAll(listOf("armeabi-v7a", "armeabi"))
            }
        }

        getByName("debug") {
            ndk {
                abiFilters.addAll(listOf("armeabi-v7a", "armeabi"))
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packagingOptions {
        resources {
            excludes += setOf("META-INF/NOTICE.md", "META-INF/LICENSE.md")
        }
    }
    namespace = "com.spacester.myfriend"
}

repositories {
    flatDir {
        dirs("../deepar")
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.4.1")
    //noinspection GradleDependency
    implementation("com.google.android.material:material:1.5.0")
    //noinspection GradleDependency,GradleDependency
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    //noinspection GradleDependency
    implementation("com.google.firebase:firebase-auth:21.0.1")
    //noinspection GradleDependency
    implementation("com.google.firebase:firebase-database:20.0.3")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    //noinspection GradleDependency
    implementation("com.google.firebase:firebase-storage:20.0.0")

    //noinspection GradleDependency,DifferentStdlibGradleVersion
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    //noinspection GradleDependency
    implementation("com.google.firebase:firebase-messaging:23.0.0")
    implementation(project(path = ":Stipop"))
    implementation(project(path = ":nativetemplates"))
    implementation(project(path = ":SimplePlacePicker-master"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    implementation("com.hbb20:ccp:2.5.4")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.github.tylersuehr7:social-text-view:1.0.0")
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("com.iceteck.silicompressorr:silicompressor:2.2.4")

    implementation("com.googlecode.mp4parser:isoparser:1.0.6") {
        exclude(group = "org.aspectj", module = "aspectjrt")
    }
    implementation("com.hendraanggrian.appcompat:socialview:0.3-rc1")
    implementation("com.mapbox.mapboxsdk:mapbox-android-plugin-places-v9:0.12.0")
    implementation("androidx.browser:browser:1.4.0")

    implementation("com.github.JagarYousef:ChatVoicePlayer:1.1.0")
    implementation("com.makeramen:roundedimageview:2.3.0")
    implementation("com.github.pgreze:android-reactions:1.6")
    implementation("com.github.chrisbanes:PhotoView:2.3.0")

    implementation("com.google.android.gms:play-services-places:17.0.0")
    //noinspection GradleDependency
    implementation("com.devlomi.record-view:record-view:2.0.1")
    implementation("com.wang.avi:library:2.1.3")
    //noinspection GradleDependency
    implementation("io.agora.rtc:full-sdk:3.3.1")

    //noinspection GradleDependency
    implementation("org.slf4j:slf4j-api:1.7.30")
    implementation("com.github.tony19:logback-android-core:1.1.1-4")


    implementation("com.github.tony19:logback-android-classic:1.1.1-4") {
        exclude(group = "com.google.android", module = "android")
    }

    //noinspection GradleDependency
    androidTestImplementation("com.jayway.android.robotium:robotium-solo:5.6.3")
    androidTestImplementation("com.android.support.test:rules:1.0.2")
    implementation("org.jitsi.react:jitsi-meet-sdk:6.0.0") {
        isTransitive = true
    }
    //noinspection GradleDependency
    implementation(files("libs/YouTubeAndroidPlayerApi.jar"))

    //noinspection GradleDependency
    implementation("com.dailymotion.dailymotion-sdk-android:sdk:0.2.6")
    implementation(files("../deepar/deepar.aar"))
    implementation("com.github.shts:StoriesProgressView:3.0.0")

    implementation("com.google.android.gms:play-services-location:19.0.1")

    implementation("com.android.volley:volley:1.2.1")

    implementation("com.google.code.gson:gson:2.8.9")
    //noinspection GradleDependency,GradleDependency
    implementation("com.google.firebase:firebase-iid:21.1.0")

    //noinspection GradleDependency
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version")
    //noinspection GradleDependency
    implementation("com.github.bumptech.glide:glide:4.12.0")

    androidTestImplementation("junit:junit:4.13.2")
    //noinspection GradleDependency
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    //noinspection GradleDependency
    implementation("com.google.android.gms:play-services-ads:19.8.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.1")
    //noinspection GradleDependency,GradleDependency
    implementation("com.googlecode.json-simple:json-simple:1.1.1")
    //noinspection GradleDependency
    configurations {
        implementation.get()
                .exclude(mapOf("group" to "junit", "module" to "junit"))
    }
    //noinspection GradleDependency
    implementation("com.github.pgreze:android-reactions:1.5.1")
    implementation("com.giphy.sdk:ui:2.1.12")
    //noinspection GradleDependency
    implementation("com.github.iammert:CameraVideoButton:0.2")
    implementation("com.github.nguyencse:urlembeddedview:1.0.2")
    implementation("com.sun.mail:android-mail:1.6.7")
    implementation("com.sun.mail:android-activation:1.6.7")
}