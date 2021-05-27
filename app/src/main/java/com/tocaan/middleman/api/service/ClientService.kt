package com.tocaan.middleman.api.service

import com.tocaan.middleman.api.LiveDataCallAdapterFactory
import com.tocaan.middleman.api.models.UserBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ClientService {

    companion object {
        fun getClient(): ClientService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://127.0.0.1:5000/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(HttpClient.getClient())
                .build()

            return retrofit.create(ClientService::class.java)
        }
    }

    @POST("saveUser")
    fun saveUser( @Body userBody: UserBody): Call<String>


}