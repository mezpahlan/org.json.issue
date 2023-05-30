rootProject.name = "org.json Issue 283422335"

include(":app")
include(":module-b")
include(":module-a")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
