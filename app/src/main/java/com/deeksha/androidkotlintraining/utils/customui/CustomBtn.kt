package com.deeksha.androidkotlintraining.utils.customui


import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.deeksha.androidkotlintraining.R


class CustomBtn (context: Context, attrs: AttributeSet) : AppCompatButton(context, attrs) {
    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomBtn,
            0, 0
        ).apply {

            try {
                background = context.getDrawable(R.drawable.btn_background)
                typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)

               // textSize = 30.0f

            } finally {
                recycle()
            }
        }
    }
}