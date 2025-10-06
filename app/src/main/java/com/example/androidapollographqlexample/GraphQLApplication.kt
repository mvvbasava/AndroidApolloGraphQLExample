package com.example.androidapollographqlexample

import android.app.Application
import com.example.androidapollographqlexample.network.ApolloClientProvider

class GraphQLApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        // Initialize Apollo Client
        // The ApolloClientProvider will create the client lazily when first accessed
        // This ensures proper initialization timing
    }
    
    companion object {
        // Provide access to Apollo Client throughout the app
        val apolloClient by lazy { ApolloClientProvider.apolloClient }
    }
}
