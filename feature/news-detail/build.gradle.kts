plugins {
    id("alio.android.feature")
}

android {
    namespace = "com.skogkatt.news.detail"
}

dependencies {
    implementation(project(":core:media"))

    implementation(libs.landscapist.glide)

    implementation(libs.hilt.navigation.compose)

    implementation(libs.androidx.media3.exoplayer)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
}
