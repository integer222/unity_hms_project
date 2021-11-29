package ru.progstech.unity.hms.ads.rewarded;

import android.app.Activity;

import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.reward.RewardAd;

import ru.progstech.unity.hms.core.utils.AdsExecutor;

public class RewardedAdProxy {

    private final RewardAd rewardAd;
    private final RenderRewardedAdListener renderAdListener;
    private final Activity activity;

    public RewardedAdProxy(Activity activity, IRewardedAdStatusListener listener) {
        this.activity = activity;
        renderAdListener = new RenderRewardedAdListener(listener);
        rewardAd = RewardAd.createRewardAdInstance(activity);
        rewardAd.setRewardAdListener(renderAdListener);
    }

    public void loadAd(String adId, AdParam param) {
        AdsExecutor.executeMainThread(() -> {
            rewardAd.loadAd(adId, param);
        });
    }

    public void show() {
        AdsExecutor.executeMainThread(() -> {
           if (!rewardAd.isLoaded()) {
               return;
           }
           rewardAd.show(activity);
        });
    }

    public boolean isLoaded() {
        return rewardAd.isLoaded();
    }
}
