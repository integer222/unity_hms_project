package ru.progstech.unity.hms.ads.interstitial;

import android.app.Activity;

import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.InterstitialAd;

import ru.progstech.unity.hms.ads.core.IAdStatusListener;
import ru.progstech.unity.hms.ads.core.RenderAdListener;

public class InterstitialAdProxy {

    private final InterstitialAd interstitialAd;

    private final RenderAdListener renderAdListener;

    public InterstitialAdProxy(Activity activity, IAdStatusListener listener) {
        renderAdListener = new RenderAdListener(listener);
        interstitialAd = new InterstitialAd(activity);
        interstitialAd.setAdListener(renderAdListener);
    }

    public void loadAd(String adId, AdParam param) {
        interstitialAd.setAdId(adId);
        interstitialAd.loadAd(param);
    }

    public boolean isLoading() {
        return interstitialAd.isLoading();
    }

    public boolean isLoaded() {
        return interstitialAd.isLoaded();
    }





}
