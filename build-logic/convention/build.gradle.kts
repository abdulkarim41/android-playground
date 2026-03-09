import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.abdulkarim.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationConvention") {
            id = libs.plugins.iamkarim.android.application.get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibraryConvention") {
            id = libs.plugins.iamkarim.android.library.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("jvmLibraryConvention") {
            id = libs.plugins.iamkarim.jvm.library.get().pluginId
            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("androidHiltConvention") {
            id = libs.plugins.iamkarim.android.hilt.get().pluginId
            implementationClass = "AndroidHiltConventionPlugin"
        }
//
//        register("androidRetrofitConvention") {
//            id = "iamkarim.android.retrofit"
//            implementationClass = "AndroidRetrofitConventionPlugin"
//        }
//
        register("androidFeatureConvention") {
            id = libs.plugins.iamkarim.android.feature.get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }
//
//        register("androidNavigationComponentConvention") {
//            id = "iamkarim.android.navigation"
//            implementationClass = "AndroidNavigationComponentConventionPlugin"
//        }
//
//        register("androidApplicationFirebaseConvention") {
//            id = "iamkarim.android.firebase"
//            implementationClass = "AndroidApplicationFirebaseConventionPlugin"
//        }
//
//        register("androidRoomConvention") {
//            id = "iamkarim.android.room"
//            implementationClass = "AndroidRoomConventionPlugin"
//        }
    }
}

