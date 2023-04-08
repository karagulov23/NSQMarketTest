package krg.sltn.nsqmarkettest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class Status{
    Loading,
    One,
    Two,
    Unknown
}

class MainViewModel(val api: API) :ViewModel() {

    val statusLiveData = MutableLiveData<Status>(Status.Loading)

    init {
        viewModelScope.launch {
            val api = ApiClient.assemblyaiApi()

            var response = api.transcript(mapOf("audio_url" to "https://testtasks.nutgeek.fun/a/"))
            Log.e("e", "response: $response")

            while (response.status != "completed") {

                delay(2000)
                response = api.status(response.id)
                Log.e("e", "response: $response")
            }

            Log.e("e", "--- text: ${response.text}")
            val text = response.text.lowercase()
            when{
                text.contains("one") -> statusLiveData.postValue(Status.One)
                text.contains("two") -> statusLiveData.postValue(Status.Two)
                else -> statusLiveData.postValue(Status.Unknown)
            }
        }

    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        return MainViewModel(
            ApiClient.assemblyaiApi()
        ) as T
    }
}