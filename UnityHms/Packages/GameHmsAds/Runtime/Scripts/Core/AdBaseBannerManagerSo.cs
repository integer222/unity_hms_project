using UnityEngine;

namespace MicroLibs.Unity.Hms.Ads.Core
{
    
    public abstract class AdBaseBannerManagerSo : ScriptableObject, IAdSimpleManager
    {
        
        [SerializeField]
        protected string adId;

        [SerializeField] protected bool isRefresh;
        [Range(30, 120)] [SerializeField] protected int refreshSeconds = 30;

        public abstract void Init();

        public abstract void ReLoad();

        public abstract void Show();

        public abstract void Hide();

        public abstract void Destroy();
        
        
        
    }
}