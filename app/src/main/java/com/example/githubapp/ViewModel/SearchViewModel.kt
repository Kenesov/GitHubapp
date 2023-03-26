package com.example.githubapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.domin.MainRepository
import com.example.githubapp.models.Data.GetUserProfileInfoData
import com.example.githubapp.models.Data.GetUserRepositoriesData
import com.example.githubapp.models.Data.ItemsData
import com.example.githubapp.models.Data.ResultData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchViewModel(private val repo: MainRepository): ViewModel() {

    val searchUserFlow = MutableSharedFlow<List<ItemsData>>()
    val searchRepositoryFlow = MutableSharedFlow<List<GetUserRepositoriesData>>()
    val messageFlow = MutableSharedFlow<String>()
    val errorFlow = MutableSharedFlow<Throwable>()

    suspend fun searchUser(login: String) {
        repo.searchUser(login).onEach {
            when(it) {
            is ResultData.Success ->{
                searchUserFlow.emit(it.data)
            }
            is  ResultData.Message -> {
                messageFlow.emit(it.message)
            }
            is ResultData.Error -> {
                errorFlow.emit(it.error)
            }
            }
        }
    }

    suspend fun searchRepository(name: String) {
        repo.searchRepository(name).onEach {
            when(it) {
                is ResultData.Success ->{
                    searchRepositoryFlow.emit(it.data)
                }
                is ResultData.Message ->{
                    messageFlow.emit(it.message)
                }
                is ResultData.Error ->{
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }
}