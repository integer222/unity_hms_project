package ru.progstech.unity.hms

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.reward.Reward
import ru.progstech.unity.hms.ads.banner.BannerAdProxy
import ru.progstech.unity.hms.ads.banner.UnityBannerSize
import ru.progstech.unity.hms.ads.core.Ads
import ru.progstech.unity.hms.ads.core.SimpleAdStatusListener
import ru.progstech.unity.hms.ads.interstitial.InterstitialAdProxy
import ru.progstech.unity.hms.ads.native_ad.NativeAdProxy
import ru.progstech.unity.hms.ads.rewarded.IRewardedAdStatusListener
import ru.progstech.unity.hms.ads.rewarded.RewardedAdProxy
import ru.progstech.unity.hms.ads.rewarded.SimpleRewardedAdStatusListener
import ru.progstech.unity.hms.databinding.ActivityAdsBinding

class AdsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAds()
        initViews()
    }

    private fun initAds() {
        Ads.init(this)
        Ads.setLogsEnabled(true)
    }

    private fun initViews() {
        initBanner()
        initNative()
        initRewarded()
        initInterstitial()
    }

    private fun initBanner() {
        val proxy = BannerAdProxy(this, null).apply {
            setAdId("testw6vs28auh3")
            setUnityAdSizeByType(UnityBannerSize.BANNER_SIZE_360_57)
        }
        with(binding.bannerControls) {
            title.setText(R.string.title_banner)
            load.setOnClickListener {
                proxy.loadAd(AdParam.Builder().build())
            }
            show.setOnClickListener {
                proxy.show()
            }
            hide.setOnClickListener {
                proxy.hide()
            }
            destroy.setOnClickListener {
                proxy.destroy()
            }
        }

    }

    private fun initNative() {
        val proxy = NativeAdProxy(this, null)
        with(binding.nativeControls) {
            title.setText(R.string.title_native)
            load.setOnClickListener {
                proxy.loadAd("testb65czjivt9")
            }
            show.setOnClickListener {
                proxy.show()
            }
            hide.setOnClickListener {
                proxy.hide()
            }
            destroy.setOnClickListener {
                proxy.destroy()
            }
        }
    }

    private fun initInterstitial() {
        val proxy = InterstitialAdProxy(this, null)
        with(binding.interstitialControls) {
            title.setText(R.string.title_interstitial)
            load.setOnClickListener {
                proxy.loadAd("testb4znbuh3n2", AdParam.Builder().build())
            }
            show.setOnClickListener {
                proxy.show()
            }
        }

    }

    private fun initRewarded() {
        val listener = object : SimpleRewardedAdStatusListener() {
            override fun onRewarded(reward: Reward?) {
                super.onRewarded(reward)
                Log.d("Rewarded", "onRewarded")
            }

            override fun onRewardAdFailedToLoad(i: Int) {
                super.onRewardAdFailedToLoad(i)
                Log.d("Rewarded", "onRewardAdFailedToLoad")
            }

            override fun onRewardAdLoaded() {
                super.onRewardAdLoaded()
                Log.d("Rewarded", "onRewardAdLoaded")
            }

            override fun onRewardAdOpened() {
                super.onRewardAdOpened()
                Log.d("Rewarded", "onRewardAdOpened")
            }

            override fun onRewardAdCompleted() {
                super.onRewardAdCompleted()
                Log.d("Rewarded", "onRewardAdCompleted")
            }
        }
        val proxy = RewardedAdProxy(this, listener)
        with(binding.rewardedControls) {
            title.setText(R.string.title_rewarded)
            load.setOnClickListener {
                proxy.loadAd("testx9dtjwj8hp", AdParam.Builder().build())
            }
            show.setOnClickListener {
                proxy.show()
            }
        }
    }
}