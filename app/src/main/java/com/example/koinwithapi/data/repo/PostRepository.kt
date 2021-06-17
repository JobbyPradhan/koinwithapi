package com.example.koinwithapi.data.repo

import com.example.koinwithapi.data.Post
import com.example.koinwithapi.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PostRepository(private val apiServiceImpl: ApiServiceImpl) {
    fun getPost() : Flow<List<Post>> = flow{
        emit(apiServiceImpl.getPost())
    }.flowOn(Dispatchers.IO)
    /*  fun getPost():Flow<List<Post>> = flow {
        emit(apiServiceImpl.getPost())
    }.flowOn(Dispatchers.IO)*/
}