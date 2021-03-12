package ru.progstech.unity.hms.ads.core;

import com.huawei.hms.ads.AdListener;

import ru.progstech.unity.hms.core.utils.AdsExecutor;

public class RenderAdListener extends AdListener {

    private final IAdStatusListener listener;

    public RenderAdListener(IAdStatusListener listener) {
        this.listener = listener != null ? listener : new SimpleAdStatusListener();
    }

    private void executeMainThread(Runnable runnable) {
        AdsExecutor.executeMainThread(runnable);
    }

    @Override
    public void onAdClosed() {
        super.onAdClosed();
        executeMainThread(listener::onAdClosed);
    }

    @Override
    public void onAdFailed(int i) {
        super.onAdFailed(i);
        executeMainThread(() -> listener.onAdFailed(i));
    }

    @Override
    public void onAdLeave() {
        super.onAdLeave();
        executeMainThread(listener::onAdLeave);
    }

    @Override
    public void onAdOpened() {
        super.onAdOpened();
        executeMainThread(listener::onAdOpened);
    }

    @Override
    public void onAdLoaded() {
        super.onAdLoaded();
        executeMainThread(listener::onAdLoaded);
    }

    @Override
    public void onAdClicked() {
        super.onAdClicked();
        executeMainThread(listener::onAdClicked);
    }

    @Override
    public void onAdImpression() {
        super.onAdImpression();
        executeMainThread(listener::onAdImpression);
    }

}
