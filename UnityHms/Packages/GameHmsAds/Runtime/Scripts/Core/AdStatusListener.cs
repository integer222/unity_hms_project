using System;

namespace MicroLibs.Unity.Hms.Ads.Core
{
    public class AdStatusListener
    {
         public Action OnAdClosed = delegate {  };

         public Action<int> OnAdFailed = delegate {  };

         public Action OnAdLeave = delegate {  };

         public Action OnAdOpened = delegate {  };

         public Action OnAdLoaded = delegate {  };

         public Action OnAdClicked = delegate {  };

         public Action OnAdImpression = delegate {  };
    }
}