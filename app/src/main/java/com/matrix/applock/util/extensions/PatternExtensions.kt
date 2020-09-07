package com.matrix.applock.util.extensions

import com.andrognito.patternlockview.PatternLockView
import com.matrix.applock.data.database.pattern.PatternDot

fun List<PatternLockView.Dot>.convertToPatternDot(): List<PatternDot> {
    val patternDotList: ArrayList<PatternDot> = arrayListOf()
    forEach {
        patternDotList.add(PatternDot(column = it.column, row = it.row))
    }
    return patternDotList
}