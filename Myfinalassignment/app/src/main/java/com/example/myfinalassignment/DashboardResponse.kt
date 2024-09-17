package com.example.myfinalassignment

// Data class representing the response from the dashboard API
data class DashboardResponse(
    val entities: List<Entity>,     // List of entity objects
    val entityTotal: Int            // Total count of entities
)
