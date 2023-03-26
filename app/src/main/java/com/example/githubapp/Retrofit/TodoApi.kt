package com.example.githubapp.Retrofit

import com.example.githubapp.models.Data.*
import retrofit2.Response
import retrofit2.http.*

interface TodoApi {

    @GET("/user")
    @FormUrlEncoded
    suspend fun getUserInfo(): Response<GetUserProfileInfoData>

    @GET("/user/repos")
    @FormUrlEncoded
    suspend fun getUserRepository(): Response<List<GetUserRepositoriesData>>

    @GET("/search/users?q=yourtext")
    @FormUrlEncoded
    suspend fun searchUsers(
        @Query("login") login: String
    ): Response<SearchUsersByUsernameData>

    @GET("/search/repositories?q=Chat App")
    @FormUrlEncoded
    suspend fun searchRepository(
        @Query("q") name: String
    ): Response<SearchRepositoriesByRepositoryNameData>

    @Headers("Accept: application/json")
    @POST("https://github.com/login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") client_id: String,
        @Field("client_secret") client_secret: String,
        @Field("code") code: String
    ): Response<ResponseData>
}