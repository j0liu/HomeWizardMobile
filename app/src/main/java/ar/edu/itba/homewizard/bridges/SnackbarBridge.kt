package ar.edu.itba.homewizard.bridges

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SnackbarBridge @Inject constructor() {
    private val latestMessages: MutableLiveData<StatusMessage> by lazy {
        MutableLiveData<StatusMessage>()
    }

    fun sendMessage(message: String, type: SnackbarType = SnackbarType.INFO) {
        latestMessages.postValue(StatusMessage(message, type))
    }

    fun subscribe(subscriberFun : (String, SnackbarType) -> Unit) {
        return latestMessages.observeForever { data ->
            if (data.message != "")
                subscriberFun(data.message, data.type)
        }
    }
}

enum class SnackbarType {
    ERROR, INFO, PANIC
}
data class StatusMessage(val message: String, val type: SnackbarType = SnackbarType.INFO)