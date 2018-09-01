package net.bplaced.therefactory.nomoore.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Christian on 16.02.2018.
 */

public class MediaManager {

    public static AssetManager assetManager = new AssetManager();

    public static void preloadSound(String assetPath) {
        if (!assetManager.isLoaded(assetPath)) {
            assetManager.load(assetPath, Sound.class);
            assetManager.finishLoading();
        }
    }

    public static void preloadMusic(String assetPath) {
        if (!assetManager.isLoaded(assetPath)) {
            assetManager.load(assetPath, Music.class);
            assetManager.finishLoading();
        }
    }

    public static Music getMusic(String assetPath) {
        preloadMusic(assetPath);
        return assetManager.get(assetPath, Music.class);
    }

    public static Sound getSound(String assetPath) {
        preloadSound(assetPath);
        return assetManager.get(assetPath, Sound.class);
    }
}
