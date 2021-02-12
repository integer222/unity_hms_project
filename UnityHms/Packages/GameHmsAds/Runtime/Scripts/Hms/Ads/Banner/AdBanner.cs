using Progstech.Unity.Hms.Ads.Core;
using UnityEngine;

namespace Progstech.Unity.Hms.Ads.Banner
{
    public class AdBanner
    {

        private AndroidJavaObject _adBannerProxy;
        private readonly AdStatusListener _listener = new AdStatusListener();
        private AdStatusListenerProxy _adStatusListenerProxy;
        
        public void Init()
        {
            if (Application.isEditor)
            {
                Debug.Log("[AdBanner] Editor fake init");
                return;
            }
            UnityHwAds.Init();
            _adStatusListenerProxy = new AdStatusListenerProxy(_listener);
            _adBannerProxy = new AndroidJavaObject(
                AdConstants.ADBannerProxyClassName, 
                AndroidUtils.GetCurrentActivity(), 
                _adStatusListenerProxy);
        }
        
        public AdStatusListener GetListener()
        {
            return _listener;
        }

        public void Load(string adId, int bannerSize, long bannerRefresh)
        {
            if (_adBannerProxy == null)
            {
                Debug.Log("[AdBanner] no init");
                return;
            }
            _adBannerProxy.Call("setAdId", adId);
            _adBannerProxy.Call("setUnityAdSizeType", bannerSize);
            _adBannerProxy.Call("setBannerRefresh", bannerRefresh);
            _adBannerProxy.Call("loadAd", new AdParam().ToJavaParam());
        }

        public void Show()
        {
            _adBannerProxy?.Call("show");
        }
        
        public void Hide()
        {
            _adBannerProxy?.Call("hide");
        }

        public void Destroy()
        {
            _adBannerProxy?.Call("destroy");
        }
    }
}