plugins {
    alias(libs.plugins.iamkarim.android.library)
    alias(libs.plugins.iamkarim.android.hilt)
}

android {
    namespace = "com.abdulkarim.sharedpref"
}

dependencies {
    implementation(libs.androidx.core.ktx)
}