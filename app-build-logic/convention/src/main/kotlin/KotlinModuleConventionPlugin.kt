import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class KotlinModuleConventionPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        pluginManager.apply {
            apply(catalog.plugin("kotlin-jvm"))
            apply(catalog.plugin("kotlin-serialization"))
        }

        dependencies {
            add("implementation", catalog.library("koin.annotations"))
            add("implementation", catalog.library("koin.core"))

            add("implementation", catalog.library("kotlinx.coroutines.core"))
            add("implementation", catalog.library("kotlinx.datetime"))
            add("implementation", catalog.library("kotlinx.serialization.json"))

            add("testImplementation", catalog.library("koin.test"))
            add("testImplementation", catalog.library("kotlin.test"))
            add("testImplementation", catalog.library("kotlinx.coroutines.test"))
        }

        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = JavaVersion.toVersion(catalog.version("javaCompatibility").displayName)
            targetCompatibility = JavaVersion.toVersion(catalog.version("javaCompatibility").displayName)
        }
    }
}

private val org.gradle.api.Project.`sourceSets`: org.gradle.api.tasks.SourceSetContainer get() =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("sourceSets") as org.gradle.api.tasks.SourceSetContainer

/**
 * Configures the [sourceSets][org.gradle.api.tasks.SourceSetContainer] extension.
 */
private fun org.gradle.api.Project.`sourceSets`(configure: Action<SourceSetContainer>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("sourceSets", configure)
