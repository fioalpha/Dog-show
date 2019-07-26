package com.fioalpha.dogshows.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fioalpha.dogshows.R
import com.fioalpha.dogshows.list.DogListActivity
import com.fioalpha.dogshows.list.ShowDogFullScreen
import com.fioalpha.dogshows.signup.SignUpActivity
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class SplashActivity: AppCompatActivity() {

    private val viewModel: SplashViewModel by inject()

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.actvitity_splash)

        disposable.add(
            viewModel.bind()
                .delay(2000, TimeUnit.MILLISECONDS)
                .subscribe (
                    { goActivity(it) }, { Log.e("ERROR", "Has Error") }
                )
        )

    }

    override fun onPause() {
        super.onPause()
        disposable.clear()
    }

}


fun Context.goActivity(activities: NextPage, bundle: Bundle = Bundle()) {
    when (activities) {
         NextPage.DOG_LIST ->{
            Intent(this, DogListActivity::class.java).apply {
                    addFlags(
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK
                    )
                startActivity(this)
            }
         }
        NextPage.SIGN_UP -> {
            Intent(this, SignUpActivity::class.java).apply {
                addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
                )
                startActivity(this)
            }
        }
        NextPage.FULL_SCREEN -> {
            Intent(this, ShowDogFullScreen::class.java).apply {
                putExtras(bundle)
                startActivity(this)
            }
        }
    }

}