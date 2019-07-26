package com.fioalpha.dogshows.list

import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.fioalpha.dogshows.R
import com.squareup.picasso.Picasso

class ShowDogFullScreen: AppCompatActivity() {

    private val urlImageExtra: String by lazy {
        intent.getStringExtra(DogListActivity.DOG_IMAGE_PATH_EXTRA)
    }

    private val dogImageView: ImageView by lazy {
        ImageView(this).apply {
            layoutParams = WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(dogImageView)

        Picasso.with(this).load(urlImageExtra)
            .placeholder(R.drawable.ic_dog)
            .centerCrop()
            .fit()
            .into(dogImageView)

    }

}