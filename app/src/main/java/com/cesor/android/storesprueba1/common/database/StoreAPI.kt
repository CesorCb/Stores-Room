package com.cesor.android.storesprueba1.common.database

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/****
 * Project: StoresPrueba1
 * From: com.cesor.android.storesprueba1.common.database
 * Created by: César Castro on 11/07/2022 at 08:30.
 ***/
class StoreAPI constructor(context: Context){
    companion object{
        @Volatile
        private var INSTANCE : StoreAPI? = null
        fun getInstance(context: Context) = INSTANCE ?: synchronized(this){
            INSTANCE ?: StoreAPI(context).also { INSTANCE = it }
        }
    }
    val requestQueue : RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>){
        requestQueue.add(req)
    }

}