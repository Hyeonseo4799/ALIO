plugins {
    id("alio.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.skogkatt.navigation"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
}
