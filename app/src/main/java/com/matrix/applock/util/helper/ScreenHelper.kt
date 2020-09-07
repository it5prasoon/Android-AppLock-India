package com.matrix.applock.util.helper

import android.content.res.Resources

fun getScreenWidth(): Int = Resources.getSystem().getDisplayMetrics().widthPixels

fun getScreenHeight(): Int = Resources.getSystem().getDisplayMetrics().heightPixels