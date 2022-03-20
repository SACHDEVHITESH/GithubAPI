package com.example.githubapi.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapi.data.remote.api.base.ApiClient
import com.example.githubapi.data.remote.api.models.ClosedPRModel
import com.example.githubapi.data.remote.api.models.GithubUserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ClosedPrViewModel : ViewModel() {

    private val githubClosedPR = MutableLiveData<List<ClosedPRModel>?>()
    private val githubUserModel = MutableLiveData<GithubUserModel?>()

    fun getClosedPRInfo() : MutableLiveData<List<ClosedPRModel>?> {
            val response = ApiClient.getInstance().apis.getClosedPRInfo()
                response.enqueue(object : Callback<List<ClosedPRModel>>{
                    override fun onResponse(call: Call<List<ClosedPRModel>>, response: Response<List<ClosedPRModel>>) {
                        if (response.code() == 200) {
                            githubClosedPR.value = response.body()
                        } else {
                            githubClosedPR.value = null
                        }
                    }
                    override fun onFailure(call: Call<List<ClosedPRModel>>, t: Throwable) {
                        githubClosedPR.value = null
                    }

                })

        return githubClosedPR
    }

    fun getUserInfoByUsername() : MutableLiveData<GithubUserModel?> {
        val response = ApiClient.getInstance().apis.getUserInfo()
        response.enqueue(object : Callback<GithubUserModel>{
            override fun onResponse(call: Call<GithubUserModel>, response: Response<GithubUserModel>) {
                if (response.code() == 200) {
                    githubUserModel.value = response.body()
                } else {
                    githubUserModel.value = null
                }
            }
            override fun onFailure(call: Call<GithubUserModel>, t: Throwable) {
                githubUserModel.value = null
            }

        })

        return githubUserModel
    }
}
