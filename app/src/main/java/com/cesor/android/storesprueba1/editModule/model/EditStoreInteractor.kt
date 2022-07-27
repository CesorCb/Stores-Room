package com.cesor.android.storesprueba1.editModule.model

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import com.cesor.android.storesprueba1.StoreApplication
import com.cesor.android.storesprueba1.common.entities.StoreEntity
import com.cesor.android.storesprueba1.common.utils.StoresException
import com.cesor.android.storesprueba1.common.utils.TypeError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/****
 * Project: StoresPrueba1
 * From: com.cesor.android.storesprueba1.editModule.model
 * Created by: César Castro on 4/07/2022 at 21:21.
 ***/
class EditStoreInteractor {

<<<<<<< HEAD
    fun getStoreById(id: Long): LiveData<StoreEntity> {
=======
    fun getStoreById(id: Long): LiveData<StoreEntity>{
>>>>>>> 4928e1c6a1e69c228df54dff3ef6de12ca4bcb8e
        return  StoreApplication.database.storeDao().getStoreById(id)
    }

    suspend fun saveStore(storeEntity: StoreEntity) = withContext(Dispatchers.IO){
        try {
            StoreApplication.database.storeDao().addStore(storeEntity)
        } catch (e: SQLiteConstraintException){
            throw StoresException(TypeError.INSERT)
        }
    }

    suspend fun updateStore(storeEntity: StoreEntity) = withContext(Dispatchers.IO){
        try {
            val result = StoreApplication.database.storeDao().updateStore(storeEntity)
            if (result == 0) throw StoresException(TypeError.UPDATE)
        } catch (e: SQLiteConstraintException){
            throw StoresException(TypeError.UPDATE)
        }
    }
}