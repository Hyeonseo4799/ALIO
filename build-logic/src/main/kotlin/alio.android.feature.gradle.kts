import com.skogkatt.alio.configureComposeAndroid

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
