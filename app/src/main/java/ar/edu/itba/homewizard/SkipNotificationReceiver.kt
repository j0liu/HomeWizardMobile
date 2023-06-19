package ar.edu.itba.homewizard

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import ar.edu.itba.homewizard.data.repository.DeviceRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class SkipNotificationReceiver() : BroadcastReceiver() {
    @Inject
    lateinit var deviceRepository : DeviceRepository


    @OptIn(DelicateCoroutinesApi::class)
    override fun onReceive(context: Context, intent: Intent) {
        val background : Boolean = context.getSharedPreferences("ar.edu.itba.homewizard", Context.MODE_PRIVATE).getBoolean("background", false)
        if (intent.action.equals(MyIntent.SHOW_NOTIFICATION) && !background) {
             GlobalScope.launch(Dispatchers.IO) {
                deviceRepository.updateDevices()
            }
            abortBroadcast()
        }
    }

    companion object {
        private const val TAG = "SkipNotificationReceiver"
    }
}