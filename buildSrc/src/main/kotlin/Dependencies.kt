class Dependencies {
    object Androidx {
        const val AppCompat = "androidx.appcompat:appcompat:${Versions.MainDependencies.AppCompat}"
        const val Material = "com.google.android.material:material:${Versions.MainDependencies.Material}"
        const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.MainDependencies.ConstraintLayout}"
        const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.Dependencies.SwipeRefreshLayout}"
        const val webKit = "androidx.webkit:webkit:${Versions.Dependencies.WebKit}"
        const val CoreKtx = "androidx.core:core-ktx:${Versions.MainDependencies.CoreKtx}"
        const val ActivityKtx = "androidx.activity:activity-ktx:${Versions.Dependencies.ActivityKTX}"
        const val FragmentKtx = "androidx.fragment:fragment-ktx:${Versions.Dependencies.FragmentKTX}"
    }
    object DependencyInjection {
        const val  daggerHilt = "com.google.dagger:hilt-android:${Versions.Dependencies.Dagger_Hilt}"
        const val daggerHiltCompiler =
            "com.google.dagger:hilt-android-compiler:${Versions.Dependencies.Dagger_Hilt}"
        const val androidHiltViewModel =
            "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.Dependencies.Android_Hilt}"
        const val androidHiltCompiler =
            "androidx.hilt:hilt-compiler:${Versions.Dependencies.Android_Hilt}"
    }
    object Lifecycle {
        const val viewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Dependencies.Lifecycle}"
        const val lifecycleRuntime =
            "androidx.lifecycle:lifecycle-runtime:${Versions.Dependencies.Lifecycle}"
    }

    object Coroutine {
        const val coroutineCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Dependencies.Coroutine}"
        const val coroutineAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Dependencies.Coroutine}"
    }
    object Storage {
        const val RoomRuntime = "androidx.room:room-runtime:${Versions.Dependencies.Room}"
        const val RoomCompiler = "androidx.room:room-compiler:${Versions.Dependencies.Room}"
        const val RoomKtx = "androidx.room:room-ktx:${Versions.Dependencies.Room}"
        const val RoomPaging3 = "androidx.room:room-paging:${Versions.Dependencies.RoomPaging3}"
        const val paging ="androidx.paging:paging-runtime:${Versions.Dependencies.Paging}"
        const val firebaseDatabase = "com.google.firebase:firebase-database-ktx:${Versions.Dependencies.FirebaseDatabase}"
        const val preferenceDataStore = "androidx.datastore:datastore-preferences:${Versions.Dependencies.PreferencesDataStore}"
        const val protoDataStore = "androidx.datastore:datastore-core:${Versions.Dependencies.ProtoDataStore}"
        const val protobuf = "com.google.protobuf:protobuf-javalite:${Versions.Dependencies.Protobuf}"
    }

    object Network {
//        Retrofit
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Dependencies.Retrofit}"
        const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.Dependencies.Retrofit}"
        const val retrofitConverterJackson = "com.squareup.retrofit2:converter-jackson:${Versions.Dependencies.Retrofit}"
        const val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.Dependencies.Retrofit}"

//    Ok HTTP
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.Dependencies.Okhttp}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.Dependencies.Okhttp}"

        // Google gson
        const val googleGson = "com.google.code.gson:gson:${Versions.Dependencies.GoogleGson}"

//  Jackson json
const val jacksonCore =
    "com.fasterxml.jackson.core:jackson-core:${Versions.Dependencies.JacksonJson}"
        const val jacksonAnnotation =
            "com.fasterxml.jackson.core:jackson-annotations:${Versions.Dependencies.JacksonJson}"
        const val jacksonDataBind =
            "com.fasterxml.jackson.core:jackson-databind:${Versions.Dependencies.JacksonJson}"

        //  Moshi Json
        const val moshiCore = "com.squareup.moshi:moshi:${Versions.Dependencies.MoshiJson}"
        const val moshiCodegen =
            "com.squareup.moshi:moshi-kotlin-codegen:${Versions.Dependencies.MoshiJson}"

        //        Glide
        const val glide = "com.github.bumptech.glide:glide:${Versions.Dependencies.Glide}"
        const val glideCompiler =
            "com.github.bumptech.glide:compiler:${Versions.Dependencies.Glide}"

        //        Picasso
        const val picasso = "com.squareup.picasso:picasso:${Versions.Dependencies.Picasso}"
    }
    object Test {
        const val junit = "junit:junit:${Versions.Dependencies.Junit}"
    }

    object AndroidTest {
        const val testCore = "androidx.test:core-ktx:${Versions.Dependencies.TestCore}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.Dependencies.EspressoCore}"
        const val extJunitKtx = "androidx.test.ext:junit-ktx:${Versions.Dependencies.extJunitKtx}"

    }

}