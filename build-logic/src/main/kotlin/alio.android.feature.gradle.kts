import com.skogkatt.alio.configureComposeAndroid
import com.skogkatt.alio.libs

plugins {
    id("alio.android.library")
    id("alio.android.hilt")
    kotlin("plugin.compose")
}

android {
    configureComposeAndroid(this)
}

dependencies {
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))

    implementation(libs.findLibrary("androidx-navigation").get())

    implementation(libs.findLibrary("orbit-viewmodel").get())
    implementation(libs.findLibrary("orbit-compose").get())
}
