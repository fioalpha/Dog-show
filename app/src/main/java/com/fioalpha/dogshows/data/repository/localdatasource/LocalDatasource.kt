package com.fioalpha.dogshows.data.repository.localdatasource

import android.content.Context
import android.util.Base64
import com.fioalpha.dogshows.domain.model.User
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Maybe

interface LocalDatasource {

    fun saveToken(user: User): Completable
    fun getToken(): Maybe<User>

}

class LocalDatasourceImpl(
    private val context: Context,
    private val gson: Gson
): LocalDatasource {
    private val shared by lazy { context.getSharedPreferences("DOG_SHARED", Context.MODE_PRIVATE) }

    override fun getToken(): Maybe<User> {
        return Maybe.defer {
            val text = shared.getString("USER", "")
            if (text.isNullOrEmpty()) Maybe.empty()
            else {
                val userParse = String(Base64.decode(text, Base64.DEFAULT))
                Maybe.just(gson.fromJson(userParse, User::class.java))
            }
        }
    }

    override fun saveToken(user: User): Completable {
        write(user) { encoder(it) }
        return Completable.complete()
    }

    private fun write(user: User, encoder: (String) -> String ) {

        val userParse = gson.toJson(user)

        with(shared.edit()) {
            putString("USER", encoder(userParse))
            apply()
        }
    }

    private fun encoder(text: String): String = Base64.encodeToString(text.toByteArray(), Base64.DEFAULT)
}