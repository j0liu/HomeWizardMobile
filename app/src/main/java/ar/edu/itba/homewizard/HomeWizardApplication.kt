package ar.edu.itba.homewizard

import android.app.Application
import android.content.Intent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HomeWizardApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val intent = Intent(this, EventService::class.java)
//        startService(intent)
    }

}