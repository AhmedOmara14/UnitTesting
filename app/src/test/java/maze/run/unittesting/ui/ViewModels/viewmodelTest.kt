package maze.run.unittesting.ui.ViewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.ViewModelProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import maze.run.unittesting.getOrAwaitValueTest
import maze.run.unittesting.mainCoroutineRule
import maze.run.unittesting.repository.FakeShoppingRepository
import maze.run.unittesting.repository.Repository
import maze.run.unittesting.ui.other.Status
import org.junit.After
import org.junit.Before
import org.junit.Rule

import org.junit.Test

@ExperimentalCoroutinesApi
class viewmodelTest {


    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutinerule= mainCoroutineRule()
    lateinit var viewmodel : viewmodel

    @Before
    fun setUp() {
        viewmodel=viewmodel(FakeShoppingRepository())
    }


    @Test
    fun `insert shopping item into database with field item ,return error`(){
        viewmodel.
        insertShoppingItemtoDB("jeans","","102.0")

        val value=viewmodel.insertShoppingItemintoDB.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert shopping item into database with valid input`(){
        viewmodel.insertShoppingItemtoDB("jeans","5","102.0")

        val value=viewmodel.insertShoppingItemintoDB.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(Status.SUCCESS)
    }

}