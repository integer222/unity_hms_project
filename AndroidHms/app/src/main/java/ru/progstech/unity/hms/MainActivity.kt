package ru.progstech.unity.hms

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.huawei.hms.ads.AdParam
import ru.progstech.unity.hms.ads.core.IAdStatusListener
import ru.progstech.unity.hms.ads.banner.BannerAdProxy
import ru.progstech.unity.hms.ads.banner.UnityBannerSize
import ru.progstech.unity.hms.ads.interstitial.InterstitialAdProxy
import ru.progstech.unity.hms.ads.core.Ads
import ru.progstech.unity.hms.ads.native_ad.NativeAdProxy
import ru.progstech.unity.hms.databinding.ActivityMainBinding
import ru.progstech.unity.hms.oaid.OAIDCallback
import ru.progstech.unity.hms.oaid.OAIDReader


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //OAIDReader.writeToLog(this)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTestRemoteConfig.setOnClickListener {
            startActivity(Intent(this, RemoteConfigActivity::class.java))
        }
        binding.btnTestAds.setOnClickListener {
            startActivity(Intent(this, AdsActivity::class.java))
        }
//        Ads.init(this)
//        Ads.setLogsEnabled(true)
//        val proxyNative = NativeAdProxy(this, null)
//        val proxy = BannerAdProxy(this, null).apply {
//            setAdId("testw6vs28auh3")
//            //loadAd(AdParam.Builder().build())
//            setUnityAdSizeByType(UnityBannerSize.BANNER_SIZE_360_57)
//        }
//
//        val proxyFull = InterstitialAdProxy(this, null)
//
//        //proxyFull.loadAd("teste9ih9j0rc3", AdParam.Builder().build())
//
//        binding.loadBanner.setOnClickListener {
////            if (proxyFull.isLoaded()) {
////                proxyFull.show()
////            }else {
////                proxyFull.loadAd(AdParam.Builder().build())
////            }
//
//            proxy.loadAd(AdParam.Builder().build())
//        }
//
//        binding.loadNative.setOnClickListener {
//            proxyNative.loadAd("testb65czjivt9")
//        }
//        binding.destroyNative.setOnClickListener {
//            proxyNative.destroy()
//        }
    }
}