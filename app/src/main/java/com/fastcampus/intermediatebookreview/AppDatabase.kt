package com.fastcampus.intermediatebookreview

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fastcampus.intermediatebookreview.dao.HistoryDao
import com.fastcampus.intermediatebookreview.model.History

@Database(entities = [History::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}