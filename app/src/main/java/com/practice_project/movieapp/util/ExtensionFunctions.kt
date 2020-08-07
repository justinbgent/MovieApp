package com.practice_project.movieapp.util

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import com.google.android.material.button.MaterialButton

fun Activity.hideSoftKeyboard(){
    val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
}

fun MaterialButton.disableButton(){
    this.isFocusable = false
    this.alpha = .5f
}

fun MaterialButton.enableButton(){
    this.isFocusable = true
    this.alpha = 1f
}