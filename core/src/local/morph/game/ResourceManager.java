package local.morph.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;

import java.util.Iterator;

/**
 * Created by morph on 07.01.2016.
 */
public class ResourceManager {
    private ArrayMap<String, Texture> textures;
    private ArrayMap<String, Music> musics;
    private ArrayMap<String, Sound> sounds;
    private ArrayMap<String, BitmapFont> fonts;

    public ResourceManager() {
        this.textures = new ArrayMap<String, Texture>();
        this.musics = new ArrayMap<String, Music>();
        this.sounds = new ArrayMap<String, Sound>();
        this.fonts = new ArrayMap<String, BitmapFont>();
    }

    public Texture loadTexture(String name) {
        Texture texture = new Texture(name);
        textures.put(name, texture);

        return texture;
    }

    public Music loadMusic(String name) {
        Music music = Gdx.audio.newMusic(Gdx.files.internal(name));
        musics.put(name, music);

        return music;
    }

    public Sound loadSound(String name) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(name));
        sounds.put(name, sound);

        return sound;
    }

    public BitmapFont loadFont(String name) {
        BitmapFont font = new BitmapFont(Gdx.files.internal(name));
        fonts.put(name, font);

        return font;
    }

    public Texture getTexture(String name) {
        if (!textures.containsKey(name)) {
            throw new RuntimeException("Texture not found");
        }

        return textures.get(name);
    }

    public Music getMusic(String name) {
        if (!musics.containsKey(name)) {
            throw new RuntimeException("Music not found");
        }

        return musics.get(name);
    }

    public Sound getSound(String name) {
        if (!sounds.containsKey(name)) {
            throw new RuntimeException("Sound not found");
        }

        return sounds.get(name);
    }

    public BitmapFont getFont(String name) {
        if (!fonts.containsKey(name)) {
            throw new RuntimeException("Font not found");
        }

        return fonts.get(name);
    }

    public void dispose() {
        disposeTextures();
        disposeMusics();
        disposeSounds();
        disposeFonts();
    }

    private void disposeTextures() {
        Iterator<ObjectMap.Entry<String, Texture>> iter = textures.iterator();

        while (iter.hasNext()) {
            ObjectMap.Entry<String, Texture> next = iter.next();
            next.value.dispose();
        }
    }

    private void disposeMusics() {
        Iterator<ObjectMap.Entry<String, Music>> iter = musics.iterator();

        while (iter.hasNext()) {
            ObjectMap.Entry<String, Music> next = iter.next();
            next.value.dispose();
        }
    }

    private void disposeSounds() {
        Iterator<ObjectMap.Entry<String, Sound>> iter = sounds.iterator();

        while (iter.hasNext()) {
            ObjectMap.Entry<String, Sound> next = iter.next();
            next.value.dispose();
        }
    }

    private void disposeFonts() {
        Iterator<ObjectMap.Entry<String, BitmapFont>> iter = fonts.iterator();

        while (iter.hasNext()) {
            ObjectMap.Entry<String, BitmapFont> next = iter.next();
            next.value.dispose();
        }
    }
}
