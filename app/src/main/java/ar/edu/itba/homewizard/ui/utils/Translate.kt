package ar.edu.itba.homewizard.ui.utils

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("DiscouragedApi")
fun Translate(context: Context, value: String): String {
    if (value.toIntOrNull() != null){
        return value
    }
    val textId = context.resources.getIdentifier(value, "string",context.packageName)
    return context.resources.getString(textId)
}