package co.edu.udea.compumovil.gr06_20232.lab1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class CountryName(
    var common: String,

)
data class Country(
    var name: CountryName,
)

const val BASE_URL = "https://restcountries.com/v3.1/subregion/"

interface APIService {
    @GET("South America?fields=name")
    suspend fun getCountries(): List<Country>

    companion object {
        var apiService: APIService? = null
        fun getInstance(): APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}