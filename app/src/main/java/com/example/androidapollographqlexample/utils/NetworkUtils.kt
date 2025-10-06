package com.example.androidapollographqlexample.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object NetworkUtils {
    
    /**
     * Check if the device has an active internet connection
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            
            when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected == true
        }
    }
    
    /**
     * Get a user-friendly error message based on the exception type
     */
    fun getErrorMessage(exception: Throwable): String {
        return when (exception) {
            is java.net.UnknownHostException -> "No internet connection. Please check your network settings."
            is java.net.SocketTimeoutException -> "Request timed out. Please try again."
            is java.net.ConnectException -> "Unable to connect to server. Please try again later."
            is com.apollographql.apollo3.exception.ApolloException -> {
                when (exception.message) {
                    "HTTP 404" -> "Resource not found."
                    "HTTP 500" -> "Server error. Please try again later."
                    else -> exception.message ?: "An unexpected error occurred."
                }
            }
            else -> exception.message ?: "An unexpected error occurred."
        }
    }
}
