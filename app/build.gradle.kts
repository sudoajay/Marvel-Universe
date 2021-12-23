import com.google.protobuf.gradle.*

plugins {
    id("com.android.application")
    kotlin("android")
    id ("kotlin-kapt")
    //    Hilt
    id("dagger.hilt.android.plugin")
    id("kotlin-android")
    id ("kotlin-parcelize")
//    ProtoBuf
    id ("com.google.protobuf" ) version "0.8.18"

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
        multiDexEnabled = true
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
    implementDependencyInjection()
    implementAndroidX()
    implementDataBase()
//    implementNetwork()
    implementTest()
    implementAndroidTest()
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.19.1"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins{
                create("java") {
                    option("lite")
                }
            }
        }
    }

    kapt {
        correctErrorTypes = true
    }

}