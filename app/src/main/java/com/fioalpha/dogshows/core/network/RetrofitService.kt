package com.fioalpha.dogshows.core.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    val BASE_URL = "https://api-iddog.idwall.co/"

    fun retrofit(
        client: OkHttpClient = httpClient(),
        gson: Gson = gson(),
        url: String = BASE_URL
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(url)
            .build()
    }

    private fun gson(): Gson = GsonBuilder().create()

    private fun httpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()

}

val networkModule = module {
    single { RetrofitService().retrofit()}
}