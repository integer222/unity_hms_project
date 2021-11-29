package ru.progstech.unity.hms.ads.rewarded;

import com.huawei.hms.ads.reward.Reward;
import com.huawei.hms.ads.reward.RewardAdListener;

import ru.progstech.unity.hms.core.utils.AdsExecutor;

public class RenderRewardedAdListener implements RewardAdListener {

    private final IRewardedAdStatusListener listener;

    public RenderRewardedAdListener(IRewardedAdStatusListener listener) {
        this.listener = listener != null ? listener : new SimpleRewardedAdStatusListener();
    }

    private void executeMainThread(Runnable runnable) {
        AdsExecutor.executeMainThread(runnable);
    }


    @Override
    public void onRewarded(Reward reward) {
        executeMainThread(() -> listener.onRewarded(reward));
    }

    @Override
    public void onRewardAdClosed() {
        executeMainThread(listener::onRewardAdClosed);
    }

    @Override
    public void onRewardAdFailedToLoad(int i) {
        executeMainThread(() -> listener.onRewardAdFailedToLoad(i));
    }

    @Override
    public void onRewardAdLeftApp() {
        executeMainThread(listener::onRewardAdLeftApp);
    }

    @Override
    public void onRewardAdLoaded() {
        executeMainThread(listener::onRewardAdLoaded);
    }

    @Override
    public void onRewardAdOpened() {
        executeMainThread(listener::onRewardAdOpened);
    }

    @Override
    public void onRewardAdCompleted() {
        executeMainThread(listener::onRewardAdCompleted);
    }

    @Override
    public void onRewardAdStarted() {
        executeMainThread(listener::onRewardAdStarted);
    }
}
