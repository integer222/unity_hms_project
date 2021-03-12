package ru.progstech.unity.hms.core.utils;

import android.os.Handler;
import android.os.Looper;

public final class AdsExecutor {

    private static final Handler MAIN_THREAD_HANDLER = new Handler(Looper.getMainLooper());

    public static void executeMainThread(Runnable runnable) {
        MAIN_THREAD_HANDLER.post(runnable);
    }


}
