package local.morph.game.sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

import local.morph.game.sprites.Sprite;

/**
 * Created by morph on 08.01.2016.
 */
public class SceneManager {
    private Array<Sprite> sprites;

    public SceneManager() {
        sprites = new Array<Sprite>();
    }

    public void add(Sprite sprite) {
        sprites.add(sprite);
    }

    public void update(OrthographicCamera camera, float delta) {
        Iterator<Sprite> iterator = sprites.iterator();

        while (iterator.hasNext()) {
            iterator.next().update(camera, delta);
        }
    }

    public void draw(SpriteBatch batch) {
        Iterator<Sprite> iterator = sprites.iterator();

        while (iterator.hasNext()) {
            iterator.next().draw(batch);
        }
    }
}
