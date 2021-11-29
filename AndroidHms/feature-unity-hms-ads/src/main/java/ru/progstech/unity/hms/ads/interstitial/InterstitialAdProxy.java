package ru.progstech.unity.hms.ads.interstitial;

import android.app.Activity;

import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.InterstitialAd;

import ru.progstech.unity.hms.ads.core.IAdStatusListener;
import ru.progstech.unity.hms.ads.core.RenderAdListener;
import ru.progstech.unity.hms.core.utils.AdsExecutor;

public class InterstitialAdProxy {

    private final InterstitialAd interstitialAd;
    private final RenderAdListener renderAdListener;
    private final Activity activity;

    public InterstitialAdProxy(Activity activity, IAdStatusListener listener) {
        this.activity = activity;
        renderAdListener = new RenderAdListener(listener);
        interstitialAd = new InterstitialAd(activity);
        interstitialAd.setAdListener(renderAdListener);
    }

    public void loadAd(String adId, AdParam param) {
        AdsExecutor.executeMainThread(() -> {
            interstitialAd.setAdId(adId);
            interstitialAd.loadAd(param);
        });
    }

    public void show() {
        AdsExecutor.executeMainThread(() -> {
            if (!interstitialAd.isLoaded()) {
                return;
            }
            interstitialAd.show(activity);
        });
    }

    public boolean isLoading() {
        return interstitialAd.isLoading();
    }

    public boolean isLoaded() {
        return interstitialAd.isLoaded();
    }


}
