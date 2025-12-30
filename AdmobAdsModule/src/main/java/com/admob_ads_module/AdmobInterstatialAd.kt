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

internal class AdmobInterstatialAd: IInterstitalAd
{
    private   var interstitialAd: InterstitialAd?=null

    private  var activity: Activity? =null
    private lateinit var AD_UNIT_ID: String
    constructor( ad_unit_id: String,activity: Activity){
        if(ad_unit_id.isEmpty())
            throw Exception("Ad Unit Id is Empty")
        this.AD_UNIT_ID=ad_unit_id
        this.activity=activity

    }
    override fun LoadInterstitalAd()
    {
        InterstitialAd.load(
            activity!!,
            AD_UNIT_ID,
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback()
            {
                override fun onAdLoaded(ad: InterstitialAd)
                {
                    Log.d(Constraints.AdmobAdsTAG+"LoadInterstitalAd.onAdLoaded", "\n+++++++++++++++++\n"+"Ad was loaded."+"\n+++++++++++++++++\n")
                    interstitialAd = ad
                    InterstatialAdCalBacksResult()
                }

                override fun onAdFailedToLoad(adError: LoadAdError)
                {
                    Log.d(Constraints.AdmobAdsTAG+"LoadInterstitalAd.onAdFailedToLoad", "\n+++++++++++++++++\n"+adError.message+"\n+++++++++++++++++\n")
                    interstitialAd = null
                }
            },
        )

    }

    override fun ShowInterstatialAd()
    {
        if(interstitialAd==null)
            return
        if(activity!!.isFinishing || activity!!.isDestroyed){
            Log.d("Ads", "Activity not valid")

            return
        }
        activity!!.runOnUiThread {

            interstitialAd?.show(activity!!)
            interstitialAd=null

        }



    }
    private fun  InterstatialAdCalBacksResult()
    {



       interstitialAd?.fullScreenContentCallback =
           object : FullScreenContentCallback()
           {
               override fun onAdDismissedFullScreenContent()
               {
                   Log.d(
                       Constraints.AdmobAdsTAG + "onAdDismissedFullScreenContent",
                       "\n+++++++++++\nAd was dismissed.\n+++++++++++\n"
                   )

                   interstitialAd = null



               }

       override fun onAdFailedToShowFullScreenContent(adError: AdError)
       {
           Log.d(
               Constraints.AdmobAdsTAG + "onAdFailedToShowFullScreenContent",
               "\n+++++++++++++++++\nAd failed to show.\n++++++++++++++\n"
           )
           interstitialAd = null



       }

                override fun onAdShowedFullScreenContent() {
                    Log.d(Constraints.AdmobAdsTAG+"onAdShowedFullScreenContent", "\n++++++++++++++\nAd showed fullscreen content.\n++++++++++++++\n")
                }

                override fun onAdImpression() {
                    Log.d(Constraints.AdmobAdsTAG+"onAdImpression", "\n++++++++++++++\nAd recorded an impression.\n++++++++++++++\n")
                }

                override fun onAdClicked() {
                    Log.d(Constraints.AdmobAdsTAG+"onAdClicked", "\n++++++++++++++\nAd was clicked.\n++++++++++++++\n")
                }
            }
    }
    }


