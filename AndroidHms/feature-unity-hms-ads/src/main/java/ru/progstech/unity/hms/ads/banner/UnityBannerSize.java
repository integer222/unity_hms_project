package ru.progstech.unity.hms.ads.banner;

import com.huawei.hms.ads.BannerAdSize;

public enum UnityBannerSize {

    BANNER_SIZE_360_57(BannerAdSize.BANNER_SIZE_360_57),
    BANNER_SIZE_360_144(BannerAdSize.BANNER_SIZE_360_144),
    BANNER_SIZE_320_50(BannerAdSize.BANNER_SIZE_320_50),
    BANNER_SIZE_DYNAMIC(BannerAdSize.BANNER_SIZE_DYNAMIC),
    BANNER_SIZE_468_60(BannerAdSize.BANNER_SIZE_468_60),
    BANNER_SIZE_INVALID(BannerAdSize.BANNER_SIZE_INVALID),
    BANNER_SIZE_320_100(BannerAdSize.BANNER_SIZE_320_100),
    BANNER_SIZE_728_90(BannerAdSize.BANNER_SIZE_728_90),
    BANNER_SIZE_300_250(BannerAdSize.BANNER_SIZE_300_250),
    BANNER_SIZE_SMART(BannerAdSize.BANNER_SIZE_SMART),
    BANNER_SIZE_160_600(BannerAdSize.BANNER_SIZE_160_600);

    private final BannerAdSize bannerSize;

    UnityBannerSize(BannerAdSize bannerSize) {
        this.bannerSize = bannerSize;
    }

    public BannerAdSize getBannerSize() {
        return bannerSize;
    }

}
