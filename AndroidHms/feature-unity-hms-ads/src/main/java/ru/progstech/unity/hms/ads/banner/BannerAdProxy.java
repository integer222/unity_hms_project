package ru.progstech.unity.hms.ads.banner;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.banner.BannerView;

import ru.progstech.unity.hms.ads.core.IAdStatusListener;
import ru.progstech.unity.hms.ads.core.RenderAdListener;
import ru.progstech.unity.hms.ads.utils.AdsExecutor;

public class BannerAdProxy {

    private final Activity activity;
    private final RenderAdListener renderAdListener;

    private BannerView bannerView;
    private String adId;
    private long bannerRefresh = 30L;
    private UnityBannerSize unityBannerSize = UnityBannerSize.BANNER_SIZE_360_57;

    public BannerAdProxy(Activity activity, IAdStatusListener listener) {
        this.activity = activity;
        this.renderAdListener = new RenderAdListener(listener);
    }

    public void loadAd(AdParam param) {
        executeMainThread(() -> {
            if (adId == null) {
                return;
            }
            if (bannerView == null) {
                bannerView = initBanner(activity);
            }
            bannerView.setAdId(adId);
            bannerView.setBannerRefresh(bannerRefresh);
            bannerView.setBannerAdSize(unityBannerSize.getBannerSize());
            if (BannerAdSize.BANNER_SIZE_INVALID == bannerView.getBannerAdSize()) {
                return;
            }
            bannerView.loadAd(param);
        });
    }

    private BannerView initBanner(Activity activity) {
        if (bannerView != null) {
            return bannerView;
        }
        FrameLayout.LayoutParams bannerViewLayoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        bannerViewLayoutParams.gravity = Gravity.BOTTOM;
        BannerView banner = new BannerView(activity);
        banner.setBackgroundColor(Color.TRANSPARENT);
        banner.setAdListener(renderAdListener);
        activity.addContentView(banner, bannerViewLayoutParams);
        bannerView = banner;
        return bannerView;
    }

    public void show() {
        executeMainThread(() -> {
            if (bannerView == null) {
                return;
            }
            bannerView.resume();
            bannerView.setVisibility(View.VISIBLE);
        });
    }

    public void hide() {
        executeMainThread(() -> {
            if (bannerView == null) {
                return;
            }
            bannerView.pause();
            bannerView.setVisibility(View.GONE);
        });
    }

    public void destroy() {
        executeMainThread(() -> {
            if (bannerView == null) {
                return;
            }
            bannerView.destroy();
            ViewParent parent = bannerView.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(bannerView);
            }
            bannerView = null;
        });
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public void setBannerRefresh(long bannerRefresh) {
        this.bannerRefresh = bannerRefresh;
    }

    public void setUnityAdSizeByType(UnityBannerSize unityBannerSize) {
        this.unityBannerSize = unityBannerSize;
    }

    public void setUnityAdSizeType(int unityBannerSize) {
        UnityBannerSize[] values = UnityBannerSize.values();
        if (unityBannerSize < 0 || unityBannerSize >= values.length) {
            throw new IllegalStateException("Range[0.." + (values.length - 1) + "]");
        }
        setUnityAdSizeByType(values[unityBannerSize]);
    }

    private void executeMainThread(Runnable runnable) {
        AdsExecutor.executeMainThread(runnable);
    }
}
