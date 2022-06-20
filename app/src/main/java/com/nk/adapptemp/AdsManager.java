package com.nk.adapptemp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class AdsManager {

    private static final String TAG = "Test_code";

    private Context context;

    private InterstitialAd mInterstitialAd;

    public AdsManager(Context context) {
        this.context = context;

        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });
    }

    public void createAds(AdView adView){
        AdRequest adRequest = new AdRequest.Builder().build();

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Toast.makeText(context, "BANNER Error: " + loadAdError.getCode(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Toast.makeText(context, "Ad's BANNER in loaded", Toast.LENGTH_SHORT).show();
            }
        });

        adView.loadAd(adRequest);
    }

    public void createInterstitialAd(Activity activity){
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(context, "ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);

                        mInterstitialAd = null;
                        Toast.makeText(context, "INTERSTITIAL Error: " + loadAdError.getCode(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onAdFailedToLoad: " + loadAdError.getMessage());
                    }

                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        super.onAdLoaded(interstitialAd);

                        mInterstitialAd = interstitialAd;
                        Toast.makeText(context, "Ad's INTERSTITIAL in loaded", Toast.LENGTH_SHORT).show();
                    }
                });

//        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
//            @Override
//            public void onAdDismissedFullScreenContent() {
//                super.onAdDismissedFullScreenContent();
//                Log.d(TAG, "onAdDismissedFullScreenContent: This ad was dismissed");
//            }
//
//            @Override
//            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
//                super.onAdFailedToShowFullScreenContent(adError);
//                Log.d(TAG, "onAdFailedToShowFullScreenContent: The ad failed to show");
//            }
//
//            @Override
//            public void onAdShowedFullScreenContent() {
//                super.onAdShowedFullScreenContent();
//                Log.d(TAG, "onAdShowedFullScreenContent: This ad was shown");
//                mInterstitialAd = null;
//            }
//        });

        if (mInterstitialAd != null){
            mInterstitialAd.show(activity);
        }else{
            Log.d(TAG, "createInterstitialAd: This interstitial ad wasn't ready yet");
        }
    }
}
