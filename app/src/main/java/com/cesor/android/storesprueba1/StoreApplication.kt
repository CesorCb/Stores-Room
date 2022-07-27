package com.cesor.android.storesprueba1

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cesor.android.storesprueba1.common.database.StoreDatabase

/****
 * Project: StoresPrueba1
 * From: com.cesor.android.storesprueba1
 * Created by: César Castro on 23/06/2022 at 15:54.
 ***/
class StoreApplication : Application() {
    companion object{
        lateinit var database: StoreDatabase
    }
    override fun onCreate() {
        super.onCreate()

        val MIGRATION_1_2 = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE StoreEntity ADD COLUMN photoUrl TEXT NOT NULL DEFAULT ''")
            }
        }
        val MIGRATION_2_3 = object : Migration(2, 3){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE UNIQUE INDEX index_storeEntity_name ON StoreEntity(name)")
            }
        }

        database = Room.databaseBuilder(this,
            StoreDatabase::class.java,
            "StoreDatabase")
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .build()

    }
}