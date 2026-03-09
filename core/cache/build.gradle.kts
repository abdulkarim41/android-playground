plugins {
    alias(libs.plugins.iamkarim.android.library)
    alias(libs.plugins.iamkarim.android.hilt)
    alias(libs.plugins.iamkarim.android.room)
}

android {
    namespace = "com.abdulkarim.cache"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}