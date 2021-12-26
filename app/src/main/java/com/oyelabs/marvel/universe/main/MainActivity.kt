package com.oyelabs.marvel.universe.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.oyelabs.marvel.universe.BaseActivity
import com.oyelabs.marvel.universe.R
import com.oyelabs.marvel.universe.databinding.ActivityMainBinding
import com.oyelabs.marvel.universe.helper.Toaster
import com.oyelabs.marvel.universe.main.bottomSheet.DarkModeBottomSheet
import com.oyelabs.marvel.universe.main.bottomSheet.NavigationDrawerBottomSheet
import com.oyelabs.marvel.universe.main.ui.repository.CharacterPagingAdapterGson
import com.oyelabs.marvel.universe.main.ui.viewModel.MainViewModel
import com.oyelabs.marvel.universe.sendFeedback.SendFeedback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    val TAG = "MainActivityTAG"
    val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    private var isDarkTheme: Boolean = false
    private var doubleBackToExitPressedOnce = false

    @Inject
    lateinit var characterPagingAdapterGson: CharacterPagingAdapterGson

    @Inject
    lateinit var navigationDrawerBottomSheet: NavigationDrawerBottomSheet
    lateinit var darkModeBottomSheet: DarkModeBottomSheet


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isDarkTheme = isNightModeActive(applicationContext)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!isDarkTheme) {
                WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
                    true
            }
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewModel
        binding.activity = this
        binding.lifecycleOwner = this
    }

    override fun onResume() {
        super.onResume()

        darkModeBottomSheet = DarkModeBottomSheet(this)

        setReference()


    }

    private fun setReference() {

        //      Setup Swipe Refresh
        binding.swipeRefresh.setColorSchemeResources(R.color.colorAccent)
        binding.swipeRefresh.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(applicationContext, R.color.swipeBgColor)
        )

        binding.swipeRefresh.setOnRefreshListener {
            refreshData()
        }

        //         Setup BottomAppBar Navigation Setup
        binding.bottomAppBar.navigationIcon?.mutate()?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                it.setTint(
                    ContextCompat.getColor(applicationContext, R.color.colorAccent)
                )
            }
            binding.bottomAppBar.navigationIcon = it
        }
        setSupportActionBar(binding.bottomAppBar)


        setRecyclerView()

    }

    private fun setRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = characterPagingAdapterGson
        refreshData()


        characterPagingAdapterGson.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.NotLoading && characterPagingAdapterGson.itemCount < 1
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
    }

    private fun callData() {
        lifecycleScope.launch {
            viewModel.getPagingGsonSourceWithNetwork()
                .collectLatest { pagingData ->
                    characterPagingAdapterGson.submitData(pagingData)

                }
        }
    }

    private fun refreshData() {
        showProgressAndHideRefresh()
        CoroutineScope(Dispatchers.IO).launch {
            callData()
            delay(2000)
            viewModel.hideProgress.postValue(true)
        }


    }

    private fun showProgressAndHideRefresh() {
        if (binding.swipeRefresh.isRefreshing)
            binding.swipeRefresh.isRefreshing = false
        viewModel.hideProgress.value = false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.bottom_toolbar_menu, menu)
        val actionSearch = menu.findItem(R.id.search_optionMenu)
        manageSearch(actionSearch)

        return super.onCreateOptionsMenu(menu)
    }

    private fun manageSearch(searchItem: MenuItem) {
        val searchView =
            searchItem.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_SEARCH
        manageFabOnSearchItemStatus(searchItem)
        manageInputTextInSearchView(searchView)
    }

    private fun manageFabOnSearchItemStatus(searchItem: MenuItem) {
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                binding.deleteFloatingActionButton.hide()
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                binding.deleteFloatingActionButton.show()
                return true
            }
        })
    }

    private fun manageInputTextInSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                refreshData()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.search = newText
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> showNavigationDrawer()
            R.id.refresh_optionMenu -> refreshData()
            R.id.sendFeedBack_optionMenu -> startActivity(
                Intent(
                    applicationContext,
                    SendFeedback::class.java
                )
            )
            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }

    private fun showNavigationDrawer() {
        navigationDrawerBottomSheet.show(
            supportFragmentManager.beginTransaction(),
            navigationDrawerBottomSheet.tag
        )
    }

    fun showDarkMode() {
        darkModeBottomSheet.show(
            supportFragmentManager.beginTransaction(),
            darkModeBottomSheet.tag
        )
    }


    override fun onBackPressed() {
        onBack()
    }

    private fun onBack() {
        if (doubleBackToExitPressedOnce) {
            closeApp()
            return
        }
        doubleBackToExitPressedOnce = true
        Toaster.showToast(applicationContext, getString(R.string.click_back_text))
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000L)
            doubleBackToExitPressedOnce = false
        }
    }

    private fun closeApp() {
        val homeIntent = Intent(Intent.ACTION_MAIN)
        homeIntent.addCategory(Intent.CATEGORY_HOME)
        homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(homeIntent)
    }
}