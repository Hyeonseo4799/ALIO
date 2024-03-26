import com.skogkatt.alio.configureComposeAndroid

plugins {
    id("alio.android.library")
    id("alio.android.hilt")
}

android {
    configureComposeAndroid(this)

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}
