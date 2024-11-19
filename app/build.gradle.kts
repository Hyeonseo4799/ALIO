plugins {
    id("alio.android.application")
    alias(libs.plugins.baselineprofile)
}

android {
    namespace = "com.skogkatt.alio"

    defaultConfig {
        applicationId = "com.skogkatt.alio"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(projects.core.navigation)
    implementation(projects.feature.newsFeed)
    implementation(projects.feature.newsDetail)

    implementation(libs.androidx.navigation)
    
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)

    baselineProfile(project(":baselineprofile"))
    implementation(libs.androidx.profileinstaller)
}
