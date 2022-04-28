package maksim.melamed.fetchtask.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import maksim.melamed.fetchtask.models.Data
import maksim.melamed.fetchtask.services.Service

/**
returns list of Data objects within suspend function
*/
class Repository {

    private val service = Service.create()

    suspend fun getData(): List<Data> {
        return withContext(Dispatchers.IO) {
            service.getFetchData()
        }
    }
}