package krg.sltn.nsqmarkettest

import krg.sltn.nsqmarkettest.models.Response
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface API {
        @POST("transcript")
        @Headers(
            "Content-Type: application/json",
            "Authorization: 71835505a2aa49049ef1c214fc15b26b"
        )
        suspend fun transcript(@Body body: Map<String, String>): Response

        @GET("transcript/{id}")
        @Headers(
            "Content-Type: application/json",
            "Authorization: 71835505a2aa49049ef1c214fc15b26b"
        )
        suspend fun status(@Path("id") id: String): Response
}

object ApiClient {

    fun assemblyaiApi(): API {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.assemblyai.com/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        return retrofit.create(API::class.java)
    }
}

