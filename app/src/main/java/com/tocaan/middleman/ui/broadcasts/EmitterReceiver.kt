package com.tocaan.middleman.ui.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.tocaan.middleman.api.models.UserBody
import com.tocaan.middleman.repositories.NetworkRepository

class EmitterReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.getStringExtra("name")
        NetworkRepository().saveUser(
            UserBody(
                intent?.getIntExtra("id", 0),
                intent?.getStringExtra("email"),
                intent?.getStringExtra("name"),
                intent?.getStringExtra("phone"),
                intent?.getStringExtra("username"),
                intent?.getStringExtra("website")
            )
        )
        { requestState, response ->
            respond(context, response)
        }

    }

    private fun respond(context: Context?, response: String?) {
        val intent = Intent()
        intent.action = "com.middleman.response"
        intent.putExtra("message", response)
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        context?.sendBroadcast(intent)
    }
}
