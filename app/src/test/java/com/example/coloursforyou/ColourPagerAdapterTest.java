package com.example.coloursforyou;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ColourPagerAdapterTest {

    @Test
    public void numberOfItemsTest() {
        ColourPagerAdapter colourPagerAdapter = new ColourPagerAdapter(new Colour[2]);
        assertEquals(2, colourPagerAdapter.getItemCount());
    }

    @Test
    public void viewHolderTest() {
        Context context = mock(Context.class);
        ViewGroup viewGroup = new FrameLayout(context);
        when(viewGroup.getContext()).thenReturn(context);

    }
}