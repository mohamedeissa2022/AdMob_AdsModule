package com.admob_ads_module

import android.content.Context
import android.view.ViewGroup
import com.ads.adsmanagermodule.IAdBanner
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

 internal class AdmobAdBanner: IAdBanner {
    private lateinit var AdBannerId: String
    private lateinit var adView: AdView
    private lateinit var context: Context
     val  adview: AdView
        get() {return adView}
    constructor(AdBannerId: String, context: Context)
    {
        this.AdBannerId=AdBannerId
        PrepareAdBanner()
    }
    private  fun   PrepareAdBanner(){
        adView = AdView(context)
        val adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, 360)
        adView.setAdSize(adSize)
        adView.adUnitId=AdBannerId
    }
    override fun LoadAdBanner()
    {
        val ad_requst: AdRequest = AdRequest.Builder().build()
        adView.loadAd(ad_requst)
    }

    fun destroyBanner() {
        // Remove banner from view hierarchy.
        val parentView = adView?.parent
        if (parentView is ViewGroup) {
            parentView.removeView(adView)
        }
        // Destroy the banner ad resources.
        adView?.destroy()
        // Drop reference to the banner ad.
        null.also {
            if (it != null) {
                adView = it
            }
        }

    }
    fun AdBannerListenrResultMassages(){
        adView?.adListener =
            object : AdListener() {
                override fun onAdClicked() {
                    println("Ad Clicked")
                }

                override fun onAdClosed() {
                    println("Ad Closed")



                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    println("Ad Failed To Load: ${adError.message}")

                    LoadAdBanner()
                }

                override fun onAdImpression() {
                    println("Ad Impression")



                }

                override fun onAdLoaded() {
                    println("Ad Loaded")


                }

                override fun onAdOpened() {
                    println("Ad Opened")


                }
            }
    }

}