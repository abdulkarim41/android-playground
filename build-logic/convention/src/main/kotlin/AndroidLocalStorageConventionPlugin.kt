

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidLocalStorageConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("implementation",project(":library:data-store"))
                add("implementation",project(":library:secure-data-store"))
                add("implementation",project(":library:sharedpref"))
            }
        }
    }
}