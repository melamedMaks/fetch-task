package maksim.melamed.fetchtask.repositories

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import maksim.melamed.fetchtask.models.Data
import maksim.melamed.fetchtask.services.Service

import org.junit.Test

    /**
tests service and repository method for receiving expected results
*/
class RepositoryTest {

    private val repository = Repository()

    @Test
    fun received_data_from_service_is_not_null() {
        val data = runBlocking {
            Service.create().getFetchData()
        }
        assertThat(data).isNotNull()
    }

    @Test
    fun list_of_data_is_not_empty() {
        val data = runBlocking {
            repository.getData()
        }
        assertThat(data).isNotEmpty()
    }

    @Test
    fun list_of_data_is_not_null() {
        val data = runBlocking {
            repository.getData()
        }
        assertThat(data).isNotNull()
    }

    @Test
    fun list_of_data_is_list() {
        val data = runBlocking {
            repository.getData()
        }
        assertThat(data) is List<*>
    }

    @Test
    fun item_in_list_is_type_of_data_class() {
        val data = runBlocking {
            repository.getData()
        }
        val item = data[0]
        assertThat(item.javaClass).isEqualTo(Data(0,0,"").javaClass)
    }
}