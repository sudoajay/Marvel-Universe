/**
 * Plugins used in the project
 */
object Plugins {
    const val pluginBuildGradle =
        "com.android.tools.build:gradle:${Versions.Project.Android_Gradle_Plugin_version}"
    const val pluginKotlinGradle =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Project.kotlin_version}"
    const val  pluginDaggerHilt =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.Dependencies.Dagger_Hilt}"



//    const val pluginSafeArgs =
//        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.Dependencies.Navigation_Plugin_version}"
//    const val pluginDaggerHilt =
//        "com.google.dagger:hilt-android-gradle-plugin:${Versions.Dependencies.Dagger_Hilt}"
//    const val pluginKotlinSerialization =
//        "org.jetbrains.kotlin:kotlin-serialization:${Versions.Project.kotlin_version}"
}