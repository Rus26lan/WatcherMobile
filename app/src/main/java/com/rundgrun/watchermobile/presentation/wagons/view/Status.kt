package com.rundgrun.watchermobile.presentation.wagons.view


import android.graphics.Color
import com.rundgrun.watchermobile.R

enum class Status(val color: Int) {
    GOOD(R.color.basic_green),
    BAD(R.color.basic_red),
    UNKNOWN(R.color.basic_gray);
}