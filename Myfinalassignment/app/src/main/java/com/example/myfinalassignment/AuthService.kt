import com.example.myfinalassignment.DashboardResponse
import com.example.myfinalassignment.LoginRequest
import com.example.myfinalassignment.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {

    // Login endpoint based on location
    @POST("footscray/auth")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    // Retrieve dashboard data
    @GET("dashboard/{keypass}")
    fun getDashboard(@Path("keypass") keypass: String): Call<DashboardResponse>

}
