plugins {
    alias(libs.plugins.iamkarim.android.library)
}

android {
    namespace = "com.abdulkarim.securedatastore"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}