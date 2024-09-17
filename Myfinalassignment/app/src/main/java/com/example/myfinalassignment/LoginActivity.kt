package com.example.myfinalassignment

import AuthService
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialise UI components
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvError = findViewById(R.id.tvError)

        // Set click listener on login button
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                // Perform login if fields are filled
                performLogin(username, password)
            } else {
                // Display error if fields are empty
                tvError.text = "Kindly complete all the required fields."
                tvError.visibility = TextView.VISIBLE
            }
        }
    }

    private fun performLogin(username: String, password: String) {
        // Create Retrofit instance for API calls
        val retrofit = Retrofit.Builder()
            .baseUrl("https://vu-nit3213-api.onrender.com/")  // Base URL of the API
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val authService = retrofit.create(AuthService::class.java)

        // Create a LoginRequest with the entered username and password
        val loginRequest = LoginRequest(username, password)

        // Make the login API call
        authService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val keypass = response.body()?.keypass
                    Log.d("LoginActivity", "Keypass: $keypass")

                    // Navigate to DashboardActivity and pass the keypass
                    keypass?.let {
                        val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                        intent.putExtra("keypass", it)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    // Show error message for invalid credentials
                    tvError.text = "Invalid credentials, please try again"
                    tvError.visibility = TextView.VISIBLE
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Show error message for API failure
                tvError.text = "Error: ${t.message}"
                tvError.visibility = TextView.VISIBLE
            }
        })
    }
}
