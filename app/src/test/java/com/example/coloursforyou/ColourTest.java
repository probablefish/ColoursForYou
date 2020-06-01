package com.example.coloursforyou;

import android.os.Parcel;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColourTest {

    private final String HEX = "#FFFFFF";
    private final String NAME = "testName";
    private final String NAME_CAPITALIZED = "TestName";

    @Test
    public void testNameIsCapitalized() {
        assertEquals(NAME_CAPITALIZED, generateTestColour().getName());
    }

    @Test
    public void getHexTest() {
        assertEquals(HEX, generateTestColour().getHex());
    }

    @Test
    public void writeToParcelTest() {
        Parcel parcel = MockParcel.obtain();
        generateTestColour().writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        assertEquals(HEX, parcel.readString());
        assertEquals(NAME_CAPITALIZED, parcel.readString());
    }

    @Test
    public void createFromParcelTest() {
        Parcel parcel = MockParcel.obtain();
        generateTestColour().writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        Colour parceledColour = Colour.CREATOR.createFromParcel(parcel);
        assertEquals(HEX, parceledColour.getHex());
        assertEquals(NAME_CAPITALIZED, parceledColour.getName());
    }

    @Test
    public void creatorArrayTest() {
        assertEquals(5, Colour.CREATOR.newArray(5).length);
    }

    @Test
    public void describeContents() {
        assertEquals(0, generateTestColour().describeContents());
    }

    private Colour generateTestColour() {
        return new Colour(HEX, NAME);
    }
}