import java.util.Properties

plugins {
    id("alio.android.library")
    id("alio.android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.skogkatt.network"

    defaultConfig {

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "BASE_URL", getSecretsProperty("BASE_URL"))
        buildConfigField("String", "GUARDIAN_API_KEY", getSecretsProperty("GUARDIAN_API_KEY"))
        buildConfigField("String", "DEEPL_API_KEY", getSecretsProperty("DEEPL_API_KEY"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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
