package maze.run.unittesting.ui.ViewModels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import maze.run.unittesting.remote.respnoces.ImageResponce
import maze.run.unittesting.repository.RepositoryInterface
import maze.run.unittesting.other.Resource
import maze.run.unittestingroomretrofitmmvm.data.local.ShoppingItem

class viewmodel
@ViewModelInject constructor(
    val repository: RepositoryInterface
):ViewModel() {
    private val shoppingItem = repository.observeAllItems()
    private val totalprice = repository.observeTotalPrice()

    private val _image = MutableLiveData<Resource<ImageResponce>>()
    val images: LiveData<Resource<ImageResponce>> = _image

    private val _curImageUrl = MutableLiveData<String>()
    val curImageUrl: LiveData<String> = _curImageUrl

    private val _insertShoppingItemintoDB = MutableLiveData<Resource<ShoppingItem>>()
    val insertShoppingItemintoDB: LiveData<Resource<ShoppingItem>> = _insertShoppingItemintoDB

    private val _deleteShoppingItemfromDB = MutableLiveData<Resource<ShoppingItem>>()
    val deleteShoppingItemfromDB: LiveData<Resource<ShoppingItem>> = _deleteShoppingItemfromDB


    fun setCurImageUrl(url: String) {
        _curImageUrl.postValue(url)
    }

     fun deleteShoppingItemfromDB(shoppingItem: ShoppingItem)=viewModelScope.launch{
        repository.delete(shoppingItem)
    }

     fun insertShoppingItemintoDB(shoppingItem: ShoppingItem)= viewModelScope.launch{
        repository.insert(shoppingItem)
    }

    fun insertShoppingItemtoDB(name:String, amountstring :String, price:String){
         if (name.isEmpty() || amountstring.isEmpty() || price.isEmpty()){
             _insertShoppingItemintoDB.postValue(Resource.error("the field must not be empty",null))
         }

          var amount=try {
              amountstring.toInt()
          }catch (e:Exception){
              _insertShoppingItemintoDB.postValue(Resource.error("please enter a valid amount",null))
              return
          }
         val shoppingItem =ShoppingItem(name,amount,price.toFloat(),_curImageUrl.value.toString())
         insertShoppingItemintoDB(shoppingItem)
         setCurImageUrl("")
         _insertShoppingItemintoDB.postValue(Resource.success(shoppingItem))
    }

    fun searchforimage(imageQuery :String){
      if (imageQuery.isEmpty()){
          return
      }
        _image.value=Resource.loading(null)
        viewModelScope.launch {
            val responce =repository.searchforimage(imageQuery)
            _image.value=responce
        }
    }

}