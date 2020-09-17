package maze.run.unittesting.Di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import maze.run.unittesting.remote.respnoces.PaxbayAPI
import maze.run.unittesting.repository.Repository
import maze.run.unittesting.repository.RepositoryInterface
import maze.run.unittestingroomretrofitmmvm.data.local.ShoppingDao
import maze.run.unittestingroomretrofitmmvm.data.local.ShoppingDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingDatabase::class.java, "Shopping").build()

    @Singleton
    @Provides
    fun provideShoppingItemRepository(
        dao: ShoppingDao,
        paxbayAPI: PaxbayAPI
    ) = Repository(dao, paxbayAPI) as RepositoryInterface


    @Singleton
    @Provides
    fun provideShoppingItemDao(
        shoppingDatabase: ShoppingDatabase
    ) = shoppingDatabase.shoppingDao()


    @Singleton
    @Provides
    fun providePaxbayApi(): PaxbayAPI {
        return Retrofit.Builder()
            .baseUrl("https://pixabay.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PaxbayAPI::class.java)

    }


}