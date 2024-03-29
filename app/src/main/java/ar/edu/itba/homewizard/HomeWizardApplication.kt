package ar.edu.itba.homewizard

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HomeWizardApplication : Application() {
    private var eventServiceRunning = false

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()

        if (!eventServiceRunning) {
            val intent = Intent(this, EventService::class.java)
            startService(intent)
            eventServiceRunning = true
        }
    }

    private fun createNotificationChannel() {
        val name = "test"
        val descriptionText = "test_description"
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