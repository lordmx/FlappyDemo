package local.morph.game.sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import local.morph.game.ResourceManager;

/**
 * Created by morph on 08.01.2016.
 */
public class Score implements Sprite {
    private int score;
    private BitmapFont font;
    private Vector2 position;

    public Score(ResourceManager resourceManager) {
        font = resourceManager.getFont("font.fnt");
        font.getData().setScale(0.8f, 0.8f);
        position = new Vector2(20, 390);
    }

    @Override
    public void draw(SpriteBatch batch) {
        font.draw(batch, Integer.toString(score), position.x, position.y);
    }

    @Override
    public void update(OrthographicCamera camera, float delta) {
        position.add(Bird.MOVEMENT * delta, 0);
    }

    public void score() {
        ++score;
    }

    public int getValue() {
        return score;
    }
}
