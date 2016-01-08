package local.morph.game.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import local.morph.game.ResourceManager;

/**
 * Created by morph on 07.01.2016.
 */
public class Bird implements Sprite {
    public static final int MOVEMENT = 100;
    private static final int GRAVITY = -15;

    private Vector3 position;
    private Vector3 velocity;
    private Animation animation;
    private Sound sound;
    private Rectangle rect;

    public Bird(ResourceManager resourceManager) {
        Texture texture = resourceManager.getTexture("bird.png");
        sound = resourceManager.getSound("sfx_wing.ogg");

        animation = new Animation(new TextureRegion(texture), 3, 0.5f);

        position = new Vector3();
        velocity = new Vector3(0, 0, 0);
        rect = new Rectangle(0, 0, texture.getWidth() / 3, texture.getHeight());
    }

    public void moveTo(float x, float y) {
        position.set(x, y, 0);
        rect.setPosition(x, y);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(animation.getFrame(), position.x, position.y);
    }

    public void update(OrthographicCamera camera, float delta) {
        animation.update(delta);

        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }

        velocity.scl(delta);
        position.add(MOVEMENT * delta, velocity.y, 0);

        if (position.y < 0) {
            position.y = 0;
        }

        velocity.scl(1 / delta);
        rect.setPosition(position.x, position.y);
    }

    public void jump() {
        velocity.y = 250;
        sound.play();
    }

    public Vector3 getPosition() {
        return position;
    }

    public Rectangle getRect() {
        return rect;
    }
}
