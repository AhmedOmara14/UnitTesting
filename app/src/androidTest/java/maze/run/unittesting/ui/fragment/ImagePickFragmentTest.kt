package maze.run.unittesting.ui.fragment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import maze.run.unittesting.R
import maze.run.unittesting.getOrAwaitValue
import maze.run.unittesting.launchFragmentInHiltContainer
import maze.run.unittesting.ui.ShoppingFragmentFactory
import maze.run.unittesting.ui.ViewModels.viewmodel
import maze.run.unittesting.ui.adapter.adapterforImage
import maze.run.unittesting.repository.FakeShoppingRepositoryAndroidTest
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import javax.inject.Inject

@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ImagePickFragmentTest {

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

    val testImageurl ="Test"
    val testviewmodel = viewmodel(FakeShoppingRepositoryAndroidTest())
    @Test
    fun clickonImage_popBackStackAndSetImageUrl() {
        val navController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<ImagePickFragment>(fragmentFactory = fragmentFactory) {
            Navigation.setViewNavController(requireView(), navController)
            adapterforImage1.images = listOf(testImageurl)
            viewmodel1 =testviewmodel
        }

        Espresso.onView(ViewMatchers.withId(R.id.rvImages)).perform(
            RecyclerViewActions.actionOnItemAtPosition<adapterforImage.viewholder>(
                0,
                click()
            )
        )
        verify(navController).popBackStack()

        assertThat(testviewmodel.curImageUrl.getOrAwaitValue()).isEqualTo(testImageurl)
    }
}