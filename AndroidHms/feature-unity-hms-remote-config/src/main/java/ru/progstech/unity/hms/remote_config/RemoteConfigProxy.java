package ru.progstech.unity.hms.remote_config;

import com.huawei.agconnect.remoteconfig.AGConnectConfig;

import java.util.EnumMap;

import ru.progstech.unity.hms.core.utils.Logger;

public class RemoteConfigProxy {

    private static final String TAG = "[RemoteConfigProxy]";

    private final AGConnectConfig config;
    private final RenderRemoteConfigFetchListener renderRemoteConfigFetchListener;

    private EnumMap<AGConnectConfig.SOURCE, Integer> unitySourceMap = new EnumMap<>(AGConnectConfig.SOURCE.class);

    public RemoteConfigProxy(IRemoteConfigFetchListener listener) {
        config = AGConnectConfig.getInstance();
        renderRemoteConfigFetchListener = new RenderRemoteConfigFetchListener(listener);
        unitySourceMap.put(AGConnectConfig.SOURCE.STATIC, 0);
        unitySourceMap.put(AGConnectConfig.SOURCE.DEFAULT, 1);
        unitySourceMap.put(AGConnectConfig.SOURCE.REMOTE, 2);
    }

    public void fetch() {
        config.fetch().addOnSuccessListener(configValues -> {
            config.apply(configValues);
            renderRemoteConfigFetchListener.onSuccess();
        }).addOnFailureListener(e -> {
            Logger.e(TAG, "onFail", e);
            renderRemoteConfigFetchListener.onFail();
        });
    }

    public boolean getBool(String key) {
        return config.getValueAsBoolean(key);
    }

    public long getLong(String key) {
        return config.getValueAsLong(key);
    }

    public double getDouble(String key) {
        return config.getValueAsDouble(key);
    }

    public String getString(String key) {
        return config.getValueAsString(key);
    }

    public byte[] getByteArray(String key) {
        return config.getValueAsByteArray(key);
    }

    public int getSource(String key) {
        return unitySourceMap.get(config.getSource(key));
    }

}
