package com.admob_ads_module

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.ads.adsmanagermodule.IInterstitalAd
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

internal class AdmobInterstatialAd: IInterstitalAd {
 private  lateinit var interstitialAd: InterstitialAd
    private lateinit var activity: Activity
    private lateinit var AD_UNIT_ID: String
    constructor( AD_UNIT_ID: String,activity: Activity){
        this.AD_UNIT_ID=AD_UNIT_ID
        this.activity=activity
    }
    override fun LoadInterstitalAd() {
        InterstitialAd.load(
            activity,
            AD_UNIT_ID,
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    interstitialAd = ad
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError.message)
                    null.also {
                        if (it != null) {
                            interstitialAd = it
                        }
                    }
                }
            },
        )
        InterstatialAdCalBacksResult()
    }

    override fun ShowInterstatialAd()
    {
        if(activity!=null)
        interstitialAd?.show(activity)
    }
   private fun  InterstatialAdCalBacksResult(){
        interstitialAd?.fullScreenContentCallback =
            object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(Constraints.AdmobAdsTAG+":onAdDismissedFullScreenContent", "\nAd was dismissed.\n")

                    null.also {
                        if (it != null) {
                            interstitialAd = it
                        }
                    }
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.d(Constraints.AdmobAdsTAG+":onAdFailedToShowFullScreenContent", "\nAd failed to show.\n")

                    null.also {
                        if (it != null) {
                            interstitialAd = it
                        }
                    }
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(Constraints.AdmobAdsTAG+":onAdShowedFullScreenContent", "\nAd showed fullscreen content.\n")
                }

                override fun onAdImpression() {
                    Log.d(Constraints.AdmobAdsTAG+":onAdImpression", "\nAd recorded an impression.\n")
                }

                override fun onAdClicked() {
                    Log.d(Constraints.AdmobAdsTAG+":onAdClicked", "\nAd was clicked.\n")
                }
            }
    }
}