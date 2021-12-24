package com.oyelabs.marvel.universe

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.oyelabs.marvel.universe.main.proto.ProtoManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var protoManager: ProtoManager
    private var TAG = "BaseActivityTAG"

    var currentTheme: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDataFromProtoDatastoreAndSetTheme()
    }

    private fun getDataFromProtoDatastoreAndSetTheme() {

        protoManager.dataStoreStatePreferences.data.asLiveData().observe(this) {
            lifecycleScope.launch {
                if (it.currentTheme.isEmpty())
                    protoManager.setDefaultValue()
            }
            currentTheme = it.currentTheme
            setAppTheme(currentTheme!!)
            Log.e(TAG , currentTheme + "Here we go  -  + - " + it.currentTheme + " --- 2" )
        }

    }

    fun isNightModeActive(context: Context): Boolean {
        val defaultNightMode = AppCompatDelegate.getDefaultNightMode()
        if (defaultNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            return true
        }
        if (defaultNightMode == AppCompatDelegate.MODE_NIGHT_NO) {
            return false
        }
        val currentNightMode = (context.resources.configuration.uiMode
                and Configuration.UI_MODE_NIGHT_MASK)
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> return false
            Configuration.UI_MODE_NIGHT_YES -> return true
            Configuration.UI_MODE_NIGHT_UNDEFINED -> return false
        }
        return false
    }


    private fun setAppTheme(currentTheme: String) {
        when (currentTheme) {
            getString(R.string.off_text) -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            getString(
                R.string.automatic_at_sunset_text
            ) -> setDarkMode(isSunset())
            getString(
                R.string.set_by_battery_saver_text
            ) -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                    )

                } else {

                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }

            }
            getString(
                R.string.system_default_text
            ) -> {

                AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                )
            }
            else -> {

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

    }
    private fun setDarkMode(isDarkMode: Boolean) {
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
    private fun isSunset(): Boolean {
        val rightNow: Calendar = Calendar.getInstance()
        val hour: Int = rightNow.get(Calendar.HOUR_OF_DAY)
        return hour < 6 || hour > 18
    }


}