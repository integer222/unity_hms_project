using UnityEngine;

namespace MicroLibs.Unity.Hms.Ads.Core
{
    public class AdStatusListenerProxy : AndroidJavaProxy
    {
        
        private readonly AdStatusListener _listener;

        public AdStatusListenerProxy(AdStatusListener listener) : base(AdConstants.ADAdListenerClassName)
        {
            _listener = listener;
        }

        public void onAdClosed()
        {
            _listener.OnAdClosed();
        }

        public void onAdFailed(int errorCode)
        {
            _listener.OnAdFailed(errorCode);
        }

        public void onAdLeave()
        {
            _listener.OnAdLeave();
        }

        public void onAdOpened()
        {
            _listener.OnAdOpened();
        }

        public void onAdLoaded()
        {
            _listener.OnAdLoaded();
        }

        public void onAdClicked()
        {
            _listener.OnAdClicked();
        }

        public void onAdImpression()
        {
            _listener.OnAdImpression();
        }
    }
    

}