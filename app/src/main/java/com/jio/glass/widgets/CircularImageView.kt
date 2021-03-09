package com.jio.glass.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.jio.glass.utils.CIRCULAR_OUTLINE

class CircularImageView(
    context: Context,
    attrs: AttributeSet
) : AppCompatImageView(context, attrs) {


    init {
        outlineProvider = CIRCULAR_OUTLINE
        clipToOutline = true
    }

}