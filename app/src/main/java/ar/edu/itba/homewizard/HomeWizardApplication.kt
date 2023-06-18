package ar.edu.itba.homewizard

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HomeWizardApplication : Application() {
    private var eventServiceRunning = false


    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()

        if (!eventServiceRunning) {
            val intent = Intent(this, EventService::class.java)
//                startForegroundService(intent)
                startService(intent)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            } else {
//                startForegroundService(intent)
//            }

            eventServiceRunning = true
        }
    }

    private fun createNotificationChannel() {
        val name = "test"// getString(R.string.channel_name)
        val descriptionText = "test_description" // getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        with (NotificationManagerCompat.from(this)) {
            createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID = "device"
    }
}