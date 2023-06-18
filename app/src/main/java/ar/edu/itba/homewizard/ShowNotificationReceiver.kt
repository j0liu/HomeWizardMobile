package ar.edu.itba.homewizard

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ShowNotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val deviceId: String? = intent.getStringExtra(MyIntent.UPDATE_DEVICE)
        Log.d(TAG, "Show notification intent received {$deviceId)")

        showNotification(context, deviceId!!)
    }

    private fun showNotification(context: Context, deviceId: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(MyIntent.UPDATE_DEVICE, deviceId)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context, HomeWizardApplication.CHANNEL_ID)
            .setSmallIcon(R.drawable.air_conditioner)
            .setContentTitle(context.getString(R.string.latina))
            .setContentText(context.getString(R.string.door))
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(context.getString(R.string.cool)))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        try {
            val notificationManager = NotificationManagerCompat.from(context)
            if (notificationManager.areNotificationsEnabled())
                notificationManager.notify(deviceId.hashCode(), builder.build())
        } catch (e: SecurityException) {
            Log.d(TAG, "Notification permission not granted $e")
        }
    }

    companion object {
        private const val TAG = "ShowNotificationReceiver"
    }
}