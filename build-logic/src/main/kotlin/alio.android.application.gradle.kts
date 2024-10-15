import com.skogkatt.alio.configureComposeAndroid
import com.skogkatt.alio.configureKotlinAndroid

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.compose")
    id("alio.android.hilt")
}

android {
    configureKotlinAndroid(this)
    configureComposeAndroid(this)

    defaultConfig {
        targetSdk = 34
    }
}
