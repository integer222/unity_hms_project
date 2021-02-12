package ru.progstech.unity.hms.ads.core;

import android.content.Context;

import com.huawei.hms.ads.HwAds;

public final class Ads {

    public static void init(Context context) {
        HwAds.init(context);
    }

    public static void setLogsEnabled(boolean isEnabled) {
        Logger.setEnabled(isEnabled);
    }

}
