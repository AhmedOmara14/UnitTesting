package maze.run.unittesting.remote.respnoces

import maze.run.unittesting.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PaxbayAPI {

    @GET("/api/")
    suspend fun searchforimage(
        @Query("q") searchquery :String,
        @Query("key") apikey: String = BuildConfig.API_KEY,
        ):Response<ImageResponce>
}