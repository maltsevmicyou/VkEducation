package com.vk.simplerepoexample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vk.simplerepoexample.data.dto.App
import com.vk.simplerepoexample.data.dto.AppEntity
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.Executors
import androidx.core.content.edit

class RuStoreRepositoryImpl(context: Context) : RuStoreRepository {

    private companion object {
        private const val BASE_URL = "http://185.103.109.134"
        private const val LAST_TIME = "LAST_TIME"
        private const val TIME = "TIME"
    }

    // Retrofit region start
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
    private val contentType = "application/json".toMediaType()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()
    private val service = retrofit.create(ApiInterface::class.java)
    // Retrofit region end


    // Room region start
    private val mapper = Mapper()
    private val sharedPref = context.getSharedPreferences(LAST_TIME, Context.MODE_PRIVATE)

    @Database(entities = [AppEntity::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun appsDao(): AppsDao
    }

    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "app-db"
    ).build()
    private val executor = Executors.newSingleThreadExecutor()
    // Room region end

    override fun getApplications(
        onSuccess: (List<App>, type: String) -> Unit,
        onFailed: (Throwable) -> Unit,
    ) {
        if (System.currentTimeMillis() - 10_000 < sharedPref.getLong(TIME, 0)) {
            val apps = db.appsDao().getApps().map { mapper.toApp(it) }
            if (apps.isNotEmpty()) {
                onSuccess(apps, "db")
                return
            }
        }
        service.getApps().enqueue(object : Callback<List<App>> {
            override fun onResponse(
                call: Call<List<App>?>,
                response: Response<List<App>?>
            ) {
                val apps: List<App> = response.body() ?: emptyList()
                executor.execute {
                    db.appsDao().insertApss(apps.map { mapper.toAppEntity(it) })
                    sharedPref.edit {
                        putLong(TIME, System.currentTimeMillis())
                    }
                }
                onSuccess(apps, "retrofit")
            }

            override fun onFailure(
                call: Call<List<App>?>,
                t: Throwable
            ) {
                onFailed(t)
            }
        }
        )
    }

}