package maksim.melamed.fetchtask.listfragment

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import maksim.melamed.fetchtask.models.Data
import maksim.melamed.fetchtask.repositories.Repository

class ListViewModel: ViewModel() {

    private val repository = Repository()

    private var _data = MutableLiveData<List<Data>>()
    val data: LiveData<List<Data>> get() = _data

    private var _errorHandler = MutableLiveData<String>()
    val errorHandler: LiveData<String> get() = _errorHandler

    private val handler = CoroutineExceptionHandler { _, it ->
        _errorHandler.postValue(it.localizedMessage)
    }

    fun getData() {
        viewModelScope.launch(handler) {
            _data.value = repository.getData()
        }
    }
}