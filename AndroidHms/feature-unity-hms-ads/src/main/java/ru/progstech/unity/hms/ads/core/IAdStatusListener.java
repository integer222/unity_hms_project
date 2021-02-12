package ru.progstech.unity.hms.ads.core;

public interface IAdStatusListener {


    void onAdClosed();

    void onAdFailed(int errorCode);

    void  onAdLeave();

    void  onAdOpened();

    void  onAdLoaded();

    void  onAdClicked();

    void  onAdImpression();

}
