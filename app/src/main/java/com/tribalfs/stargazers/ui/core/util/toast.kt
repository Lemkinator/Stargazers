package com.tribalfs.stargazers.ui.core.util

import android.content.Context
import androidx.fragment.app.Fragment
import dev.oneuiproject.oneui.widget.Toast

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(msg: String)  = requireContext().toast(msg)
