import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementBasicAndroid(){
//    android  kotlin core
    add("implementation", Dependencies.Androidx.CoreKtx)
//    android  Appcompat
    add("implementation", Dependencies.Androidx.AppCompat)
//    android  Material
    add("implementation", Dependencies.Androidx.Material)

    add("implementation", Dependencies.Androidx.ConstraintLayout)

}

fun DependencyHandler.implementDependencyInjection(){
    add("implementation", Dependencies.DependencyInjection.daggerHilt)
    add("kapt", Dependencies.DependencyInjection.daggerHiltCompiler)
    add("implementation", Dependencies.DependencyInjection.androidHiltViewModel)
    add("kapt", Dependencies.DependencyInjection.androidHiltCompiler)
}

fun DependencyHandler.implementAndroidX(){

    add("implementation", Dependencies.Lifecycle.viewModelKtx)
    add("implementation", Dependencies.Lifecycle.lifecycleRuntime)
    add("implementation", Dependencies.Lifecycle.lifecycleLiveData)
    add("implementation",Dependencies.Coroutine.coroutineCore)
    add("implementation",Dependencies.Coroutine.coroutineAndroid)
    add("implementation",Dependencies.Androidx.swipeRefreshLayout)
    add("implementation", Dependencies.Androidx.ActivityKtx)
    add("implementation", Dependencies.Androidx.FragmentKtx)
}

fun DependencyHandler.implementDataBase(){
    add("implementation", Dependencies.Storage.paging)
    add("implementation", Dependencies.Storage.preferenceDataStore)
    add("implementation", Dependencies.Storage.protoDataStore)
    add("implementation", Dependencies.Storage.protobuf)

}

fun DependencyHandler.implementNetwork(){
    add("implementation", Dependencies.Network.retrofit)
    add("implementation", Dependencies.Network.retrofitConverterGson)
    add("implementation", Dependencies.Network.googleGson)
    add("implementation", Dependencies.Network.okhttp)
    add("implementation", Dependencies.Network.loggingInterceptor)
    add("implementation", Dependencies.Network.glide)
    add("kapt", Dependencies.Network.glideCompiler)



}
fun DependencyHandler.implementTest() {
    add("testImplementation", Dependencies.Test.junit)
}

fun DependencyHandler.implementAndroidTest() {
    add("implementation",Dependencies.AndroidTest.testCore)
    add("androidTestImplementation", Dependencies.AndroidTest.espresso)
    add("androidTestImplementation", Dependencies.AndroidTest.extJunitKtx)

}




