import com.skogkatt.alio.configureComposeAndroid
import com.skogkatt.alio.configureKotlinAndroid

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    configureKotlinAndroid(this)
    configureComposeAndroid(this)

    defaultConfig {
        targetSdk = 34
    }
}
