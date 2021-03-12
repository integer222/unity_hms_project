package ru.progstech.unity.hms.core.utils;

import android.util.Log;

public final class Logger {

    private static boolean isEnabled = false;

    public static void setEnabled(boolean isEnabled) {
        Logger.isEnabled = isEnabled;
    }

    public static boolean isEnabled() {
        return isEnabled;
    }

    public static void v(String tag, String msg) {
        v(tag, msg, null);
    }

    public static void v(String tag, String msg, Throwable t) {
        if (!isEnabled) {
            return;
        }
        Log.v(tag, msg, t);
    }

    public static void d(String tag, String msg) {
        d(tag, msg, null);
    }

    public static void d(String tag, String msg, Throwable t) {
        if (!isEnabled) {
            return;
        }
        Log.d(tag, msg, t);
    }

    public static void i(String tag, String msg) {
        i(tag, msg, null);
    }

    public static void i(String tag, String msg, Throwable t) {
        if (!isEnabled) {
            return;
        }
        Log.i(tag, msg, t);
    }

    public static void w(String tag, String msg) {
        w(tag, msg, null);
    }

    public static void w(String tag, String msg, Throwable t) {
        if (!isEnabled) {
            return;
        }
        Log.w(tag, msg, t);
    }

    public static void e(String tag, String msg) {
        e(tag, msg, null);
    }

    public static void e(String tag, String msg, Throwable t) {
        if (!isEnabled) {
            return;
        }
        Log.e(tag, msg, t);
    }

}
