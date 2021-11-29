package ru.progstech.unity.hms.ads.rewarded;

import com.huawei.hms.ads.reward.Reward;

import ru.progstech.unity.hms.core.utils.Logger;

public class SimpleRewardedAdStatusListener implements IRewardedAdStatusListener {

    private static final String TAG = "[SimpleRewardedAdListener]";

    @Override
    public void onRewarded(Reward reward) {

    }

    @Override
    public void onRewardAdClosed() {

    }

    @Override
    public void onRewardAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardAdLeftApp() {

    }

    @Override
    public void onRewardAdLoaded() {
        Logger.d(TAG, "onAdLoaded");
    }

    @Override
    public void onRewardAdOpened() {

    }

    @Override
    public void onRewardAdCompleted() {

    }

    @Override
    public void onRewardAdStarted() {

    }
}
