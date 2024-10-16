plugins {
    id("alio.android.library")
    id("alio.android.hilt")
}

android {
    namespace = "com.skogkatt.data"
}

dependencies {

    implementation(project(":core:network"))
    implementation(project(":core:model"))

    implementation(libs.paging.runtime)

    implementation(libs.kotlinx.datetime)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
}
