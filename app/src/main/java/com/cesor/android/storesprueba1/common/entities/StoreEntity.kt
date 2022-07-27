package com.cesor.android.storesprueba1.common.entities

import androidx.room.Entity
<<<<<<< HEAD
import androidx.room.Ignore
=======
>>>>>>> 4928e1c6a1e69c228df54dff3ef6de12ca4bcb8e
import androidx.room.Index
import androidx.room.PrimaryKey

/****
 * Project: StoresPrueba1
 * From: com.cesor.android.storesprueba1
 * Created by: César Castro on 22/06/2022 at 19:09.
 ***/
@Entity(tableName = "StoreEntity", indices = [Index(value = ["name"], unique = true)])
<<<<<<< HEAD
data class StoreEntity @Ignore constructor(@PrimaryKey(autoGenerate = true) var id: Long = 0,
                                           var name: String,
                                           var phone: String,
                                           var website: String = "",
                                           var photoUrl: String,
                                           var isFavorite: Boolean = false){
=======

data class StoreEntity (@PrimaryKey(autoGenerate = true) var id: Long = 0,
                       var name: String,
                       var phone: String,
                       var website: String = "",
                       var photoUrl: String,
                       var isFavorite: Boolean = false){
>>>>>>> 4928e1c6a1e69c228df54dff3ef6de12ca4bcb8e

    constructor() : this(name = "", phone = "", photoUrl = "")
}
