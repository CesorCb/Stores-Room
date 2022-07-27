package com.cesor.android.storesprueba1.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesor.android.storesprueba1.common.entities.StoreEntity
import com.cesor.android.storesprueba1.common.utils.Constants
import com.cesor.android.storesprueba1.common.utils.StoresException
import com.cesor.android.storesprueba1.common.utils.TypeError
import com.cesor.android.storesprueba1.mainModule.model.MainInteractor
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/****
 * Project: StoresPrueba1
 * From: com.cesor.android.storesprueba1.mainModule.viewModel
 * Created by: César Castro on 4/07/2022 at 15:26.
 ***/
class MainViewModel: ViewModel() {
    private var interactor: MainInteractor = MainInteractor()


    private val typeError: MutableLiveData<TypeError> = MutableLiveData()

    private val showProgress: MutableLiveData<Boolean> = MutableLiveData()

    private val stores = interactor.stores

    fun getTypeError(): MutableLiveData<TypeError> = typeError

    fun getStores(): LiveData<MutableList<StoreEntity>>{
        return stores
    }

    fun isShowProgress(): LiveData<Boolean>{
        return showProgress
    }

    fun deleteStore(storeEntity: StoreEntity){
        executeAction { interactor.deleteStore(storeEntity) }
    }

    fun updateStore(storeEntity: StoreEntity){
        storeEntity.isFavorite = !storeEntity.isFavorite
        executeAction { interactor.updateStore(storeEntity) }
    }

    private fun executeAction(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            showProgress.value = Constants.SHOW

            try {
                block()
            } catch (e: StoresException){
                typeError.value = e.typeError
            } finally {
                showProgress.value = Constants.HIDE
            }
        }
    }
}