package ru.progstech.unity.hms

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.huawei.hms.ads.AdParam
import kotlinx.android.synthetic.main.activity_main.*
import ru.progstech.unity.hms.ads.core.IAdStatusListener
import ru.progstech.unity.hms.ads.banner.BannerAdProxy
import ru.progstech.unity.hms.ads.banner.UnityBannerSize
import ru.progstech.unity.hms.ads.interstitial.InterstitialAdProxy
import ru.progstech.unity.hms.ads.core.Ads
import ru.progstech.unity.hms.ads.native_ad.NativeAdProxy



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_test_remote_config.setOnClickListener {
            startActivity(Intent(this, RemoteConfigActivity::class.java))
        }
        btn_test_ads.setOnClickListener {
            startActivity(Intent(this, AdsActivity::class.java))
        }
        Ads.init(this)
        Ads.setLogsEnabled(true)
        val proxyNative = NativeAdProxy(this, null)
        val proxy = BannerAdProxy(this, null).apply {
            setAdId("testw6vs28auh3")
            //loadAd(AdParam.Builder().build())
            setUnityAdSizeByType(UnityBannerSize.BANNER_SIZE_360_57)
        }

        val proxyFull = InterstitialAdProxy(this, null)

        //proxyFull.loadAd("teste9ih9j0rc3", AdParam.Builder().build())

        load_banner.setOnClickListener {
//            if (proxyFull.isLoaded()) {
//                proxyFull.show()
//            }else {
//                proxyFull.loadAd(AdParam.Builder().build())
//            }

            proxy.loadAd(AdParam.Builder().build())
        }

        load_native.setOnClickListener {
            proxyNative.loadAd("testb65czjivt9")
        }
        destroy_native.setOnClickListener {
            proxyNative.destroy()
        }
    }
}