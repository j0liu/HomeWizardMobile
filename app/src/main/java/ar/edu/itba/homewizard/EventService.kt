package ar.edu.itba.homewizard

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.itba.homewizard.data.repository.DeviceRepository
import ar.edu.itba.homewizard.viewmodels.DevicesViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject


//@AndroidEntryPoint
class EventService : Service() {
//    @Inject lateinit var deviceRepository: DeviceRepository

    companion object {
        private const val TAG = "EventService"
        private const val DELAY_MILLIS: Long = 10000
    }

    private val gson = Gson()
    private lateinit var job: Job

    @OptIn(DelicateCoroutinesApi::class)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Starting service")

        job = GlobalScope.launch(Dispatchers.IO) {
            while (true) {
                val events = fetchEvents()
                events?.forEach {
                    Log.d(TAG, "Broadcasting send notification intent (${it.deviceId})")
                    val intent2 = Intent().apply {
                        action = MyIntent.SHOW_NOTIFICATION
                        `package` = MyIntent.PACKAGE
                        putExtra(MyIntent.DEVICE_ID, it.deviceId)
                    }
                    sendOrderedBroadcast(intent2, null)
                }
                delay(DELAY_MILLIS)
            }
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "Stopping service")

        if (job.isActive)
            job.cancel()
    }

    private fun fetchEvents(): List<EventData>? {

        Log.d(TAG, "Fetching events...")
        val url = "${BuildConfig.API_BASE_URL}/api/devices/events"
        val connection = (URL(url).openConnection() as HttpURLConnection).also {
            it.requestMethod = "GET"
            it.setRequestProperty("Accept", "application/json")
            it.setRequestProperty("Content-Type", "text/event-stream")
            it.doInput = true
        }

        val responseCode = connection.responseCode
        return if (responseCode == HttpURLConnection.HTTP_OK) {
            val stream = BufferedReader(InputStreamReader(connection.inputStream))
            var line: String?
            val response = StringBuffer()
            val eventList = arrayListOf<EventData>()
            while (stream.readLine().also { line = it } != null) {
                when {
                    line!!.startsWith("data:") -> {
                        response.append(line!!.substring(5))
                    }

                    line!!.isEmpty() -> {
                        Log.d(TAG, response.toString())
                        val event = gson.fromJson<EventData>(
                            response.toString(),
                            object : TypeToken<EventData?>() {}.type
                        )
                        eventList.add(event)
                        response.setLength(0)
                    }
                }
            }
            stream.close()
            Log.d(TAG, "New events found (${eventList.size})")
            if (eventList.isNotEmpty()) {
//                    deviceRepository.getDevices()
//                    deviceRepository.test()
            }
            eventList
        } else {
            Log.d(TAG, "No new events found")
            null
        }
    }

}