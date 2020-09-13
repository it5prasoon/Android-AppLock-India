package com.webers.applock.service

import com.webers.applock.data.database.pattern.PatternDot
import com.webers.applock.util.helper.PatternChecker
import io.reactivex.functions.BiFunction

class PatternValidatorFunction : BiFunction<List<PatternDot>, List<PatternDot>, Boolean> {
    override fun apply(t1: List<PatternDot>, t2: List<PatternDot>): Boolean {
        return PatternChecker.checkPatternsEqual(t1, t2)
    }
}