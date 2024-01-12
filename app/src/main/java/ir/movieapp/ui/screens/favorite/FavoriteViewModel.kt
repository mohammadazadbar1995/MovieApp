package ir.movieapp.ui.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.movieapp.data.local.Favorite
import ir.movieapp.data.local.FavoriteDao
import ir.movieapp.data.repository.FavoriteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : ViewModel() {


    fun insertFavorite(favorite: Favorite) {
        viewModelScope.launch {
            repository.insertFavorite(favorite)
        }
    }

    fun isFavorite(mediaId: Int): LiveData<Boolean> {
        return repository.isFavorite(mediaId)
    }


    fun deleteFavoriteById(mediaId: Int) {
        viewModelScope.launch {
            repository.deleteById(mediaId)
        }
    }


    fun deleteAFavorite(favorite: Favorite) {
        viewModelScope.launch {
            repository.deleteFavorite(favorite)
        }
    }

}