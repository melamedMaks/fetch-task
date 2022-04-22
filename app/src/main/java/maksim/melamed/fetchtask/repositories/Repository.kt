package maksim.melamed.fetchtask.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import maksim.melamed.fetchtask.models.Data
import maksim.melamed.fetchtask.services.Service

class Repository {

    suspend fun getData(): List<Data>{
        return withContext(Dispatchers.IO) {
            val data = Service.create().getFetchData()
            data
        }
    }
}