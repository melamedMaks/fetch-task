package maksim.melamed.fetchtask.utils

import com.google.common.truth.Truth.assertThat
import maksim.melamed.fetchtask.models.Data
import maksim.melamed.fetchtask.models.SortedData
import org.junit.Test

class UtilsKtTest {

    /**
    a couple of tests to check if filterAndSortListOfItems() function returns
    expected results
*/

    @Test
    fun data_list_is_list() {
        val dataList = listOf<Data>()
        val listOfSortedData = filterAndSortListOfItems(dataList)

        assertThat(listOfSortedData) is List<*>
    }

    @Test
    fun object_in_list_is_type_of_sorted_data() {
        val dataList = mutableListOf<Data>()
        dataList += Data(0, 0, "0")
        val listOfSortedData = filterAndSortListOfItems(dataList)
        val sortedData = listOfSortedData[0]

        assertThat(sortedData.javaClass).isEqualTo(SortedData(0, dataList).javaClass)
    }

    @Test
    fun data_list_is_not_null() {
        val dataList = mutableListOf<Data>()
        dataList += Data(0, 0, "0")
        val listOfSortedData = filterAndSortListOfItems(dataList)

        assertThat(listOfSortedData).isNotNull()
    }

    @Test
    fun data_list_is_empty_when_name_is_empty() {
        val dataList = mutableListOf<Data>()
        dataList += Data(0, 0, "")
        val listOfSortedData = filterAndSortListOfItems(dataList)

        assertThat(listOfSortedData).isEmpty()
    }

    @Test
    fun data_list_is_empty_when_name_is_null() {
        val dataList = mutableListOf<Data>()
        dataList += Data(0, 0, null)
        val listOfSortedData = filterAndSortListOfItems(dataList)

        assertThat(listOfSortedData).isEmpty()
    }
}