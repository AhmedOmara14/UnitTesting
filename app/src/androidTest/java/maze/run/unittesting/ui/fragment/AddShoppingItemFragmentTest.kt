package maze.run.unittesting.ui.fragment

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import maze.run.unittesting.R
import maze.run.unittesting.launchFragmentInHiltContainer
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class AddShoppingItemFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }


    @Test
    fun clickonImagePickButton_ActiontoImagePickFragment() {
        val navController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<AddShoppingItemFragment> {
            Navigation.setViewNavController(requireView(),navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.ivShoppingImage1)).perform(ViewActions.click())
        Mockito.verify(navController).navigate(R.id.action_addShoppingItemFragment_to_imagePickFragment)

    }
}