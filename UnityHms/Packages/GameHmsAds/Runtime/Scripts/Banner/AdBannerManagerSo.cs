using MicroLibs.Unity.Hms.Ads.Core;
using UnityEngine;

namespace MicroLibs.Unity.Hms.Ads.Banner
{
    [CreateAssetMenu(fileName = "AdSimpleBanner", menuName = "HMS/ADS/Simple Banner Manager", order = 51)]
    public class AdBannerManagerSo : AdBaseBannerManagerSo
    {
        private readonly AdBanner _banner = new AdBanner();

        private bool _isInit;

        public override void Init()
        {
            if (_isInit)
            {
                return;
            }

            _banner.Init();
            Debug.Log("[AdBannerManager] Init");
            _isInit = true;
        }

        public override void ReLoad()
        {
            Debug.Log("[AdBannerManager] Load");
            if (adId == null)
            {
                Debug.LogWarning("[AdBannerManager] adId is null");
                return;
            }

            _banner.Load(adId, AdBannerSize.BANNER_SIZE_360_57, isRefresh ? refreshSeconds : 0);
        }

        public override void Show()
        {
            Debug.Log("[AdBannerManager] Show");
            _banner.Show();
        }

        public override void Hide()
        {
            Debug.Log("[AdBannerManager] Hide");
            _banner.Hide();
        }

        public override void Destroy()
        {
            Debug.Log("[AdBannerManager] Destroy");
            _banner.Destroy();
        }
    }
}