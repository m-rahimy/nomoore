package net.bplaced.therefactory.nomoore.utils;

public class MathUtils {

    public static float oscilliate(float x, float min, float max, float period) {
        return max - (float) (Math.sin(x * 2f * Math.PI / period) * ((max - min) / 2f) + ((max - min) / 2f));
    }

}
