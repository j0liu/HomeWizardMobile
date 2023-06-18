package ar.edu.itba.homewizard

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "Boot completed.")
    }

    companion object {
        private const val TAG = "BootCompletedReceiver"
    }
}