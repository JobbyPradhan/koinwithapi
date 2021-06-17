package com.example.koinwithapi.data

import com.example.koinwithapi.adapter.PostAdapter
import com.example.koinwithapi.data.repo.PostRepository
import com.example.koinwithapi.network.ApiService
import com.example.koinwithapi.network.ApiServiceImpl
import com.example.koinwithapi.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    factory { ApiServiceImpl() } bind ApiService::class
    //single { PostAdapter }
    single { PostRepository(get()) }
    viewModel { MainViewModel(get()) }
}