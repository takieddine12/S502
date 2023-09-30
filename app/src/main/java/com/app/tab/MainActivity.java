package com.app.tab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ImageView fab;
    private Button animate;
    private ImageView arrowBack;
    private LinearLayout mainLayout ,layout;
    private Boolean isScaled = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabLayout = findViewById(R.id.tabLayout);
        animate = findViewById(R.id.button);
        fab = findViewById(R.id.fab);
        arrowBack = findViewById(R.id.arrowBack);
        mainLayout = findViewById(R.id.mainLayout);
        layout = findViewById(R.id.layout);

        fab.setScaleX(0.6f);
        fab.setScaleY(0.6f);

        // Set Toolbar To Translucent
        int translucentBlack = Color.argb(100, 0, 0, 0);
        layout.setBackgroundColor(translucentBlack);

        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Arrow Back Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        tabLayout.addTab(tabLayout.newTab().setText("Animation"));
        tabLayout.addTab(tabLayout.newTab().setText("Background"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Handle tab selection
                int position = tab.getPosition();
                // You can perform actions based on the selected tab here
                switch (position) {
                    case 0:
                        // Tab 1 is selected
                        break;
                    case 1:
                        // Tab 2 is selected
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Handle tab unselection
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Handle tab reselection
            }
        });

        showGradients(mainLayout);

        animate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // scale icon
                scaleFab();
            }
        });

    }

    private void scaleFab() {
        if(!isScaled){
            isScaled = true;
            ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(fab,
                    PropertyValuesHolder.ofFloat("scaleX", 2.2f),
                    PropertyValuesHolder.ofFloat("scaleY", 2.2f));

            objectAnimator.setInterpolator(new BounceInterpolator());
            objectAnimator.setDuration(3000);
            objectAnimator.start();
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    scaleFab();
                }
            });
        }
        else {
            isScaled = false;
            ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(fab,
                    PropertyValuesHolder.ofFloat("scaleX", 0.6f),
                    PropertyValuesHolder.ofFloat("scaleY", 0.6f));

            objectAnimator.setInterpolator(new BounceInterpolator());
            objectAnimator.setDuration(3000);
            objectAnimator.start();
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    scaleFab();
                }
            });
        }
    }
    private void showGradients(LinearLayout constraintLayout){
        // Create an AnimationDrawable
        AnimationDrawable animDrawable = new AnimationDrawable();
        animDrawable.setOneShot(false); // Set to false to make the animation run infinitely

        // Create the first gradient drawable
        GradientDrawable gradient1 = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, new int[]{
                       Color.rgb(0x20, 0x66, 0xB0),
                       Color.rgb(0x2B, 0x8B, 0xC5)});

        // Create the second gradient drawable
        GradientDrawable gradient2 = new GradientDrawable(
                GradientDrawable.Orientation.RIGHT_LEFT, new int[]{
                        Color.rgb(0x12, 0x44, 0x8E),
                        Color.rgb(0x19, 0x57, 0x9E)});


        animDrawable.addFrame(gradient1, 3000);
        animDrawable.addFrame(gradient2, 3000);

        animDrawable.setEnterFadeDuration(2500);
        animDrawable.setExitFadeDuration(2500);


        constraintLayout.setBackgroundDrawable(animDrawable);
        animDrawable.start();
    }

}