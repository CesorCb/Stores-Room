package com.cesor.android.storesprueba1.mainModule.model

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.cesor.android.storesprueba1.StoreApplication
import com.cesor.android.storesprueba1.common.entities.StoreEntity
import com.cesor.android.storesprueba1.common.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*

/****
 * Project: StoresPrueba1
 * From: com.cesor.android.storesprueba1.mainModule.viewModel
 * Created by: César Castro on 4/07/2022 at 16:19.
 ***/
class MainInteractor {

    //Función de tipo (a)->b

    fun getStores(callback: (MutableList<StoreEntity>) -> Unit){
        val url = Constants.STORES_URL + Constants.API_PATH
        var storeList = mutableListOf<StoreEntity>()

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->

            val status = response.optInt(Constants.STATUS_PROPERTY, Constants.ERROR)
            if (status == Constants.SUCCESS){

                val jsonList = response.optJSONArray(Constants.STORES_PROPERTY)?.toString()
                if (jsonList != null){
                    val mutableListType = object : TypeToken<MutableList<StoreEntity>>(){}.type
                    storeList = Gson().fromJson<MutableList<StoreEntity>>(jsonList, mutableListType)
                    callback(storeList)
                }
            }
            callback(storeList)
        },{
            it.printStackTrace()
            callback(storeList)
        })
        StoreApplication.storeAPI.requestQueue.add(jsonObjectRequest)
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