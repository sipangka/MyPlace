package com.assignment.myplace.customviews

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.widget.CheckBox
import android.widget.RadioButton

import com.assignment.myplace.R


@SuppressLint("AppCompatCustomView")
class CenterCheckBox(context: Context, attrs: AttributeSet) : CheckBox(context, attrs) {
    internal var buttonDrawable: Drawable? = null

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CompoundButton, 0, 0)
        //buttonDrawable = a.getDrawable(1);
        buttonDrawable = a.getDrawable(a.getIndex(0))
        setButtonDrawable(android.R.color.transparent)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        setButtonDrawable(android.R.color.transparent)

        if (buttonDrawable != null) {
            buttonDrawable!!.state = drawableState
            val verticalGravity = gravity and Gravity.VERTICAL_GRAVITY_MASK
            val height = buttonDrawable!!.intrinsicHeight

            var y = 0

            when (verticalGravity) {
                Gravity.BOTTOM -> y = getHeight() - height
                Gravity.CENTER_VERTICAL -> y = (getHeight() - height) / 2
            }

            val buttonWidth = buttonDrawable!!.intrinsicWidth
            val buttonLeft = (width - buttonWidth) / 2
            buttonDrawable!!.setBounds(buttonLeft, y, buttonLeft + buttonWidth, y + height)
            buttonDrawable!!.draw(canvas)
        }
    }

}