plugins {
    alias(libs.plugins.iamkarim.android.library)
    alias(libs.plugins.iamkarim.android.hilt)
}

android {
    namespace = "com.abdulkarim.securedatastore"
}

dependencies {
    implementation(libs.androidx.security.crypto)
}