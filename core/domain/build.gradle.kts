plugins {
    id("alio.android.library")
    id("alio.android.hilt")
}

android {
    namespace = "com.skogkatt.domain"
}

dependencies {

    implementation(project(":core:data"))
    implementation(project(":core:model"))

    implementation(libs.paging.runtime)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
}
