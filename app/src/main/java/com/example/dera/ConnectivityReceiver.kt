package com.example.dera

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

@Suppress("DEPRECATION")
class NetworkChangeReceiver(private val listener: ThreeButtonPage) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    interface NetworkChangeListener {
        fun onNetworkAvailable()
        fun onNetworkUnavailable()
    }
}
