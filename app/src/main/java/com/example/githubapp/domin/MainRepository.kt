package com.example.githubapp.domin

import android.security.identity.ResultData
import com.example.githubapp.Retrofit.TodoApi
import com.example.githubapp.models.Local.LocalStorage
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MainRepository(val api: TodoApi) {

    suspend fun getUserPropile() = flow {
        val response = api.getUserInfo()
        if (response.isSuccessful){
            emit(com.example.githubapp.models.Data.ResultData.Success(response.body()!!))
        } else{
            emit(com.example.githubapp.models.Data.ResultData.Message(response.message()))
        }
    }.catch {
        emit(com.example.githubapp.models.Data.ResultData.Error(it))
    }

    suspend fun getUserRepository() = flow {
        val response = api.getUserRepository()

        if (response.isSuccessful){
            emit(com.example.githubapp.models.Data.ResultData.Success(response.body()!!))
        } else {
            emit(com.example.githubapp.models.Data.ResultData.Message(response.message()))
        }
    }.catch {
        emit(com.example.githubapp.models.Data.ResultData.Error(it))
    }

    suspend fun searchUser(login: String) = flow {
        val response = api.searchUsers(login)
        if (response.isSuccessful) {
            emit(com.example.githubapp.models.Data.ResultData.Success(response.body()!!.items))
        } else {
            emit(com.example.githubapp.models.Data.ResultData.Message(response.message()))
        }
    }.catch {
        emit(com.example.githubapp.models.Data.ResultData.Error(it))
    }

    suspend fun getAccessToken(code: String) = flow {
        val client_id = "8f3cf5f09bd0c93a0528"
        val client_secret = "5447af3efb5afba3751aa6a0025e97affcf1a538"
        val response = api.getAccessToken(client_id,client_secret,code)
        if (response.isSuccessful){
            emit(com.example.githubapp.models.Data.ResultData.Success(response.body()!!))
        } else {
            emit(com.example.githubapp.models.Data.ResultData.Message(response.message()))
        }
    }.catch {
        emit(com.example.githubapp.models.Data.ResultData.Error(it))
    }

    suspend fun searchRepository(searchValue: String) = flow {
        val reponse = api.searchRepository(searchValue)
        if (reponse.isSuccessful){
            emit(com.example.githubapp.models.Data.ResultData.Success(reponse.body()!!.items))
        } else {
            emit(com.example.githubapp.models.Data.ResultData.Message(reponse.message()))
        }
    }.catch {
        emit(com.example.githubapp.models.Data.ResultData.Error(it))
    }
}