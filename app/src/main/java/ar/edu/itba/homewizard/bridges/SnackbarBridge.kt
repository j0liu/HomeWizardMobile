package ar.edu.itba.homewizard.bridges

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SnackbarBridge @Inject constructor() {
    private val latestMessages: MutableLiveData<StatusMessage> by lazy {
        MutableLiveData<StatusMessage>()
    }

    fun sendMessage(message: String, isError: Boolean = false) {
        latestMessages.postValue(StatusMessage(message, isError))
    }

    fun subscribe(subscriberFun : (String, Boolean) -> Unit) {
        return latestMessages.observeForever { data ->
            if (data.message != "")
                subscriberFun(data.message, data.isError)
        }
    }
}

data class StatusMessage(val message: String, val isError: Boolean = false)