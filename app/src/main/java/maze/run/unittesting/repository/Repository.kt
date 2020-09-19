package maze.run.unittesting.repository

import androidx.lifecycle.LiveData
import maze.run.unittesting.remote.respnoces.ImageResponce
import maze.run.unittesting.remote.respnoces.PaxbayAPI
import maze.run.unittesting.other.Resource
import maze.run.unittestingroomretrofitmmvm.data.local.ShoppingDao
import maze.run.unittestingroomretrofitmmvm.data.local.ShoppingItem
import javax.inject.Inject

class Repository
@Inject constructor(
    val shoppingDao: ShoppingDao,
    val paxbayAPI: PaxbayAPI
) : RepositoryInterface {
    override suspend fun insert(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun delete(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
       return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchforimage(imagequery: String): Resource<ImageResponce> {

        val responce =paxbayAPI.searchforimage(imagequery)

       return responce.body()?.let {
            return@let Resource.success(it)
        } ?:Resource.error("error",null)

    }

}