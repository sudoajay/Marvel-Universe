// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        mavenCentral()

    }
    dependencies {
        classpath(Plugins.pluginBuildGradle)
        classpath(Plugins.pluginKotlinGradle)
        classpath(Plugins.pluginDaggerHilt)

    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}