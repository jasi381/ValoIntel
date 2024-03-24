plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinKsp)
    alias(libs.plugins.hiltAndroid)
    id("kotlin-parcelize")
}

android {
    namespace = "com.jasmeet.valorantapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jasmeet.valorantapi"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //Okhttp
    implementation (libs.okhttp)
    implementation(libs.logging.interceptor)

    //Gson
    implementation (libs.gson)

    //livedata
    implementation(libs.androidx.runtime.livedata)

    //coil compose
    implementation(libs.coil.compose)
    implementation(libs.coil.gif)

    //navigation & viewModel
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.runtime.livedata)

    //material icons
    implementation(libs.materialIcons)



    //hilt
    implementation(libs.hiltAndroid)
    implementation(libs.androidx.palette.ktx)
    ksp(libs.hiltCompiler)
    ksp(libs.hiltCompilerKapt)
    implementation (libs.androidx.hilt.navigation.compose)


    //google fonts
    implementation(libs.googleFonts)

    //room
    implementation(libs.room)
    implementation(libs.roomKtx)
    ksp(libs.roomCompiler)

    //Media3
    implementation(libs.media3Ui)
    implementation(libs.media3Exoplayer)

    implementation ("com.google.accompanist:accompanist-navigation-material:0.35.0-alpha")

    //appComponents
    implementation(project(mapOf("path" to ":AppComponents")))
}