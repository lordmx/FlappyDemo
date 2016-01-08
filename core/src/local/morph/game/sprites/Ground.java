package local.morph.game.sprites;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import local.morph.game.FlappyDemo;
import local.morph.game.ResourceManager;

/**
 * Created by morph on 08.01.2016.
 */
public class Ground implements Sprite {
    public static final int GROUND_Y_OFFSET = -30;

    private Rectangle rect;
    private Texture texture;
    private Vector2 pos1, pos2;
    private boolean isInitialized = false;

    public Ground(ResourceManager resourceManager) {
        texture = resourceManager.getTexture("ground.png");

        pos1 = new Vector2(0, GROUND_Y_OFFSET);
        pos2 = new Vector2(0, GROUND_Y_OFFSET);

        rect = new Rectangle();

        rect.y = Tube.LOWEST_OPENING - Tube.TUBE_GAP - GROUND_Y_OFFSET;
        rect.width = FlappyDemo.WIDTH;
        rect.height = Math.abs(GROUND_Y_OFFSET);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(texture, pos1.x, pos1.y);
        batch.draw(texture, pos2.x, pos2.y);
    }

    @Override
    public void update(OrthographicCamera camera, float delta) {
        float cameraOffset = camera.position.x - (camera.viewportWidth / 2);

        if (!isInitialized) {
            pos1.x = cameraOffset;
            pos2.x = pos1.x + texture.getWidth();

            isInitialized = true;
        }

        if (cameraOffset > pos1.x + texture.getWidth()) {
            pos1.add(texture.getWidth() * 2, 0);
        }

        if (cameraOffset > pos2.x + texture.getWidth()) {
            pos2.add(texture.getWidth() * 2, 0);
        }

        rect.x = Math.min(pos1.x, pos2.x);
    }

    public boolean isCollided(Rectangle player) {
        return rect.overlaps(player);
    }
}
