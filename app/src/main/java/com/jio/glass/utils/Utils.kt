package com.jio.glass.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Outline
import android.graphics.Rect
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.ViewOutlineProvider
import android.view.inputmethod.InputMethodManager
import androidx.core.view.WindowInsetsCompat
import kotlin.math.roundToInt


val CIRCULAR_OUTLINE: ViewOutlineProvider = object : ViewOutlineProvider() {
    override fun getOutline(view: View, outline: Outline) {
        outline.setOval(
            view.paddingLeft,
            view.paddingTop,
            view.width - view.paddingRight,
            view.height - view.paddingBottom
        )
    }
}

fun dpToPx(dp: Int): Int {
    return (dp * Resources.getSystem().displayMetrics.density).toInt()
}


/**
 * get rootView
 */
fun Activity.getRootView(): View {
    return findViewById(android.R.id.content)
}

/**
 * convert dp to pixel
 */
fun Context.convertDpToPx(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        this.resources.displayMetrics
    )
}

/**
 * show keyboard
 */
fun Activity.isKeyboardOpen(): Boolean {
    val visibleBounds = Rect()
    this.getRootView().getWindowVisibleDisplayFrame(visibleBounds)
    val heightDiff = getRootView().height - visibleBounds.height()
    val marginOfError = this.convertDpToPx(50F).roundToInt()
    return heightDiff > marginOfError
}

/**
 * hide keyboard
 */
fun View.hideKeyboard(){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        windowInsetsController?.hide(WindowInsetsCompat.Type.ime())
    } else {
        val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        manager?.hideSoftInputFromWindow(windowToken, 0)
    }
}