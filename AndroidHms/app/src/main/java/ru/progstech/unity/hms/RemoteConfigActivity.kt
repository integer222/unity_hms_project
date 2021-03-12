package ru.progstech.unity.hms

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_remote_config.*
import ru.progstech.unity.hms.remote_config.IRemoteConfigFetchListener
import ru.progstech.unity.hms.remote_config.RemoteConfigProxy

class RemoteConfigActivity : AppCompatActivity(), IRemoteConfigFetchListener {

    companion object {
        private const val TAG = "[RemoteConfig]"
    }

    private lateinit var proxy: RemoteConfigProxy

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote_config)
        proxy = RemoteConfigProxy(this)
        btn_fetch.setOnClickListener {
            fetch()
        }
        btn_params.setOnClickListener {
            readParams()
        }
    }

    private fun fetch() {
        proxy.fetch()
    }

    @SuppressLint("SetTextI18n")
    private fun readParams() {
        val seconds = proxy.getLong("ad_native_banner_refresh_seconds")
        txt_text.text = "ad_native_banner_refresh_seconds = $seconds"
        Log.d(TAG, seconds.toString())
    }

    override fun onSuccess() {
        Log.d(TAG, "onSuccess")
    }

    override fun onFail() {
        Log.d(TAG, "onFail")
    }
}