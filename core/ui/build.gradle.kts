plugins {
    id("alio.android.library")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.skogkatt.ui"

    buildFeatures {
        compose = true
    }
}

dependencies {
    val bom = libs.androidx.compose.bom
    implementation(platform(bom))
    implementation(libs.androidx.compose.ui)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
}
