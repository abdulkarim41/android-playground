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
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
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

        register("androidRetrofitConvention") {
            id = libs.plugins.iamkarim.android.retrofit.get().pluginId
            implementationClass = "AndroidRetrofitConventionPlugin"
        }

        register("androidFeatureConvention") {
            id = libs.plugins.iamkarim.android.feature.get().pluginId
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("androidNavigationComponentConvention") {
            id = libs.plugins.iamkarim.android.navigation.get().pluginId
            implementationClass = "AndroidNavigationComponentConventionPlugin"
        }

        register("androidApplicationFirebaseConvention") {
            id = libs.plugins.iamkarim.android.firebase.get().pluginId
            implementationClass = "AndroidApplicationFirebaseConventionPlugin"
        }

        register("androidRoomConvention") {
            id = libs.plugins.iamkarim.android.room.get().pluginId
            implementationClass = "AndroidRoomConventionPlugin"
        }

        register("androidLocalStorageConvention") {
            id = libs.plugins.iamkarim.android.local.storage.get().pluginId
            implementationClass = "AndroidLocalStorageConventionPlugin"
        }
    }
}

