package maksim.melamed.fetchtask.utils

import com.google.common.truth.Truth.assertThat
import maksim.melamed.fetchtask.models.Data
import maksim.melamed.fetchtask.models.SortedData
import org.junit.Test

class UtilsKtTest {

    /*
    a couple of tests to check if filterAndSortListOfItems() function returns
    expected results
*/

    @Test
    fun returns_true_if_is_list() {
        val dataList = listOf<Data>()
        val listOfSortedData = filterAndSortListOfItems(dataList)

        assertThat(listOfSortedData) is List<*>
    }

    @Test
    fun returns_true_if_object_in_list_is_sorted_data_object() {
        val dataList = mutableListOf<Data>()
        dataList += Data(0, 0, "0")
        val listOfSortedData = filterAndSortListOfItems(dataList)
        val sortedData = listOfSortedData[0]

        assertThat(sortedData.javaClass).isEqualTo(SortedData(0, dataList).javaClass)
    }

    @Test
    fun returns_true_if_list_is_not_null() {
        val dataList = mutableListOf<Data>()
        dataList += Data(0, 0, "0")
        val listOfSortedData = filterAndSortListOfItems(dataList)

        assertThat(listOfSortedData).isNotNull()
    }

    @Test
    fun returns_true_if_list_is_empty_then_name_is_empty() {
        val dataList = mutableListOf<Data>()
        dataList += Data(0, 0, "")
        val listOfSortedData = filterAndSortListOfItems(dataList)

        assertThat(listOfSortedData).isEmpty()
    }

    @Test
    fun returns_true_if_list_is_empty_then_name_is_null() {
        val dataList = mutableListOf<Data>()
        dataList += Data(0, 0, null)
        val listOfSortedData = filterAndSortListOfItems(dataList)

        assertThat(listOfSortedData).isEmpty()
    }
}