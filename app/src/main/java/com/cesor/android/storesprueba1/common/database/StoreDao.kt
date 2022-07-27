package com.cesor.android.storesprueba1.common.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cesor.android.storesprueba1.common.entities.StoreEntity

/****
 * Project: StoresPrueba1
 * From: com.cesor.android.storesprueba1
 * Created by: César Castro on 23/06/2022 at 15:47.
 ***/
@Dao
interface StoreDao{
    @Query("SELECT * FROM StoreEntity")
    fun getAllStores() : LiveData<MutableList<StoreEntity>>

    @Query("SELECT * FROM StoreEntity where id = :id")
    fun getStoreById(id: Long) : LiveData<StoreEntity>

    @Insert
    suspend fun addStore(storeEntity: StoreEntity) : Long

    @Update
    suspend fun updateStore(storeEntity: StoreEntity): Int

    @Delete
    suspend fun deleteStore(storeEntity: StoreEntity): Int
}