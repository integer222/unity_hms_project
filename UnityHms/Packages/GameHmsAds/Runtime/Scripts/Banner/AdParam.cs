using UnityEngine;

namespace MicroLibs.Unity.Hms.Ads.Banner
{
    public class AdParam
    {
        
        public AndroidJavaObject ToJavaParam()
        {
            var builder = new AndroidJavaObject("com.huawei.hms.ads.AdParam$Builder");
            return builder.Call<AndroidJavaObject>("build");
        }
        
    }
}