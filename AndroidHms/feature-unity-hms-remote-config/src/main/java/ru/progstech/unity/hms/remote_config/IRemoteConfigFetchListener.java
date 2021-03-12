package ru.progstech.unity.hms.remote_config;

public interface IRemoteConfigFetchListener {

    default void onSuccess() {
    }

    default void onFail() {
    }
}
