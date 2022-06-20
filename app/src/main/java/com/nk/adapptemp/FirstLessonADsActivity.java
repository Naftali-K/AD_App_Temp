package com.nk.adapptemp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdView;

public class FirstLessonADsActivity extends AppCompatActivity {

    private AdView adView;
    private AdsManager adsManager;
    private Button startInterstitialAdBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_lesson_ads);

        setReferences();

        adsManager = new AdsManager(this);

        adsManager.createAds(adView);

        startInterstitialAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adsManager.createInterstitialAd(FirstLessonADsActivity.this);
            }
        });
    }

    private void setReferences(){
        adView = findViewById(R.id.adView);
        startInterstitialAdBtn = findViewById(R.id.start_interstitial_ad_btn);
    }
}