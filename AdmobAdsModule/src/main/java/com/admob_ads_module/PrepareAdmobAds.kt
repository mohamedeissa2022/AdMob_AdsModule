package com.admob_ads_module

import android.content.Context
import android.util.Log
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener

object PrepareAdmobAds
{
    fun  Initialize(context: Context)
    {
        MobileAds.initialize(context,object : OnInitializationCompleteListener {
            override fun onInitializationComplete(p0: InitializationStatus) {
                Log.d(Constraints.AdmobAdsTAG+"PrepareAdmobAds.Initialize", "\n+++++++++++\nInitialization Complete .\n+++++++++++\n")

            }


        })
    }



}
