package com.example.myfinalassignment

import AuthService
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DashboardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EntityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Initialise the RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Retrieve the keypass from the intent
        val keypass = intent.getStringExtra("keypass")

        // Check if keypass is not null and fetch dashboard data
        keypass?.let {
            fetchDashboardData(it)
        } ?: run {
            // Show error if keypass is null
            Toast.makeText(this, "Keypass is missing", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchDashboardData(keypass: String) {
        // Create a Retrofit instance to interact with the API
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vu-nit3213-api.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create an instance of AuthService using Retrofit
        val authService = retrofit.create(AuthService::class.java)

        // Call the API to fetch the dashboard data
        authService.getDashboard(keypass).enqueue(object : Callback<DashboardResponse> {
            override fun onResponse(call: Call<DashboardResponse>, response: Response<DashboardResponse>) {
                if (response.isSuccessful) {
                    // Get the list of entities from the response
                    val entities = response.body()?.entities ?: emptyList()

                    // Set up the adapter with the retrieved entities
                    adapter = EntityAdapter(entities) { entity ->
                        // Navigate to the Details screen when an item is clicked
                        val intent = Intent(this@DashboardActivity, DetailActivity::class.java)
                        intent.putExtra("entity", entity)
                        startActivity(intent)
                    }

                    // Attach the adapter to the RecyclerView
                    recyclerView.adapter = adapter
                } else {
                    // Show error message if the response is not successful
                    Toast.makeText(this@DashboardActivity, "Failed to load dashboard", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                // Show error message in case of API call failure
                Toast.makeText(this@DashboardActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
