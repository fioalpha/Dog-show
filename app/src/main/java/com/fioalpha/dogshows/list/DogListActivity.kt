package com.fioalpha.dogshows.list

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fioalpha.dogshows.R
import com.fioalpha.dogshows.signup.hide
import com.fioalpha.dogshows.signup.show
import com.fioalpha.dogshows.splash.NextPage
import com.fioalpha.dogshows.splash.goActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_dog_list.*
import org.koin.android.ext.android.inject

class DogListActivity : AppCompatActivity() {

    companion object {
        const val DOG_IMAGE_PATH_EXTRA = "DOG_IMAGE_PATH_EXTRA"
    }

    private val viewModel: DogListViewModel by inject()

    private val dogsAdapter: DogsAdapter by lazy {
        DogsAdapter {
            goActivity(
                NextPage.FULL_SCREEN,
                Bundle().apply { putString(DOG_IMAGE_PATH_EXTRA, it) }
            )
        }
    }

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_list)

        dog_recycler.apply {
            layoutManager = LinearLayoutManager(this@DogListActivity)
            adapter = dogsAdapter
        }

        disposable.add( viewModel.viewModel.subscribe(::render) )

        disposable.add( viewModel.getDogsByCategory(getString(R.string.husky_category)).subscribe() )

        dog_list_bottom_menu.setOnNavigationItemSelectedListener (::clickMenuHandle)
    }

    override fun onPause() {
        super.onPause()
        disposable.clear()
    }

    private fun render(it: DogListViewState?) {
        when (it) {
            is DogListViewState.Loading -> {
                loading()
            }
            is DogListViewState.Success -> {
                success(it)
            }
            is DogListViewState.Failure -> {
                //TODO Implementation handle to failure
            }
        }
    }

    private fun clickMenuHandle(itemSelected: MenuItem): Boolean {
        when (itemSelected.itemId) {
            R.id.menu_pug -> {
                getDogs(getString(R.string.pug_category))
            }
            R.id.menu_hound -> {
                getDogs(getString(R.string.hound_category))
            }
            R.id.menu_husly -> {
                getDogs(getString(R.string.husky_category))
            }
            R.id.menu_labrador -> {
                getDogs(getString(R.string.labrador_category))
            }
            else -> {
                throw RuntimeException(getString(R.string.dog_category_dont_found_error))
            }
        }
        return true
    }

    private fun success(it: DogListViewState.Success) {
        dog_list_progress.hide()
        dog_recycler.show()
        dog_list_bottom_menu.show()
        dogsAdapter.setData(it.dogs)
    }

    private fun loading() {
        dog_list_progress.show()
        dog_recycler.hide()
        dog_list_bottom_menu.hide()
    }

    private fun getDogs(category: String) {
        viewModel.getDogsByCategory(category)
            .subscribe()

    }

}


