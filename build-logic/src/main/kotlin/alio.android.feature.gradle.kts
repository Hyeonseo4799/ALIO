import com.skogkatt.alio.configureComposeAndroid
import com.skogkatt.alio.libs

plugins {
    id("alio.android.library")
    id("alio.android.hilt")
    kotlin("plugin.compose")
}

android {
    configureComposeAndroid(this)

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":core:navigation"))

    implementation(libs.findLibrary("androidx-navigation").get())
}