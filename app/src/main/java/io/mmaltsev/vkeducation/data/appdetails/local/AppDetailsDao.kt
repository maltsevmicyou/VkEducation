package io.mmaltsev.vkeducation.data.appdetails.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDetailsDao {
    @Query("SELECT * FROM app_details WHERE id = :id")
    fun getAppDetails(id: String): Flow<AppDetailsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAppDetails(appDetails: AppDetailsEntity)

//    @Query("DELETE FROM app_details WHERE id = :id")
//    suspend fun deleteAppDetails(id: String)
}