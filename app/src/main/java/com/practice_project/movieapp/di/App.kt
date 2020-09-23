package com.practice_project.movieapp.di

import android.app.Application
import com.practice_project.movieapp.MovieConstants
import com.practice_project.movieapp.retrofit.MovieService
import com.practice_project.movieapp.viewmodel.DetailsViewModel
import com.practice_project.movieapp.viewmodel.MoviesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class App: Application() {
    private val apiModule = module {
        fun movieService(): MovieService {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            logger.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder().addInterceptor(logger).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(MovieConstants.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(MovieService::class.java)
        }

        single { movieService() }
    }

    private val viewModelModule = module {
        viewModel { DetailsViewModel(get()) }
        viewModel { MoviesViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                apiModule,
                viewModelModule
            )
        }
    }

    val appComponent: ApplicationGraph by lazy {
        DaggerApplicationGraph.builder()
            .viewModelModule(ViewModelModule())
            .apiModule(ApiModule())
            .build()
    }
}