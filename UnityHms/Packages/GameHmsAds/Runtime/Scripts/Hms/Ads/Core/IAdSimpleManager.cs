namespace Progstech.Unity.Hms.Ads.Core
{
    public interface IAdSimpleManager
    {
        void Init();
        void ReLoad();
        void Show();
        void Hide();
        void Destroy();
    }
}