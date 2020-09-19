package maze.run.unittesting.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import maze.run.unittesting.HiltTestRunner
import maze.run.unittesting.getOrAwaitValue
import maze.run.unittesting.launchFragmentInHiltContainer
import maze.run.unittesting.ui.fragment.ShoppingFragment
import maze.run.unittestingroomretrofitmmvm.data.local.ShoppingDao
import maze.run.unittestingroomretrofitmmvm.data.local.ShoppingDatabase
import maze.run.unittestingroomretrofitmmvm.data.local.ShoppingItem
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ShoppingDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
     lateinit var ShoppingItemDatabase: ShoppingDatabase

    private lateinit var dao: ShoppingDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = ShoppingItemDatabase.shoppingDao()
    }

    @After
    fun teardown() {
        ShoppingItemDatabase.close()
    }


    @Test
    fun insertShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem("name", 1, 1f, "url", id = 1)
        dao.insertShoppingItem(shoppingItem)

        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).contains(shoppingItem)
    }


    @Test
    fun deleteShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem("name", 1, 1f, "url", id = 1)
        dao.insertShoppingItem(shoppingItem)
        dao.deleteShoppingItem(shoppingItem)

        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).doesNotContain(shoppingItem)
    }

    @Test
    fun observetotlalprice() = runBlockingTest {
        val shoppingItem = ShoppingItem("name", 1, 1f, "url", id = 1)
        val shoppingItem1 = ShoppingItem("name", 2, 1f, "url", id = 2)
        val shoppingItem2 = ShoppingItem("name", 3, 1f, "url", id = 3)

        dao.insertShoppingItem(shoppingItem)
        dao.insertShoppingItem(shoppingItem1)
        dao.insertShoppingItem(shoppingItem2)

        val allShoppingItems = dao.observeTotalPrice().getOrAwaitValue()

        assertThat(allShoppingItems).isEqualTo(1 * 1f + 2 * 1f + 3 * 1f)
    }


}