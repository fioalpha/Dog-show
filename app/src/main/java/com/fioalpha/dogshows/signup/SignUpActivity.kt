package com.fioalpha.dogshows.signup

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fioalpha.dogshows.R
import com.fioalpha.dogshows.splash.NextPage
import com.fioalpha.dogshows.splash.goActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_sign_up_activity.*
import org.koin.android.ext.android.inject

class SignUpActivity: AppCompatActivity() {

    private val viewModel: SignUpViewModel by inject()

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_up_activity)

        disposable.add(
            viewModel.viewState.subscribe { render(it) }
        )

        button.setOnClickListener {
            disposable.add(viewModel.makeSignUp(sign_up_email.text.toString()).subscribe())
        }

    }

    override fun onPause() {
        super.onPause()
        disposable.clear()
    }

    private fun render(state: SignUpViewState) {
        when(state) {
            is SignUpViewState.Success -> { handleViewSuccess() }
            is SignUpViewState.Loading -> { handleViewLoading() }
            is SignUpViewState.Failure -> handleViewError(state.error)
        }
    }

    private fun handleViewSuccess() {
        sign_up_container_group.show()
        sign_up_progress_group.hide()
        goActivity(NextPage.DOG_LIST)
    }

    private fun handleViewLoading() {
        sign_up_container_group.hide()
        sign_up_progress_group.show()
    }

    private fun handleViewError(throwable: Throwable) {
        sign_up_container_group.show()
        sign_up_progress_group.hide()
        showMessage(throwable.message)
    }
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun Context.showMessage(messageError: String?) {
    Toast.makeText(this, messageError?: "Houve algum erro", Toast.LENGTH_SHORT).show()
}