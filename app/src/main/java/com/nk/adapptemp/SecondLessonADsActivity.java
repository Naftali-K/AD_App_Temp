package com.nk.adapptemp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdValue;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class SecondLessonADsActivity extends AppCompatActivity {

    private AdView bannerAdView;
    private Button startInterstitialAdBtn, rewardedVideAdBtn;

    private AdRequest adRequest;
    private InterstitialAd mInterstitialAd;
    private RewardedAd mRewardedAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_lesson_ads);
        setReferences();

        MobileAds.initialize(this);

        adRequest = new AdRequest.Builder().build();
        bannerAdView.loadAd(adRequest);

        // Banner Ad
        bannerAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Toast.makeText(getApplicationContext(), "Ad loaded!", Toast.LENGTH_SHORT).show();
            }
            //have many more actions
        });

        // Ad for full screen, can close
        startInterstitialAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InterstitialAd.load(getBaseContext(), "ca-app-pub-3940256099942544/1033173712", adRequest,
                        new InterstitialAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                                super.onAdLoaded(interstitialAd);
                                mInterstitialAd = interstitialAd;
                            }
                        });

                if (mInterstitialAd != null){
                    mInterstitialAd.show(SecondLessonADsActivity.this);
                }
            }
        });

        // Ad with timing, and video
        rewardedVideAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RewardedAd.load(getBaseContext(), "ca-app-pub-3940256099942544/5224354917", adRequest,
                        new RewardedAdLoadCallback() {
                            @Override
                            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                                super.onAdLoaded(rewardedAd);
                                mRewardedAd = rewardedAd;
                            }
                        });

                if (mRewardedAd != null){
                    mRewardedAd.show(SecondLessonADsActivity.this, new OnUserEarnedRewardListener() {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            Toast.makeText(getApplicationContext(), "Rewarded Ad Finished", Toast.LENGTH_SHORT).show();
                        }
                    });

                    mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdClicked() {
                            super.onAdClicked();
                            Toast.makeText(getApplicationContext(), "Ad clicked", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            Toast.makeText(getApplicationContext(), "Ad closed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    private void setReferences(){
        bannerAdView = findViewById(R.id.banner_ad_view);
        startInterstitialAdBtn = findViewById(R.id.start_interstitial_ad_btn);
        rewardedVideAdBtn = findViewById(R.id.rewarded_vide_ad_btn);
    }
}