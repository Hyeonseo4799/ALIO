plugins {
    id("alio.android.library")
    id("alio.android.hilt")
}

android {
    namespace = "com.skogkatt.media"
}

dependencies {

    implementation(libs.androidx.media3.exoplayer)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
}
