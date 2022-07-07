package com.cesor.android.storesprueba1.editModule.model

import com.cesor.android.storesprueba1.StoreApplication
import com.cesor.android.storesprueba1.common.entities.StoreEntity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/****
 * Project: StoresPrueba1
 * From: com.cesor.android.storesprueba1.editModule.model
 * Created by: César Castro on 4/07/2022 at 21:21.
 ***/
class EditStoreInteractor {

    fun saveStore(storeEntity: StoreEntity, callback: (Long) ->Unit){
        doAsync {
            val newId = StoreApplication.database.storeDao().addStore(storeEntity)
            uiThread {
                callback(newId)
            }
        }
    }
    fun updateStore(storeEntity: StoreEntity, callback: (StoreEntity) ->Unit){
        doAsync {
            StoreApplication.database.storeDao().updateStore(storeEntity)
            uiThread {
                callback(storeEntity)
            }
        }
    }
}