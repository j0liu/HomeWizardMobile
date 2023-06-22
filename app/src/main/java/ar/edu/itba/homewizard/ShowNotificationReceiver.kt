package ar.edu.itba.homewizard

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ar.edu.itba.homewizard.data.repository.DeviceRepository
import ar.edu.itba.homewizard.ui.utils.SharedPreferencesUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ShowNotificationReceiver : BroadcastReceiver() {
    @Inject
    lateinit var deviceRepository : DeviceRepository

    @OptIn(DelicateCoroutinesApi::class)
    override fun onReceive(context: Context, intent: Intent) {
        val background : Boolean = context.getSharedPreferences("ar.edu.itba.homewizard", Context.MODE_PRIVATE).getBoolean("background", false)
        if (background) {
            val deviceId: String? = intent.getStringExtra(MyIntent.UPDATE_DEVICE)
            Log.d(TAG, "Show notification intent received {$deviceId)")

            val notificationsEnabled : Boolean = context.getSharedPreferences(SharedPreferencesUtils.DEVICE_SP_KEY, Context.MODE_PRIVATE).getBoolean(deviceId, false)
            if (notificationsEnabled) {
                GlobalScope.launch(Dispatchers.IO) {
                    showNotification(context, deviceId!!)
                }
            }
        }
    }

    private suspend fun showNotification(context: Context, deviceId: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra(MyIntent.UPDATE_DEVICE, deviceId)
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        })

        val device = deviceRepository.getDevice(deviceId)
        val builder = NotificationCompat.Builder(context, HomeWizardApplication.CHANNEL_ID)
            .setSmallIcon(device.type.icon)
            .setContentTitle(device.name)
            .setContentText(context.getString(R.string.device_changes))
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