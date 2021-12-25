package com.oyelabs.marvel.universe.scrolling

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.oyelabs.marvel.universe.BaseActivity
import com.oyelabs.marvel.universe.R
import com.oyelabs.marvel.universe.databinding.ActivityScrollingBinding
import com.oyelabs.marvel.universe.scrolling.model.CharacterInfo
import java.util.*
import kotlin.math.abs


class ScrollingActivity : BaseActivity() {
    private lateinit var binding: ActivityScrollingBinding
    private var isDarkTheme: Boolean = false
    private lateinit var characterInfo: CharacterInfo

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


        characterInfo = CharacterInfo(
            Integer.parseInt(intent.getStringExtra("id")?:"1"), intent.getStringExtra("name")?:"error",
            intent.getStringExtra("imageUrl")?:"error", intent.getStringExtra("url")?:"error"
        )

    }

    override fun onResume() {
        super.onResume()


        setSupportActionBar(binding.toolbar)
        binding.openAppFloating.setOnClickListener {
            openUrl()

        }

        setImageLoader()

        binding.toolbar.setNavigationIcon(R.drawable.ic_back)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


        binding.appBar.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                //  Collapsed
                binding.collapsingToolbarLayout.title = characterInfo.name
            } else {
                //Expanded
                binding.collapsingToolbarLayout.title = ""
            }
        })


    }

    private fun setRecyclerView() {
        binding.include.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.include.recyclerView.setHasFixedSize(true)
//        binding.include.recyclerView.adapter = personPagingAdapterGson

    }

    private fun openUrl(){
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(characterInfo.url)
            )
        )
    }

    private fun setImageLoader() {
        val newUrl =
            (characterInfo.thumbnail)
        Log.e(TAG, "Value  - ${newUrl}")
        Glide
            .with(applicationContext)
            .load(newUrl)
            .override(200, 200)
            .placeholder(R.drawable.marvel)
            .error(R.drawable.marvel)
            .into(binding.appImageImageView);


    }

}