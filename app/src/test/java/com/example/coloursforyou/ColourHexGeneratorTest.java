package com.example.coloursforyou;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColourHexGeneratorTest {

    @Test
    public void getRandomColourHexLength() {
        assertEquals(7, ColourHexGenerator.getRandomColourHex().length());
    }

    @Test
    public void getRandomColourHexIsAHexColour() {
        assertTrue(ColourHexGenerator.getRandomColourHex().matches("^(#[0-9a-fA-F]{6}){1}$"));
    }

    @Test
    public void getRandomColourHexIsRandom() {
        String colour1 = ColourHexGenerator.getRandomColourHex();
        String colour2 = ColourHexGenerator.getRandomColourHex();
        assertNotEquals(colour1, colour2);
    }
}