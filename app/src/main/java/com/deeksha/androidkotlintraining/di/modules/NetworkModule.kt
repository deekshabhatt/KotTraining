package com.deeksha.androidkotlintraining.di.modules


import android.app.Application
import com.deeksha.androidkotlintraining.data.network.MyApi
import com.deeksha.androidkotlintraining.data.network.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
object NetworkModule {

    @Provides
    internal fun provideApiInterfaceInstance(client: OkHttpClient): MyApi {
        return provideRetrofitInterface(client).create(MyApi::class.java)
    }
    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    internal fun provideRetrofitInterface(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    // .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")

    @Provides
    fun provideHttpClient(interceptor: NetworkConnectionInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideInterceptor(application: Application): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(application)
    }

    //.certificatePinner(certificatePinner)

    /* private val certificatePinner = CertificatePinner.Builder()
         .add("https://api.simplifiedcoding.in/course-apis/mvvm/", "sha256/0jQVmOH3u5mnMGhGRUCmMKELXOtO9q8i3xfrgq3SfzI")
         .build()*/
}
