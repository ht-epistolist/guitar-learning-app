package com.example.hope.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hope.data.room.convrters.DateConverter
import com.example.hope.data.room.dao.CurrentUserDao
import com.example.hope.data.room.entity.CreatedSongs
import com.example.hope.data.room.entity.CurrentUser
import com.example.hope.data.room.entity.Genres
import com.example.hope.data.room.entity.MySongs
import com.example.hope.data.room.entity.PublishedSongs
import com.example.hope.data.room.entity.RecentSongs
import com.example.hope.data.room.entity.SavedSongs
import com.example.hope.data.room.entity.SearchedSongs
import com.example.hope.data.room.entity.SearchedWords
import com.example.hope.data.room.entity.Songs
import com.example.hope.data.room.entity.Users

@TypeConverters(value = [DateConverter::class])
@Database(
    entities = [CurrentUser::class, CreatedSongs::class, Genres::class, MySongs::class, PublishedSongs::class, RecentSongs::class, SavedSongs::class, SearchedSongs::class, SearchedWords::class, Songs::class, Users::class],//specifying the entities (tables) of the database
    version = 1 //specify the version in case of the future migration to a new database
)
abstract class LocalDatabase: RoomDatabase() {//enter the data access objects for the tables
    abstract val dao: CurrentUserDao
}