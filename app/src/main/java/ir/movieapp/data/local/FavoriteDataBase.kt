package ir.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class FavoriteDataBase : RoomDatabase(){

    abstract val dao: FavoriteDao
}