package com.example.githubapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.domin.MainRepository
import com.example.githubapp.models.Data.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(private val repo: MainRepository): ViewModel() {


    val getUserPropileFlow = MutableSharedFlow<GetUserProfileInfoData>()
    val getUserRepositoryFlow = MutableSharedFlow<List<GetUserRepositoriesData>>()
    val getAccessTokenFlow = MutableSharedFlow<ResponseData>()
    val messageFlow = MutableSharedFlow<String>()
    val errorFlow = MutableSharedFlow<Throwable>()

    suspend fun getUserPropile() {
        repo.getUserPropile().onEach {
            when(it) {
            is ResultData.Success -> {
                getUserPropileFlow.emit(it.data)
            }
                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
        }

    suspend fun getUserRepository() {
        repo.getUserRepository().onEach {
            when(it) {
                is ResultData.Success -> {
                    getUserRepositoryFlow.emit(it.data)
                }
                is ResultData.Message -> {
                   messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun getAccessToken(code: String) {
        repo.getAccessToken(code).onEach {
            when(it) {
                is ResultData.Success -> {
                    getAccessTokenFlow.emit(it.data)
                }
                is ResultData.Message -> {
                    messageFlow.emit(it.message)
                }
                is ResultData.Error -> {
                    errorFlow.emit(it.error)
                }
            }
        }.launchIn(viewModelScope)
    }


}