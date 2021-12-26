package com.oyelabs.marvel.universe.scrolling

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.oyelabs.marvel.universe.BaseActivity
import com.oyelabs.marvel.universe.R
import com.oyelabs.marvel.universe.databinding.ActivityScrollingBinding
import com.oyelabs.marvel.universe.helper.Toaster
import com.oyelabs.marvel.universe.scrolling.model.CharacterInfo
import com.oyelabs.marvel.universe.scrolling.ui.repository.ComicPagingAdapterGson
import com.oyelabs.marvel.universe.scrolling.ui.viewModel.ScrollingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.abs

@AndroidEntryPoint
class ScrollingActivity : BaseActivity() {

    val viewModel: ScrollingViewModel by viewModels()

    @Inject
    lateinit var comicPagingAdapterGson: ComicPagingAdapterGson


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
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this


        characterInfo = CharacterInfo(
            (intent.getStringExtra("id")?.toLong() ?: 1),
            intent.getStringExtra("name") ?: "error",
            intent.getStringExtra("imageUrl") ?: "error",
            intent.getStringExtra("url") ?: "error"
        )
        Log.e(TAG, "Id  - ${characterInfo.id}   id - ${intent.getStringExtra("id")}")
        viewModel.id = characterInfo.id.toInt()

    }

    override fun onResume() {


        setSupportActionBar(binding.toolbar)
        binding.openAppFloating.setOnClickListener {
            openUrl()

        }

        setImageLoader()

        binding.toolbar.setNavigationIcon(R.drawable.ic_back)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.toolbar.navigationIcon?.mutate()?.let {
            it.setTint(
                ContextCompat.getColor(applicationContext, R.color.headingNormalTextColor)
            )
            binding.toolbar.navigationIcon = it
        }

        binding.collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor)
        binding.collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor)


        binding.appBar.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                //  Collapsed
                binding.collapsingToolbarLayout.title = characterInfo.name
            } else {
                //Expanded
                binding.collapsingToolbarLayout.title = ""
            }
        })

        setRecyclerView()

        super.onResume()
    }

    private fun setRecyclerView() {
        binding.include.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.include.recyclerView.adapter = comicPagingAdapterGson

        comicPagingAdapterGson.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.NotLoading && comicPagingAdapterGson.itemCount < 1
                && loadState.append.endOfPaginationReached
            )
                if (!viewModel.noData) {
                    Toaster.showToast(
                        context = applicationContext,
                        getString(R.string.characterIsEmpty_text)
                    )
                    viewModel.noData = true

                } else viewModel.noData = false
        }

        callData()

    }

    private fun callData() {
        CoroutineScope(Dispatchers.IO).launch {
            lifecycleScope.launch {
                viewModel.getPagingGsonSourceWithNetwork()
                    .collectLatest { pagingData ->
                        Log.e(TAG, "Here the value")
                        comicPagingAdapterGson.submitData(pagingData = pagingData)
                    }
            }
            delay(2000)
            viewModel.hideProgress.postValue(true)
        }
    }

    private fun openUrl() {
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
            .placeholder(R.drawable.marvel)
            .error(R.drawable.marvel)
            .into(binding.appImageImageView);


    }

}