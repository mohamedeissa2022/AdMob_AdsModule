package com.admob_ads_module

import android.app.Activity
import android.util.Log
import com.ads.adsmanagermodule.IRewordedInterstatialAd
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

internal class AdmobRewordedInterstatialAd : IRewordedInterstatialAd {
   private lateinit var RewordedAd: RewardedAd
    private lateinit var activity: Activity
    private lateinit var AD_UNIT_ID: String

    constructor(AD_UNIT_ID: String,activity: Activity) {
        this.activity = activity
        this.AD_UNIT_ID = AD_UNIT_ID
    }


    override fun LoadRewordedInterstatialAd() {
        RewardedAd.load(
            activity,
            AD_UNIT_ID,
            AdRequest.Builder().build(),
            object : RewardedAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedAd) {
                    Log.d(Constraints.AdmobAdsTAG+"LoadRewordedInterstatialAd.onAdLoaded:", "Ad was loaded.")
                    RewordedAd = ad
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(Constraints.AdmobAdsTAG+"LoadRewordedInterstatialAd.onAdFailedToLoad:", adError.message)
                    null.also {
                        if (it != null) {
                            RewordedAd = it
                        }
                    }
                }
            },
        )
    }

    override fun ShowRewordedInterstatialAd() {
        if(activity==null)
            return
        RewordedAd?.show(activity, object : OnUserEarnedRewardListener
        {
            override fun onUserEarnedReward(rewardItem: RewardItem)
            {
                Log.d(Constraints.AdmobAdsTAG+"ShowRewordedInterstatialAd.onUserEarnedReward:", "Reword Type:${rewardItem.type.toString()}"+"\tvalue:"+rewardItem.amount.toString())
            }

        }
        )
    }
}