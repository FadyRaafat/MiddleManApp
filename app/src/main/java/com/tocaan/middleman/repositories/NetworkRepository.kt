package com.tocaan.middleman.repositories

import com.tocaan.middleman.api.models.UserBody
import com.tocaan.middleman.api.service.ClientService
import retrofit2.Callback
import retrofit2.Response

class NetworkRepository {
    var clientService = ClientService.getClient()

    fun saveUser(
        userBody: UserBody,
        onFinish: (Boolean, String?) -> Unit
    ) {
        clientService.saveUser(userBody)
            .enqueue(object : Callback<String> {
                override fun onResponse(
                    call: retrofit2.Call<String>,
                    response: Response<String>
                ) {
                    if (response.isSuccessful) {
                        onFinish.invoke(true, response.body())
                    } else {
                        onFinish.invoke(false, response.errorBody()?.string())

                    }
                }

                override fun onFailure(call: retrofit2.Call<String>, t: Throwable) {
                    onFinish.invoke(
                        false,
                        "NOK, unable to connect to server, please make sure it's up and running"
                    )
                }
            })

    }
}

