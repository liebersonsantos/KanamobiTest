package com.liebersonsantos.kanamobitest.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.liebersonsantos.kanamobitest.R;

import butterknife.BindView;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.constraintSplash)
    ConstraintLayout constraintLayout;
    @BindView(R.id.imageSplash)
    ImageView imageView;

    int SPLASH_DISPLAY_LENGTH = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashAnimated();

    }

    private void splashAnimated(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        animation.reset();
        if (constraintLayout != null){
            constraintLayout.clearAnimation();
            constraintLayout.startAnimation(animation);
        }

        animation = AnimationUtils.loadAnimation(this, R.anim.translate);
        animation.reset();
        if (imageView != null){
            imageView.clearAnimation();
            imageView.startAnimation(animation);
        }

        initHandler();
    }

    private void initHandler() {
        Handler handler = new Handler();
        handler.postDelayed(() -> goToMain(), SPLASH_DISPLAY_LENGTH);
    }

    private void goToMain() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
