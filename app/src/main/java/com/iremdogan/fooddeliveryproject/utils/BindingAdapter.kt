package com.iremdogan.fooddeliveryproject.utils

import android.widget.ImageView
import android.widget.TextView
import com.iremdogan.fooddeliveryproject.R

//Extension for ImageView to change its background

fun ImageView.setIndicatorBackground(isSelected: Boolean) {
    if (isSelected) {
        this.background = this.context.getDrawable(R.drawable.ic_onboarding_indicator_selected)
    } else {
        this.background = this.context.getDrawable(R.drawable.ic_onbpoarding_indicator_unselected)
    }
}