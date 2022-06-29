package com.cesor.android.storesprueba1

/****
 * Project: StoresPrueba1
 * From: com.cesor.android.storesprueba1
 * Created by: César Castro on 27/06/2022 at 13:37.
 ***/
interface MainAux {
    fun hideFab(isVisible: Boolean = false)
    fun addStore(storeEntity: StoreEntity)
    fun updateStore(storeEntity: StoreEntity)
}