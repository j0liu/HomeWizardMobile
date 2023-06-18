package ar.edu.itba.homewizard

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class SkipNotificationReceiver(private val deviceId: String) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        println("RECEIVED!!!")
        if (intent.action.equals(MyIntent.SHOW_NOTIFICATION) &&
            intent.getStringExtra(MyIntent.DEVICE_ID).equals(deviceId)
        ) {
            Log.d(TAG, "Skipping notification send ($deviceId)")
            abortBroadcast()
        }
    }

    companion object {
        private const val TAG = "SkipNotificationReceiver"
    }
}