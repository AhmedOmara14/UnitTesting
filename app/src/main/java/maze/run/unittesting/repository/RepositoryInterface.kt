package maze.run.unittesting.repository

import androidx.lifecycle.LiveData
import maze.run.unittesting.remote.respnoces.ImageResponce
import maze.run.unittesting.other.Resource
import maze.run.unittestingroomretrofitmmvm.data.local.ShoppingItem

interface RepositoryInterface {

    suspend fun insert(shoppingItem: ShoppingItem)

    suspend fun delete(shoppingItem: ShoppingItem)

    fun observeAllItems(): LiveData<List<ShoppingItem>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchforimage(imagequery:String) : Resource<ImageResponce>

}