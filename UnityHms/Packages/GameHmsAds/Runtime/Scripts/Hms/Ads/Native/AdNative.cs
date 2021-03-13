using Progstech.Unity.Hms.Ads.Core;
using UnityEngine;

namespace Progstech.Unity.Hms.Ads.Native
{
    public class AdNative
    {
        
        private AndroidJavaObject _adProxy;
        private readonly AdStatusListener _listener = new AdStatusListener();
        private AdStatusListenerProxy _adStatusListenerProxy;
        
        public void Init()
        {
            if (Application.isEditor)
            {
                Debug.Log("[AdNative] Editor fake init");
                return;
            }
            UnityHwAds.Init();
            _adStatusListenerProxy = new AdStatusListenerProxy(_listener);
            _adProxy = new AndroidJavaObject(
                AdConstants.ADNativeProxyClassName, 
                AndroidUtils.GetCurrentActivity(), 
                _adStatusListenerProxy);
        }
        
        public void Load(string adId)
        {
            if (_adProxy == null)
            {
                Debug.Log("[AdNative] no init");
                return;
            }
            _adProxy.Call("loadAd", adId);
        }

        public AdStatusListener GetListener()
        {
            return _listener;
        }
        
        public void Show()
        {
            _adProxy?.Call("show");
        }
        
        public void Hide()
        {
            _adProxy?.Call("hide");
        }

        public void Destroy()
        {
            _adProxy?.Call("destroy");
        }
    }
}