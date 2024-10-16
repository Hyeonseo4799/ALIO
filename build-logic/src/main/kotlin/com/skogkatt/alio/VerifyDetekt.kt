package com.skogkatt.alio

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureVerifyDetekt() {
    with(pluginManager) {
        apply("io.gitlab.arturbosch.detekt")
    }

    dependencies {
        "detektPlugins"(libs.findLibrary("detekt.formatting").get())
    }
}
