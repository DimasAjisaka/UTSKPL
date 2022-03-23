package com.example.penilaian;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.example.penilaian.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashScreenBinding binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //pengaturan animasi
        int splashDuration = 2000;

        binding.icon.setAlpha(0);
        binding.iconDesc.setAlpha(0);
        binding.icon.setTranslationZ(800);
        binding.icon.setTranslationY(30);
        binding.iconDesc.setTranslationZ(-800);
        binding.iconDesc.setTranslationY(-30);
        binding.icon.animate().alpha(1).translationY(0).setStartDelay(500).setDuration(500).start();
        binding.iconDesc.animate().alpha(1).translationY(0).setStartDelay(500).setDuration(500).start();

        //next tampilan yang akan dituju
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this,ResultActivity.class));
            finish();
        },splashDuration);
    }
}