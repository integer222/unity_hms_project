package ru.progstech.unity.hms

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.progstech.unity.hms.databinding.ActivityRemoteConfigBinding
import ru.progstech.unity.hms.remote_config.IRemoteConfigFetchListener
import ru.progstech.unity.hms.remote_config.RemoteConfigProxy

class RemoteConfigActivity : AppCompatActivity(), IRemoteConfigFetchListener {

    private lateinit var binding: ActivityRemoteConfigBinding
    private lateinit var proxy: RemoteConfigProxy

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRemoteConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
        proxy = RemoteConfigProxy(this)
        binding.btnFetch.setOnClickListener {
            fetch()
        }
        binding.btnParams.setOnClickListener {
            readParams()
        }
    }

    private fun fetch() {
        proxy.fetch()
    }

    @SuppressLint("SetTextI18n")
    private fun readParams() {
        val seconds = proxy.getLong("ad_native_banner_refresh_seconds")
        binding.txtText.text = "ad_native_banner_refresh_seconds = $seconds"
        Log.d(TAG, seconds.toString())
    }

    override fun onSuccess() {
        Log.d(TAG, "onSuccess")
    }

    override fun onFail() {
        Log.d(TAG, "onFail")
    }

    companion object {
        private const val TAG = "[RemoteConfig]"
    }
}
