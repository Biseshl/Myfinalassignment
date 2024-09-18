# Myfinalassignment

**If failed to run code please check ZIP file.**
**Sorry for interruption😊**
NIT3213
Bisesh Lamichhane 's4678062'

MyFinalAssignment is an Android application built with Kotlin that demonstrates API integration, user interface design, and best practices in Android development. It features a Login screen for authentication, a Dashboard screen to display data, and a Details screen for viewing detailed information on selected items.

**Features**
Login Screen: Authenticates users via the API.
Dashboard Screen: Displays a list of entities after login.
Details Screen: Shows detailed info for a selected entity.
Error Handling: Manages invalid login attempts and other errors.


**Tech Stack**
Kotlin: For app development.
Retrofit: For API calls and HTTP requests.
ConstraintLayout and AndroidX: For responsive UI.
Material Design: For modern UI components.
Koin: For dependency injection.
Git: Version control.


**API Overview**
Base URL: https://vu-nit3213-api.onrender.com
Endpoints
Login: /footscray/auth (POST), Request: {"username": "YourFirstName", "password": "YourStudentID"}, Response: {"keypass": "topicName"}
Dashboard: /dashboard/{keypass} (GET), Response: List of entities.

**Setup**
Clone the repo:
git clone https://github.com/Biseshl/myfinalassignment.git


**Open the project in Android Studio.**
Sync Gradle to install dependencies.
Run the project using an emulator or physical device.
Dependencies
Retrofit: HTTP client
Gson: JSON parsing
Koin: Dependency injection
AndroidX, Material Components for UI
