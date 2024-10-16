plugins {
    id("alio.android.application")
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

    implementation(project(":core:navigation"))
    implementation(project(":feature:news-feed"))
    implementation(project(":feature:news-detail"))

    implementation(libs.androidx.navigation)
    
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
}
