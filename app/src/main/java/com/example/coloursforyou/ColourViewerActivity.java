package com.example.coloursforyou;

import android.os.Bundle;
import android.os.Parcelable;

import java.util.Arrays;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

public class ColourViewerActivity extends FragmentActivity {

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colour_viewer_activity);
        configureColourViewPager();
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    private void configureColourViewPager() {
        ColourPagerAdapter pagerAdapter = new ColourPagerAdapter(getColoursFromExtras());
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
    }

    private Colour[] getColoursFromExtras() {
        Colour[] colours = null;
        Parcelable[] coloursParcelArray = getIntent().getParcelableArrayExtra("colours");

        if (coloursParcelArray != null) {
            colours = Arrays.copyOf(coloursParcelArray, coloursParcelArray.length, Colour[].class);
        }

        return colours;
    }
}
