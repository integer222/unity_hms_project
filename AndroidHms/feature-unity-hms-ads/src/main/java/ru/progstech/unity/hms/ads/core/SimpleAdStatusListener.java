package ru.progstech.unity.hms.ads.core;

public class SimpleAdStatusListener implements IAdStatusListener {

    private static final String TAG = "[SimpleAdStatusListener]";

    @Override
    public void onAdClosed() {

    }

    @Override
    public void onAdFailed(int errorCode) {

    }

    @Override
    public void onAdLeave() {

    }

    @Override
    public void onAdOpened() {

    }

    @Override
    public void onAdLoaded() {
        Logger.d(TAG, "onAdLoaded");
    }

    @Override
    public void onAdClicked() {

    }

    @Override
    public void onAdImpression() {

    }
}
