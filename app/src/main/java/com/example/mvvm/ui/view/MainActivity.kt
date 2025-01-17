package com.example.mvvm.ui.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.ui.adapter.EmployeeAdapter
import com.example.mvvm.ui.viewmodel.EmployeeViewModel
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: EmployeeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe employee data
        viewModel.employees.observe(this) { employees ->
            binding.recyclerView.adapter = EmployeeAdapter(employees)
        }

        // Observe loading state
        viewModel.loading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observe error state
        viewModel.error.observe(this) { error ->
            if (error.isNotEmpty()) {
                // Display error using Toast or Snack bar
            }
        }

        // Fetch employees
        viewModel.fetchEmployees()

    }
}