package com.cesor.android.storesprueba1.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cesor.android.storesprueba1.common.entities.StoreEntity

/****
 * Project: StoresPrueba1
 * From: com.cesor.android.storesprueba1
 * Created by: César Castro on 23/06/2022 at 15:50.
 ***/
@Database(entities = [StoreEntity::class], version = 2)
abstract class StoreDatabase : RoomDatabase(){
    abstract fun storeDao() : StoreDao
}