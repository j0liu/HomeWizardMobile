package ar.edu.itba.homewizard.ui.utils

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("DiscouragedApi")
fun Translate(context: Context, value: String): String {
    if (value.toIntOrNull() != null){
        return value
    }
    return try{
        val textId = context.resources.getIdentifier(value, "string",context.packageName)
        context.resources.getString(textId)
    }
    catch (e: Exception){
        value
    }
}