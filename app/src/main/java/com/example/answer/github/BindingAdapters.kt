package com.example.answer.github

import android.content.Context
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @BindingAdapter("hideKeyboardOnInputDone")
    @JvmStatic fun hideKeyboardOnInputDone(view: ImageView, enabled: Boolean) {
        if (!enabled) return
        view.setOnClickListener {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE)
                    as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}