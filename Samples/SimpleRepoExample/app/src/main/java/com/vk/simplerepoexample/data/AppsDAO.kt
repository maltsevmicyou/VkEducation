package com.vk.simplerepoexample.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vk.simplerepoexample.data.dto.AppEntity

@Dao
interface AppsDao {
    @Query("SELECT * FROM AppEntity")
    fun getApps(): List<AppEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertApss(apps: List<AppEntity>)
}
