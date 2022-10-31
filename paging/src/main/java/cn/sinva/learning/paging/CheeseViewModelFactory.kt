package cn.sinva.learning.paging

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CheeseViewModelFactory(
    private val app: Application
) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CheeseViewModel::class.java)) {
            val cheeseDao = CheeseDb.get(app).cheeseDao()
            return CheeseViewModel(cheeseDao) as T
        }
        throw IllegalArgumentException("Unkown ViewModel class")
    }
}