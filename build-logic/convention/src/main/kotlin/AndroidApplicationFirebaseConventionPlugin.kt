import com.iamkarim.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationFirebaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.gms.google-services")
                apply("com.google.firebase.crashlytics")
            }

            dependencies {

                add(
                    "implementation",
                    platform(libs.findLibrary("firebase-bom").get())
                )

                add(
                    "implementation",
                    libs.findBundle("firebase").get()
                )
            }
        }
    }
}
