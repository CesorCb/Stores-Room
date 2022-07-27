package com.cesor.android.storesprueba1.editModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesor.android.storesprueba1.common.entities.StoreEntity
import com.cesor.android.storesprueba1.common.utils.StoresException
import com.cesor.android.storesprueba1.common.utils.TypeError
import com.cesor.android.storesprueba1.editModule.model.EditStoreInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/****
 * Project: StoresPrueba1
 * From: com.cesor.android.storesprueba1.editModule.viewModel
 * Created by: César Castro on 6/07/2022 at 20:30.
 ***/
class EditStoreViewModel :ViewModel() {
    private var storeId: Long = 0
    private val showFab = MutableLiveData<Boolean>()
    private val result = MutableLiveData<Any>()

    private val interactor: EditStoreInteractor = EditStoreInteractor()

    private val typeError: MutableLiveData<TypeError> = MutableLiveData()

    fun setTypeError(typeError: TypeError){
        this.typeError.value = typeError
    }

    fun getTypeError(): MutableLiveData<TypeError> = typeError

    fun setStoreSelected(storeEntity: StoreEntity){
        storeId = storeEntity.id
    }

<<<<<<< HEAD
    fun getStoreSelected(): LiveData<StoreEntity> {
=======
    fun getStoreSelected(): LiveData<StoreEntity>{
>>>>>>> 4928e1c6a1e69c228df54dff3ef6de12ca4bcb8e
        return interactor.getStoreById(storeId)
    }

    fun setShowFab(isVisible: Boolean){
        showFab.value = isVisible
    }

<<<<<<< HEAD
    fun getShowFab(): LiveData<Boolean> {
=======
    fun getShowFab(): LiveData<Boolean>{
>>>>>>> 4928e1c6a1e69c228df54dff3ef6de12ca4bcb8e
        return showFab
    }

    fun setResult(value: Any){
        result.value = value
    }

<<<<<<< HEAD
    fun getResult(): LiveData<Any> {
=======
    fun getResult(): LiveData<Any>{
>>>>>>> 4928e1c6a1e69c228df54dff3ef6de12ca4bcb8e
        return result
    }

    fun saveStore(storeEntity: StoreEntity){
        executeAction(storeEntity) { interactor.saveStore(storeEntity) }
    }

    fun updateStore(storeEntity: StoreEntity){
        executeAction(storeEntity) { interactor.updateStore(storeEntity) }
    }

    private fun executeAction(storeEntity: StoreEntity, block: suspend () -> Unit): Job {
        return viewModelScope.launch {
<<<<<<< HEAD
=======

>>>>>>> 4928e1c6a1e69c228df54dff3ef6de12ca4bcb8e
            try {
                block()
                result.value = storeEntity
            } catch (e: StoresException){
                typeError.value = e.typeError
            }
        }
    }
}