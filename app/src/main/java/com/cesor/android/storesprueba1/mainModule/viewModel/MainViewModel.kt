package com.cesor.android.storesprueba1.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cesor.android.storesprueba1.common.entities.StoreEntity
import com.cesor.android.storesprueba1.common.utils.Constants
import com.cesor.android.storesprueba1.mainModule.model.MainInteractor

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
    private val showProgress: MutableLiveData<Boolean> = MutableLiveData()

    private val stores : MutableLiveData<MutableList<StoreEntity>> by lazy {
        MutableLiveData<MutableList<StoreEntity>>().also {
            loadStores()
        }
    }

    fun isShowProgress() : LiveData<Boolean>{
        return showProgress
    }

    fun getStores() : LiveData<MutableList<StoreEntity>>{
        return  stores
    }
    private fun loadStores(){
//      Uso de la función de tipo:
        showProgress.value = Constants.SHOW
        interactor.getStores {
            showProgress.value = Constants.HIDE
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