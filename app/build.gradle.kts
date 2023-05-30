plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "org.json.issue"
        versionCode = 283422335
        versionName = "283422335"
        vectorDrawables.useSupportLibrary = true

        minSdk = 23
        targetSdk = 33
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }

    bundle {
        language {
            enableSplit = false
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }

        release {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "r8/main-config.pro"
            )
        }
    }
}

dependencies {
    coreLibraryDesugaring(libs.desugaringJdk)

    implementation(project(":module-a"))
    implementation(project(":module-b"))
}
