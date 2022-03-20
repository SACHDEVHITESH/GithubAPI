package com.example.githubapi.data.remote.api.base

import com.example.githubapi.data.remote.api.models.ClosedPRModel
import com.example.githubapi.data.remote.api.models.GithubUserModel
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {

    @GET("repos/torvalds/test-tlb/pulls?state=closed")
    fun getClosedPRInfo(
    ): Call<List<ClosedPRModel>>

    @GET("users/torvalds")
    fun getUserInfo(
    ): Call<GithubUserModel>
}