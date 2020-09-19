package maze.run.unittesting.ui.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import maze.run.unittesting.R
import maze.run.unittesting.getOrAwaitValue
import maze.run.unittesting.launchFragmentInHiltContainer
import maze.run.unittesting.repository.FakeShoppingRepositoryAndroidTest
import maze.run.unittesting.ui.ShoppingFragmentFactory
import maze.run.unittesting.ui.ViewModels.viewmodel
import maze.run.unittestingroomretrofitmmvm.data.local.ShoppingItem
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class AddShoppingItemFragmentTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: ShoppingFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun clickonAddShoppingItemintodb_() {
        val testviewmodel=viewmodel(FakeShoppingRepositoryAndroidTest())

        launchFragmentInHiltContainer<AddShoppingItemFragment>(fragmentFactory = fragmentFactory) {
            viewmodel1=testviewmodel
        }
        Espresso.onView(ViewMatchers.withId(R.id.etShoppingItemName)).perform(replaceText("Shoppingitem"))
        Espresso.onView(ViewMatchers.withId(R.id.etShoppingItemAmount)).perform(replaceText("5"))
        Espresso.onView(ViewMatchers.withId(R.id.etShoppingItemPrice)).perform(replaceText("5.5F"))

        Espresso.onView(ViewMatchers.withId(R.id.btnAddShoppingItem)).perform(ViewActions.click())

        assertThat(testviewmodel.shoppingItem.getOrAwaitValue()).contains(ShoppingItem("Shoppingitem",5,
            5.5F,"") )
    }
    @Test
    fun clickonImagePickButton_ActiontoImagePickFragment() {
        val navController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<AddShoppingItemFragment> (fragmentFactory = fragmentFactory){
            Navigation.setViewNavController(requireView(),navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.ivShoppingImage1)).perform(ViewActions.click())
        Mockito.verify(navController).navigate(R.id.action_addShoppingItemFragment_to_imagePickFragment)

    }


}