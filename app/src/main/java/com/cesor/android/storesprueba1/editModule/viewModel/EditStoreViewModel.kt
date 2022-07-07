package com.cesor.android.storesprueba1.editModule.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cesor.android.storesprueba1.common.entities.StoreEntity
import com.cesor.android.storesprueba1.editModule.model.EditStoreInteractor

/****
 * Project: StoresPrueba1
 * From: com.cesor.android.storesprueba1.editModule.viewModel
 * Created by: César Castro on 6/07/2022 at 20:30.
 ***/
class EditStoreViewModel : ViewModel() {

    private val storeSelected = MutableLiveData<StoreEntity>()
    private val showFab = MutableLiveData<Boolean>()
    private val result = MutableLiveData<Any>()

    private val interactor : EditStoreInteractor

    init {
        interactor = EditStoreInteractor()
    }
    fun setStoreSelected(storeEntity: StoreEntity){
        storeSelected.value = storeEntity
    }
    fun getStoreSelected() : MutableLiveData<StoreEntity>{
        return storeSelected
    }

    fun setShowFab(isVisible: Boolean){
        showFab.value = isVisible
    }
    fun getShowFab(): MutableLiveData<Boolean>{
        return showFab
    }

    fun setResult(value: Any){
        result.value = value
    }
    fun getResult(): MutableLiveData<Any>{
        return result
    }

    fun saveStore(storeEntity: StoreEntity){
        interactor.saveStore(storeEntity){ newId ->
            result.value = newId
        }
    }
    fun updateStore(storeEntity: StoreEntity){
        interactor.updateStore(storeEntity){ storeUpdated ->
            result.value = storeUpdated
        }
    }
}