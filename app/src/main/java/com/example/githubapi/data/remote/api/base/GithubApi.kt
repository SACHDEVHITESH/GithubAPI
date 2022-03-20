package com.example.githubapi.data.remote.api.base

import com.example.githubapi.data.remote.api.models.ClosedPRModel
import com.example.githubapi.data.remote.api.models.GithubUserModel
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {

    //torvalds -- github username which should be changed to view some other user profile
    //test-tlb -- github repo name which should be changed to view data of some other repo
    //state=closed -- this can be all or open as well
    @GET("repos/torvalds/test-tlb/pulls?state=closed")
    fun getClosedPRInfo(): Call<List<ClosedPRModel>>

    //torvalds = github username which should be changed to view some other user profile
    @GET("users/torvalds")
    fun getUserInfo(): Call<GithubUserModel>
}