package maksim.melamed.fetchtask

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.Animation

//custom view animation
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