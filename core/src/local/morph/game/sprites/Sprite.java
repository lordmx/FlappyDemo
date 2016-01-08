package local.morph.game.sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by morph on 08.01.2016.
 */
public interface Sprite {
    void draw(SpriteBatch batch);
    void update(OrthographicCamera camera, float delta);
}
