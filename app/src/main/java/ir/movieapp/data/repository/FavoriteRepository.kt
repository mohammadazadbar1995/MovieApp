package ir.movieapp.data.repository

import androidx.lifecycle.LiveData
import ir.movieapp.data.local.Favorite
import ir.movieapp.data.local.FavoriteDao
import ir.movieapp.data.local.FavoriteDataBase
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val favoriteDataBase: FavoriteDataBase
) {

    suspend fun insertFavorite(favorite: Favorite) {
        favoriteDataBase.dao.insertFavorite(favorite)
    }


    fun isFavorite(mediaId: Int): LiveData<Boolean>{
        return favoriteDataBase.dao.isFavorite(mediaId)
    }


    suspend fun deleteById(mediaId: Int) {
        favoriteDataBase.dao.deleteFavoriteById(mediaId)
    }
    suspend fun deleteFavorite(favorite: Favorite) {
        favoriteDataBase.dao.deleteAFavorite(favorite)
    }

    fun getAllFavorites(): LiveData<List<Favorite>> {
        return favoriteDataBase.dao.getAllFavorites()
    }

    suspend fun deleteAllFavorites() {
        favoriteDataBase.dao.deleteAllFavorites()
    }
}