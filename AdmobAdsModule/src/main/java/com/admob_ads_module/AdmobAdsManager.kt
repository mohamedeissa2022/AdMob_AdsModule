package com.admob_ads_module

import android.app.Activity
import android.content.Context
import android.util.Log
import com.ads.adsmanagermodule.AdsManager
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener

class AdmobAdsManager {
constructor(context: Context){
    MobileAds.initialize(context,object : OnInitializationCompleteListener{

        override fun onInitializationComplete(p0: InitializationStatus)
        {
            Log.d(Constraints.AdmobAdsTAG+"initialize.onInitializationComplete","\n\n\n___________Initialization Complete___________\n\n\n")
        }
    })
}
    fun  BannerAdLoad(adBannerId:String,context: Context): AdView{
       var  admobAdBanner:AdmobAdBanner=AdmobAdBanner(adBannerId,context)
        AdsManager.LoadAdBanner(admobAdBanner)
        admobAdBanner.AdBannerListenrResultMassages()
        return  admobAdBanner.adview
    }
  private  lateinit var  admobInterstatilAd:AdmobInterstatialAd
  private  lateinit var admobRewordedInterstatialAd: AdmobRewordedInterstatialAd
    fun  InterstatilAdLoad(InterstatilAdId:String,context: Activity)
    {
         admobInterstatilAd=AdmobInterstatialAd(InterstatilAdId,context)
        AdsManager.LoadInterstitalAd(admobInterstatilAd)
    }
    fun  ShowInterstatialAd(){
        AdsManager.ShowInterstitalAd(admobInterstatilAd)
    }


    fun  RewordedInterstatilAdLoad(RewordedInterstatilAdId:String,context: Activity)
    {
        admobRewordedInterstatialAd= AdmobRewordedInterstatialAd(RewordedInterstatilAdId,context)
        AdsManager.LoadRewordedInterstitalAd(admobRewordedInterstatialAd)
    }
    fun  ShowRewordedInterstatialAd(){
        AdsManager.ShowRewordedInterstitalAd(admobRewordedInterstatialAd)
    }


}