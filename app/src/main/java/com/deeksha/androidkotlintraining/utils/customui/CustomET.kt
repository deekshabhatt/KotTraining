package com.deeksha.androidkotlintraining.utils.customui

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.deeksha.androidkotlintraining.R


class CustomET(context: Context, attrs: AttributeSet) : AppCompatEditText(context, attrs) {
    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomET,
            0, 0
        ).apply {

            try {
                background = context.getDrawable(R.drawable.edit_text_bg)
                typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
                //textSize = 30.0f
            } finally {
                recycle()
            }
        }
    }
}