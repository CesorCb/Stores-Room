package com.cesor.android.storesprueba1.mainModule.model

import com.cesor.android.storesprueba1.StoreApplication
import com.cesor.android.storesprueba1.common.entities.StoreEntity
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/****
 * Project: StoresPrueba1
 * From: com.cesor.android.storesprueba1.mainModule.viewModel
 * Created by: César Castro on 4/07/2022 at 16:19.
 ***/
class MainInteractor {

    //Función de tipo (a)->b
    fun getStores(callback : (MutableList<StoreEntity>) -> Unit){
        doAsync {
            val storesList = StoreApplication.database.storeDao().getAllStores()
            uiThread {
                callback(storesList)
            }
        }
    }

    fun deleteStore(storeEntity: StoreEntity, callback: (StoreEntity) ->Unit){
        doAsync {
            StoreApplication.database.storeDao().deleteStore(storeEntity)
            uiThread {
                callback(storeEntity)
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