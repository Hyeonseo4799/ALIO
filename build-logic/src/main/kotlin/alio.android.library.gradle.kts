import com.skogkatt.alio.configureKotlinAndroid

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    configureKotlinAndroid(this)

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}
