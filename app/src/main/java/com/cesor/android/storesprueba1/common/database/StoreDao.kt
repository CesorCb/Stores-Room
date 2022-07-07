package com.cesor.android.storesprueba1.common.database

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
    fun getAllStores() : MutableList<StoreEntity>

    @Query("SELECT * FROM StoreEntity WHERE id= :id")
    fun getStoreById(id: Long) : StoreEntity

    @Insert
    fun addStore(storeEntity: StoreEntity) : Long

    @Update
    fun updateStore(storeEntity: StoreEntity)

    @Delete
    fun deleteStore(storeEntity: StoreEntity)
}