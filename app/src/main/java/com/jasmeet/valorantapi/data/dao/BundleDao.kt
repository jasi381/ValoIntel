package com.jasmeet.valorantapi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jasmeet.valorantapi.data.model.local.BundlesEntity

@Dao
interface BundleDao {
    @Query("SELECT * FROM bundles")
    suspend fun getAllBundles(): List<BundlesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBundle(bundles: List<BundlesEntity>)
}