plugins {
    id("alio.android.feature")
}

android {
    namespace = "com.skogkatt.news.feed"
}

dependencies {

    implementation(libs.landscapist.glide)

    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)

    implementation(libs.hilt.navigation.compose)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
}
