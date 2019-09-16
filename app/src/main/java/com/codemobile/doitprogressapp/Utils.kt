package com.codemobile.doitprogressapp

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources


fun dpToPx(dp: Int) = (dp * Resources.getSystem().displayMetrics.density).toInt()

object Utils {
    fun scanForActivity(context: Context?): Activity? {
        return when (context) {
            null -> null
            is Activity -> context
            is ContextWrapper -> scanForActivity(context.baseContext)
            else -> null
        }

    }

}