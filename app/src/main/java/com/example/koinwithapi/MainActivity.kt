package com.example.koinwithapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koinwithapi.Component.MainComponent
import com.example.koinwithapi.adapter.PostAdapter
import com.example.koinwithapi.databinding.ActivityMainBinding
import com.example.koinwithapi.util.ApiState
import com.example.koinwithapi.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
    private lateinit var postAdapter: PostAdapter
    private val mainViewModel : MainViewModel by viewModel<MainViewModel>()
   // private val component : MainComponent = MainComponent()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        mainViewModel.getPost()
        lifecycleScope.launchWhenStarted {
            mainViewModel._postStateFlow.collect { response->
                when(response){
                    is ApiState.Loading->{
                        binding.recyclerview.isVisible=false
                        binding.progressBar.isVisible=true
                    }
                    is ApiState.Failure -> {
                        binding.recyclerview.isVisible = false
                        binding.progressBar.isVisible = false
                    }
                    is ApiState.Success->{
                        binding.recyclerview.isVisible = true
                        binding.progressBar.isVisible = false
                        postAdapter.setData(response.data)
                        postAdapter.notifyDataSetChanged()
                    }
                    is ApiState.Empty->{

                    }
                }
            }
        }

    }

    private fun initRecyclerView() {
        postAdapter = PostAdapter(ArrayList())
        binding.apply {
            recyclerview.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = postAdapter
            }
        }
    }
}