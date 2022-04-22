package maksim.melamed.fetchtask.utils

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.Animation
import maksim.melamed.fetchtask.models.Data
import maksim.melamed.fetchtask.models.SortedData

//animates the arrow view, makes it move by translation Y from 0dp to 18dp
// and fades alpha in and out from 0.0f to 1.0f with 1 second duration
//the animation set reversible in infinite loop
//while using ObjectAnimator
fun animateArrow(view: View) {
    val trY = PropertyValuesHolder.ofFloat("translationY",18f)
    val alpha = PropertyValuesHolder.ofFloat("alpha",1f)

    ObjectAnimator.ofPropertyValuesHolder(view, trY, alpha).apply {
        duration = 1000
        repeatMode = ValueAnimator.REVERSE
        repeatCount = Animation.INFINITE
        start()
    }
}

//method to sort data according to task requirements, returns a list of SortedData objects
// * see the tests
fun filterAndSortListOfItems(list: List<Data>): List<SortedData> {
    //filters out any objects that's name field is null or empty
    var data = list.filter { !it.name.isNullOrEmpty() }
    //sorts objects by list id and by name with chained function
    data = data.sortedWith(compareBy<Data> { it.listId }.then(compareBy { it.name }))
    //groups objects by list id while using hash map and converts back to a new list
    val listOfData = data.groupBy { it.listId }.toList()
    //creating a list of SortedData objects
    val sortedData = mutableListOf<SortedData>()
    //converting a list of data to list of SortedData objects for easy read code reason
    //while iterating by index through the list
    for ((i, pair) in listOfData.withIndex()) {
        sortedData.add(i, SortedData(pair.first, pair.second))
    }
    return sortedData
}