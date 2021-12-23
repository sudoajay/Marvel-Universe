plugins {
    id("com.android.application")
    kotlin("android")
    id ("kotlin-kapt")

}

android {
    compileSdk =AppConfig.compileSdkVersion
    buildToolsVersion =AppConfig.buildToolsVersion

    defaultConfig {
        applicationId = AppConfig.packageName
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility =  JavaVersion.VERSION_1_8
        targetCompatibility =  JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
    }

    // You can change the value to override the default behavior.
    buildFeatures {
        // Determines whether to support View Binding.
        // Note that the viewBinding.enabled property is now deprecated.
        viewBinding = true
        // Determines whether to support Data Binding.
        // Note that the dataBinding.enabled property is now deprecated.
        dataBinding = true

    }


}

dependencies {

    implementBasicAndroid()
//    implementDependencyInjection()
    implementAndroidX()
//    implementDataBase()
//    implementNetwork()
    implementTest()
    implementAndroidTest()
}