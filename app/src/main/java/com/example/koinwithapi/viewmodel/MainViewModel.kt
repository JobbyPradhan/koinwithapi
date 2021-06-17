package com.example.koinwithapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koinwithapi.data.repo.PostRepository
import com.example.koinwithapi.network.ApiService
import com.example.koinwithapi.util.ApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel (private var postRepository: PostRepository): ViewModel() {

    private val postStateFlow : MutableStateFlow<ApiState> =
        MutableStateFlow(ApiState.Empty)
    val _postStateFlow : StateFlow<ApiState> = postStateFlow

    fun getPost() = viewModelScope.launch {
        postStateFlow.value = ApiState.Loading
        postRepository.getPost()
            .catch { e->
                postStateFlow.value = ApiState.Failure(e)
            }.collect { data->
                postStateFlow.value = ApiState.Success(data)
            }
    }
}