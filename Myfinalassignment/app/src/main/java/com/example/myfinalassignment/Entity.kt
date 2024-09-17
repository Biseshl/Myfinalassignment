package com.example.myfinalassignment

import java.io.Serializable

// Data class representing an entity with properties and description
data class Entity(
    val property1: String,     // First property of the entity
    val property2: String,     // Second property of the entity
    val description: String    // Detailed description of the entity
) : Serializable  // Allows the object to be passed between activities
