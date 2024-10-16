import com.skogkatt.alio.configureKotlinAndroid

plugins {
    id("com.android.library")
    kotlin("android")
    id("alio.verify.detekt")
}

android {
    configureKotlinAndroid(this)

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}
