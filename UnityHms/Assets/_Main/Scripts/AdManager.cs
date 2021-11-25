using MicroLibs.Unity.Hms.Ads.Banner;
using MicroLibs.Unity.Hms.Ads.Core;
using MicroLibs.Unity.Hms.Ads.Native;
using UnityEngine;
using UnityEngine.UI;

namespace _Main.Scripts
{
    public class AdManager : MonoBehaviour
    {

        [SerializeField] private AdBannerManagerSo banner;
        [SerializeField] private AdNativeManagerSo native;

        [SerializeField] private Toggle nativeToggle;

        private AdBaseBannerManagerSo _currentManager;

        private void Awake()
        {
            nativeToggle.onValueChanged.AddListener(OnChangeToggle);
            UpdateCurrentManager();
        }

        private void OnChangeToggle(bool value)
        {
            banner.Destroy();
            native.Destroy();
            UpdateCurrentManager();
        }

        private void UpdateCurrentManager()
        {
            _currentManager = nativeToggle.isOn ? (AdBaseBannerManagerSo) native : banner;
        }

        public void Load()
        {
            _currentManager.Init();
            _currentManager.ReLoad();
        }
        
        public void Show()
        {
            _currentManager.Show();
        }
        
        public void Hide()
        {
            _currentManager.Hide();
        }
        
        public void Destroy()
        {
            _currentManager.Destroy();
        }
        
    }
}