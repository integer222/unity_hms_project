package ru.progstech.unity.hms.remote_config;

import ru.progstech.unity.hms.core.utils.AdsExecutor;

public class RenderRemoteConfigFetchListener implements IRemoteConfigFetchListener {

    private final IRemoteConfigFetchListener listener;

    public RenderRemoteConfigFetchListener(IRemoteConfigFetchListener listener) {
        this.listener = listener != null ? listener : new IRemoteConfigFetchListener() {};
    }

    @Override
    public void onSuccess() {
        AdsExecutor.executeMainThread(listener::onSuccess);
    }

    @Override
    public void onFail() {
        AdsExecutor.executeMainThread(listener::onFail);
    }
}
