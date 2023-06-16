package ar.edu.itba.homewizard

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class EventService : Service() {

    companion object {
        private const val TAG = "EventService"
        private const val DELAY_MILLIS : Long = 10000
    }

    private lateinit var job : Job

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "Starting service...")

        job = GlobalScope.launch(Dispatchers.IO) {
            while (true) {

                val eventList = fetchEvents()
                // Lo puedo usar para lanzar una notificacion, etc.
                delay(DELAY_MILLIS)
            }
        }

        return START_STICKY
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null // Es un servicio de background por lo que no necesita soportar bind.
    }

    override fun onDestroy() {
        Log.d(TAG, "Stopping service...")

        if (job.isActive){
            job.cancel()
        }
    }

    private fun fetchEvents() : List <EventData>? {
        Log.d(TAG, "fetching events...")
        val url = "${BuildConfig.API_BASE_URL}api/devices/events"
        val connection = (URL(url).openConnection() as HttpURLConnection).also {
            it.requestMethod = "GET"
            it.setRequestProperty("Accept", "application/json")
            it.setRequestProperty("Content-Type", "text/event-stream")
            it.doInput = true
        }

        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val stream = BufferedReader(InputStreamReader(connection.inputStream))
            var line: String?
            val response = StringBuffer()
            val eventList = arrayListOf<EventData>()
            while (stream.readLine().also { line = it } != null) {
                when {
                    line!!.startsWith("data") -> {
                        response.append(line!!.substring(5))
                    }
                    line!!.isEmpty() -> {
                        val gson = Gson()
                        val event = gson.fromJson<EventData>(
                            response.toString(),
                            object: TypeToken<EventData?>() {}.type
                        )
                        Log.d(event.deviceId, event.deviceId)
                        eventList.add(event)
                    }
                }
            }
            stream.close()
            Log.d(TAG, "New events found (${eventList.size})")
            return eventList
        } else {
            Log.d(TAG, "Connection error / no new events found")
            return null
        }
    }

}