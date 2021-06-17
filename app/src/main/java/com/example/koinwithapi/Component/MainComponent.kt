package com.example.koinwithapi.Component

import com.example.koinwithapi.data.repo.PostRepository
import com.example.koinwithapi.network.ApiService
import com.example.koinwithapi.viewmodel.MainViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainComponent :KoinComponent{
    val apiService :ApiService by inject()
    val postRepository : PostRepository by inject()
    val mainViewModel: MainViewModel by inject()
}