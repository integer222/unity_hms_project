package ru.progstech.unity.hms.oaid;

public interface OAIDCallback {
    void onSuccess(String oaid, boolean isOaidTrackLimited);

    void onFail(String errMsg);
}
