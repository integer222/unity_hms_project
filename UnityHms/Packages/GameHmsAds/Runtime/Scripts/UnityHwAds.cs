using UnityEngine;

namespace MicroLibs.Unity.Hms.Ads
{
    public static class UnityHwAds
    {
        private static bool _isInit;

        public static bool IsInit()
        {
            return _isInit;
        }
        
        public static void Init()
        {
            if (_isInit)
            {
                return;
            }
            var ads = new AndroidJavaClass(Constants.AdsClassName);
            ads.CallStatic("init", AndroidUtils.GetCurrentActivity());
            _isInit = true;
        }

    }
}