package com.example.githubapp.di

import com.example.githubapp.Retrofit.TodoApi
import com.example.githubapp.ViewModel.MainViewModel
import com.example.githubapp.ViewModel.SearchViewModel
import com.example.githubapp.domin.MainRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<MainRepository> {
        MainRepository(api = get() )
    }
    single<TodoApi> {
        provideTodoApi(retrofit = get())
    }

    single<Retrofit> {
        val httpLogginInterceptor = HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
        )

        val interceptor = GitHubInterceptor()

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLogginInterceptor)
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.github.com").client(client).build()
        retrofit
    }

}
val viewModelModule = module {
    viewModel<MainViewModel> {
        MainViewModel(repo = get())
    }
    viewModel<SearchViewModel>{
        SearchViewModel(repo = get())
    }
}



fun provideTodoApi(retrofit: Retrofit): TodoApi{
    return retrofit.create(TodoApi::class.java)
}