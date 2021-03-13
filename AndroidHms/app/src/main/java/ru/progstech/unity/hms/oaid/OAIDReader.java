package ru.progstech.unity.hms.oaid;

import android.content.Context;
import android.util.Log;

import com.huawei.hms.ads.identifier.AdvertisingIdClient;

import java.io.IOException;

public class OAIDReader {

    private final static String TAG = "[OAIDReader]";

    public static void writeToLog(Context context) {
        getOAID(context, new OAIDCallback() {
            @Override
            public void onSuccess(String oaid, boolean isOaidTrackLimited) {
                Log.d(TAG, oaid + " " + isOaidTrackLimited);
            }

            @Override
            public void onFail(String errMsg) {
                Log.e(TAG, errMsg);
            }
        });
    }

    public static void getOAID(Context context, OAIDCallback callback) {
        if (null == context || null == callback) {
            Log.e(TAG, "invalid input param");
            return;
        }
        try {
            AdvertisingIdClient.Info info = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (null != info) {
                callback.onSuccess(info.getId(), info.isLimitAdTrackingEnabled());
            } else {
                callback.onFail("oaid is null");
            }
        } catch (IOException e) {
            Log.e(TAG, "getAdvertisingIdInfo IOException");
            callback.onFail("getAdvertisingIdInfo IOException");
        }
    }
}
