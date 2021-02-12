﻿using UnityEngine;

namespace Progstech.Unity.Hms.Ads
{
    public static class AndroidUtils
    {
        public static AndroidJavaObject GetCurrentActivity()
        {
            var playerClass = new AndroidJavaClass(Constants.UnityPlayerClassName);
            return playerClass.GetStatic<AndroidJavaObject>("currentActivity");
        }
    }
}