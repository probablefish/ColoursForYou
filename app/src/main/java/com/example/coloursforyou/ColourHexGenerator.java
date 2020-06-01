package com.example.coloursforyou;

import java.util.Random;

class ColourHexGenerator {

    static String getRandomColourHex()
    {
        Random random = new Random();
        return String.format("#%06x", random.nextInt() & 0xFFFFF);
    }


}
