package com.example.mvvm.repository

import com.example.mvvm.network.RetrofitClient

class EmployeeRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun fetchEmployees() = apiService.getEmployees()


}