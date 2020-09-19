package maze.run.unittesting.DI

import android.content.Context
import androidx.room.Room
import androidx.test.espresso.Root
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import maze.run.unittestingroomretrofitmmvm.data.local.ShoppingDatabase
import maze.run.unittestingroomretrofitmmvm.data.local.ShoppingItem
import javax.inject.Named

@Module
@InstallIn(ApplicationComponent::class)
class TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryInDb(@ApplicationContext context: Context) = Room
        .inMemoryDatabaseBuilder(context, ShoppingDatabase::class.java)
        .allowMainThreadQueries()
        .build()
}