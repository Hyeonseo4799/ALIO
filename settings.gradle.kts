pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ALIO"

include(":app")
include(":core:network")
include(":core:data")
include(":core:model")
include(":core:ui")
include(":core:domain")
include(":core:navigation")
include(":feature:news-feed")
include(":feature:news-detail")

// Resolve build-logic rebuild failure
// https://issuetracker.google.com/issues/315023802
gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:testClasses"))
