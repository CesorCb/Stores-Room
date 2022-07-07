package com.cesor.android.storesprueba1.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cesor.android.storesprueba1.common.entities.StoreEntity

/****
 * Project: StoresPrueba1
 * From: com.cesor.android.storesprueba1.mainModule.viewModel
 * Created by: César Castro on 4/07/2022 at 15:26.
 ***/
class MainViewModel : ViewModel() {
    private var storeList : MutableList<StoreEntity>
    private var interactor : MainInteractor

    init {
        storeList = mutableListOf()
        interactor = MainInteractor()
    }
    private val stores : MutableLiveData<List<StoreEntity>> by lazy {
        MutableLiveData<List<StoreEntity>>().also {
            loadStores()
        }
    }

    fun getStores() : LiveData<List<StoreEntity>>{
        return  stores
    }
    private fun loadStores(){
//      Uso de la función de tipo:
        interactor.getStores {
            stores.value = it
            storeList = it
        }
    }

    fun deleteStore(storeEntity: StoreEntity){
        interactor.deleteStore(storeEntity) {
            val index = storeList.indexOf(storeEntity)
            if (index != -1){
                storeList.removeAt(index)
                stores.value = storeList
            }
        }
    }
    fun updateStore(storeEntity: StoreEntity){
        storeEntity.isFavorite = !storeEntity.isFavorite

        interactor.updateStore(storeEntity) {
            val index = storeList.indexOf(storeEntity)
            if (index != -1){
                storeList[index] = storeEntity
                stores.value = storeList
            }
        }
    }
}