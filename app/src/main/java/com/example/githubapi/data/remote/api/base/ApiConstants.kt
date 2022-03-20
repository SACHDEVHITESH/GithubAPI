package com.example.githubapi.data.remote.api.base

interface ApiConstants {
    companion object {
        var baseUrl: String
            get() {
                return "https://api.github.com/"
            }
            set(value) {
            }
    }
}