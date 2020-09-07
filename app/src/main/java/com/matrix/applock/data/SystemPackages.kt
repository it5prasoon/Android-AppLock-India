package com.matrix.applock.data

object SystemPackages {
    fun getSystemPackages(): List<String> {
        return arrayListOf<String>().apply {
            add("com.android.packageinstaller")
        }
    }
}