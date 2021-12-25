package com.oyelabs.marvel.universe.scrolling

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.oyelabs.marvel.universe.BaseActivity
import com.oyelabs.marvel.universe.R
import com.oyelabs.marvel.universe.databinding.ActivityScrollingBinding
import com.oyelabs.marvel.universe.main.api.source.dto.Result
import java.util.*
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import kotlin.math.abs


class ScrollingActivity : BaseActivity() {
    private lateinit var binding: ActivityScrollingBinding
    private var isDarkTheme: Boolean = false
    private lateinit var result:Result

    var TAG = "ScrollingActivityTAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isDarkTheme = isNightModeActive(applicationContext)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!isDarkTheme) {
                WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
                    true
            }
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scrolling)
        binding.activity = this

        val gson = Gson()
        result = gson.fromJson(intent.getStringExtra("character"), Result::class.java)

    }

    override fun onResume() {
        super.onResume()


        setSupportActionBar(binding.toolbar)
        binding.openAppFloating.setOnClickListener {  }

        setImageLoader()

        binding.toolbar.setNavigationIcon(R.drawable.ic_back)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


        binding.appBar.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                //  Collapsed
                binding.collapsingToolbarLayout.title = result.name
            } else {
                //Expanded
                binding.collapsingToolbarLayout.title = ""
            }
        })



    }

    private fun setImageLoader() {
        val newUrl = (result.thumbnail.path + "." + result.thumbnail.extension).replace("http", "https")
        Log.e(TAG , "Value  - ${newUrl}")
        Glide
            .with(applicationContext)
            .load(newUrl)
            .override(200, 200)
            .placeholder(R.drawable.marvel)
            .error(R.drawable.marvel)
            .into(binding.appImageImageView);


    }

}