
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

class AndroidModuleConventionPlugin: Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        pluginManager.apply("com.android.library")
        pluginManager.apply("kotlin-android")
        pluginManager.apply(catalog.plugin("kotlin-serialization"))

        val library = extensions.getByType<LibraryExtension>()

        library.namespace = namespace()
        library.compileSdk = catalog.version(name = "androidCompileSdk").displayName.toInt()
        library.compileOptions {
            sourceCompatibility = JavaVersion.toVersion(catalog.version("javaCompatibility").displayName)
            targetCompatibility = JavaVersion.toVersion(catalog.version("javaCompatibility").displayName)
        }
        library.packagingOptions {
            resources.excludes.add("META-INF/AL2.0**")
            resources.excludes.add("META-INF/LGPL2.1**")
            resources.excludes.add("META-INF/versions/**")
        }
        library.kotlinOptions {
            jvmTarget = catalog.version("javaCompatibility").displayName
        }

        library.defaultConfig.minSdk = catalog.version(name = "androidMinSdk").displayName.toInt()
        library.defaultConfig.targetSdk = catalog.version(name = "androidTargetSdk").displayName.toInt()
        library.defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        library.defaultConfig.multiDexEnabled = true

        library.defaultConfig.vectorDrawables.useSupportLibrary = true

        dependencies {
            add("implementation", catalog.library("kotlinx.coroutines.core"))
            add("implementation", catalog.library("kotlinx.datetime"))
            add("implementation", catalog.library("kotlinx.serialization.json"))

            add("implementation", catalog.library("androidx.annotation"))
            add("implementation", catalog.library("androidx.appcompat"))
            add("implementation", catalog.library("androidx.core"))

            add("implementation", catalog.library("koin.android"))
            add("implementation", catalog.library("koin.android.compat"))
            add("implementation", catalog.library("koin.annotations"))

            add("testImplementation", catalog.library("koin.android.test"))
            add("testImplementation", catalog.library("kotlin.test"))
            add("testImplementation", catalog.library("kotlinx.coroutines.test"))

            add("androidTestImplementation", catalog.library("androidx.test.core.ktx"))
            add("androidTestImplementation", catalog.library("androidx.test.rules"))
            add("androidTestImplementation", catalog.library("androidx.test.runner"))

            add("androidTestImplementation", catalog.library("koin.android.test"))
            add("androidTestImplementation", catalog.library("kotlin.test"))
            add("androidTestImplementation", catalog.library("kotlinx.coroutines.test"))
        }
    }
}


private fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}
