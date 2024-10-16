import java.util.Properties

plugins {
    id("alio.android.library")
    id("alio.android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.skogkatt.network"

    defaultConfig {
        buildConfigField("String", "GUARDIAN_BASE_URL", getSecretsProperty("GUARDIAN_BASE_URL"))
        buildConfigField("String", "DEEPL_BASE_URL", getSecretsProperty("DEEPL_BASE_URL"))
        buildConfigField("String", "GOOGLE_TTS_BASE_URL", getSecretsProperty("GOOGLE_TTS_BASE_URL"))
        buildConfigField("String", "GUARDIAN_API_KEY", getSecretsProperty("GUARDIAN_API_KEY"))
        buildConfigField("String", "DEEPL_API_KEY", getSecretsProperty("DEEPL_API_KEY"))
        buildConfigField("String", "GOOGLE_TTS_API_KEY", getSecretsProperty("GOOGLE_TTS_API_KEY"))
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
}

fun getSecretsProperty(name: String): String {
    val propertiesFile = rootProject.file("secrets.properties")
    val properties = Properties()
    properties.load(propertiesFile.inputStream())
    return properties.getProperty(name)
}
