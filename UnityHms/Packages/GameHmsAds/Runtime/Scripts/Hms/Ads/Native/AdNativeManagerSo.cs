using System.Threading;
using System.Threading.Tasks;
using Progstech.Unity.Hms.Ads.Core;
using UnityEngine;

namespace Progstech.Unity.Hms.Ads.Native
{
    [CreateAssetMenu(fileName = "AdNativeBanner", menuName = "HMS/ADS/Native Banner Manager", order = 51)]
    public class AdNativeManagerSo : AdBaseBannerManagerSo
    {
        private readonly AdNative _native = new AdNative();

        private bool _isInit;

        private CancellationTokenSource _source;

        public override void Init()
        {
            if (_isInit)
            {
                return;
            }

            _native.Init();
            Debug.Log("[AdNativeManager] Init");
            _isInit = true;
        }

        public override void ReLoad()
        {
            Debug.Log("[AdNativeManager] ReLoad");
            if (adId == null)
            {
                Debug.LogWarning("[AdNativeManager] adId is null");
                return;
            }

            SourceReset();
            _source = new CancellationTokenSource();
            RefreshReLoad(_source.Token);
        }

        private async void RefreshReLoad(CancellationToken token)
        {
            Debug.Log("[AdNativeManager] RetryReLoad");
            ReLoadLocal();
            while (isRefresh && !token.IsCancellationRequested)
            {
                try
                {
                    await Task.Delay(refreshSeconds * 1000, token);
                    Debug.Log("[AdNativeManager] RetryReLoad Tick");
                    ReLoadLocal();
                }
                catch (TaskCanceledException)
                {
                    Debug.Log("[AdNativeManager] TaskCanceledException");
                    return;
                }
            }
        }

        private void ReLoadLocal()
        {
            Debug.Log("[AdNativeManager] ReLoadLocal");
            _native.Load(adId);
        }

        public override void Show()
        {
            Debug.Log("[AdNativeManager] Show");
            if (isRefresh)
            {
                ReLoad();
            }

            _native.Show();
        }

        public override void Hide()
        {
            Debug.Log("[AdNativeManager] Hide");
            SourceReset();
            _native.Hide();
        }

        public override void Destroy()
        {
            Debug.Log("[AdNativeManager] Destroy");
            SourceReset();
            _native.Destroy();
        }

        private void SourceReset()
        {
            Debug.Log("[AdNativeManager] SourceReset");
            if (_source != null)
            {
                _source.Cancel();
                _source.Dispose();
            }

            _source = null;
        }
    }
}