package com.nk.adapptemp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button firstLessonAdBtn, secondLessonAdBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setReferences();

        firstLessonAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), FirstLessonADsActivity.class);
                startActivity(intent);
            }
        });

        secondLessonAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), SecondLessonADsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setReferences(){
        firstLessonAdBtn = findViewById(R.id.first_lesson_ad_btn);
        secondLessonAdBtn = findViewById(R.id.second_lesson_ad_btn);
    }
}