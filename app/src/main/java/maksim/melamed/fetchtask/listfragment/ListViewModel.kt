package maksim.melamed.fetchtask.listfragment

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import maksim.melamed.fetchtask.models.Data
import maksim.melamed.fetchtask.repositories.Repository

/*
viewModel that retrieves data from repository with coroutineScope function
and sets values of mutableLiveData variables
while using coroutineExceptionHandler catches any exceptions from retrieving data process
and posts localizedMessage value as a String
*/
class ListViewModel: ViewModel() {

    private val repository = Repository()

    private var _data = MutableLiveData<List<Data>>()
    val data: LiveData<List<Data>> get() = _data

    private var _errorHandler = MutableLiveData<String>()
    val errorHandler: LiveData<String> get() = _errorHandler

    //coroutine exception handler, posts value to _errorHandler
    //that (errorHandler) observed by listFragment
    private val handler = CoroutineExceptionHandler { _, it ->
        _errorHandler.postValue(it.localizedMessage)
    }

    //function to retrieve data from repository, launched with exception handler
    fun getData() {
        viewModelScope.launch(handler) {
            _data.value = repository.getData()
        }
    }
}