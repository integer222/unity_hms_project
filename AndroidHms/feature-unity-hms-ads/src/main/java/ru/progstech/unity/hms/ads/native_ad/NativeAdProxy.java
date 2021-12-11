package ru.progstech.unity.hms.ads.native_ad;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.nativead.MediaView;
import com.huawei.hms.ads.nativead.NativeAd;
import com.huawei.hms.ads.nativead.NativeAdConfiguration;
import com.huawei.hms.ads.nativead.NativeAdLoader;
import com.huawei.hms.ads.nativead.NativeView;

import ru.progstech.unity.hms.ads.R;
import ru.progstech.unity.hms.ads.core.IAdStatusListener;
import ru.progstech.unity.hms.core.utils.Logger;
import ru.progstech.unity.hms.ads.core.RenderAdListener;
import ru.progstech.unity.hms.core.utils.AdsExecutor;

public class NativeAdProxy implements NativeAd.NativeAdLoadedListener {

    private static final String TAG = "[NativeAdProxy]";

    private final Activity activity;
    private ViewGroup nativeViewContainer;
    private NativeAd nativeAd;
    private final RenderAdListener renderAdListener;

    public NativeAdProxy(Activity activity, IAdStatusListener listener) {
        this.activity = activity;
        this.renderAdListener = new RenderAdListener(listener);
    }

    public void show() {
        AdsExecutor.executeMainThread(() -> {
            if (nativeViewContainer == null) {
                return;
            }
            nativeViewContainer.setVisibility(View.VISIBLE);
        });
    }

    public void hide() {
        AdsExecutor.executeMainThread(() -> {
            if (nativeViewContainer == null) {
                return;
            }
            nativeViewContainer.setVisibility(View.GONE);
        });
    }

    public void destroy() {
        AdsExecutor.executeMainThread(() -> {
            if (nativeAd != null) {
                nativeAd.destroy();
                nativeAd = null;
            }
            removeNativeView();
        });
    }

    private void removeNativeView() {
        if (nativeViewContainer == null) {
            return;
        }
        nativeViewContainer.removeAllViews();
    }

    public void loadAd(String adId) {
        AdsExecutor.executeMainThread(() -> {
            if (adId == null) {
                return;
            }
            NativeAdLoader.Builder builder = new NativeAdLoader.Builder(activity.getApplicationContext(), adId);
            builder.setNativeAdLoadedListener(this)
                    .setAdListener(renderAdListener);

            NativeAdConfiguration adConfiguration = new NativeAdConfiguration.Builder()
//                    .setChoicesPosition(NativeAdConfiguration.ChoicesPosition.TOP_RIGHT) // Set custom attributes.
//                    .setRequestCustomDislikeThisAd()
                    .build();

            NativeAdLoader nativeAdLoader = builder.setNativeAdOptions(adConfiguration).build();

            nativeAdLoader.loadAd(new AdParam.Builder().build());
        });
    }

    @Override
    public void onNativeAdLoaded(NativeAd nativeAd) {
        Logger.d(TAG, "onNativeAdLoaded Start");
        NativeView nativeView = getNativeView();
        if (this.nativeAd != null) {
            this.nativeAd.destroy();
        }
        this.nativeAd = nativeAd;
        initNativeAdView(this.nativeAd, nativeView);
        Logger.d(TAG, "onNativeAdLoaded End");
    }

    private NativeView getNativeView() {
        ViewGroup nativeViewContainer = getNativeContainer();
        NativeView nativeView = (NativeView) LayoutInflater.from(activity).inflate(R.layout.native_small_template, (ViewGroup) null);
        nativeView.setTitleView(nativeView.findViewById(R.id.ad_title));
        nativeView.setMediaView((MediaView) nativeView.findViewById(R.id.ad_media));
        nativeView.setAdSourceView(nativeView.findViewById(R.id.ad_source));
        nativeView.setCallToActionView(nativeView.findViewById(R.id.ad_call_to_action));

        nativeViewContainer.removeAllViews();
        nativeViewContainer.addView(nativeView);
        return nativeView;
    }

    private ViewGroup getNativeContainer() {
        if (nativeViewContainer != null) {
            return nativeViewContainer;
        }
        nativeViewContainer = (ViewGroup) LayoutInflater.from(activity).inflate(R.layout.native_container, (ViewGroup) null);
        FrameLayout.LayoutParams adViewLayoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        adViewLayoutParams.gravity = Gravity.BOTTOM;
        activity.addContentView(nativeViewContainer, adViewLayoutParams);
        return nativeViewContainer;
    }

    private void initNativeAdView(NativeAd nativeAd, NativeView nativeView) {
        ((TextView) nativeView.getTitleView()).setText(nativeAd.getTitle());
        nativeView.getMediaView().setMediaContent(nativeAd.getMediaContent());

        if (null != nativeAd.getAdSource()) {
            ((TextView) nativeView.getAdSourceView()).setText(nativeAd.getAdSource());
        }
        nativeView.getAdSourceView()
                .setVisibility(null != nativeAd.getAdSource() ? View.VISIBLE : View.INVISIBLE);

        if (null != nativeAd.getCallToAction()) {
            ((Button) nativeView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }
        nativeView.getCallToActionView()
                .setVisibility(null != nativeAd.getCallToAction() ? View.VISIBLE : View.INVISIBLE);

        nativeAd.setDislikeAdListener(this::removeNativeView);

//        // Obtain a video controller.
//        VideoOperator videoOperator = nativeAd.getVideoOperator();
//
//        // Check whether a native ad contains video materials.
//        if (videoOperator.hasVideo()) {
//            // Add a video lifecycle event listener.
//            videoOperator.setVideoLifecycleListener(videoLifecycleListener);
//        }

        // Register a native ad object.

        nativeView.setNativeAd(nativeAd);
    }


}
