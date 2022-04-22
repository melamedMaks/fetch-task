package maksim.melamed.fetchtask.services

import maksim.melamed.fetchtask.models.Data
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Service {
    @GET("hiring.json")
    suspend fun getFetchData(
    ): List<Data>

    companion object {
        fun create(): Service {
            return Retrofit.Builder()
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Service::class.java)
        }
    }
}