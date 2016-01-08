package local.morph.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import org.w3c.dom.css.Rect;

import java.util.Random;

import local.morph.game.ResourceManager;
import local.morph.game.states.PlayState;

/**
 * Created by morph on 08.01.2016.
 */
public class Tube implements Sprite {
    public static final int TUBE_WIDTH = 52;
    public static final int FLUCTUATION = 130;
    public static final int TUBE_GAP = 100;
    public static final int LOWEST_OPENING = 120;

    private Random rand;
    private Texture bottom, top;
    private Vector2 positionTop, positionBottom;
    private Rectangle rectTop, rectMiddle, rectBottom;
    private boolean isScored = false;

    public Tube(ResourceManager resourceManager) {
        bottom = resourceManager.getTexture("bottom.png");
        top = resourceManager.getTexture("top.png");

        positionTop = new Vector2();
        positionBottom = new Vector2();

        rand = new Random();

        positionTop.y = rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING;
        positionBottom.y = positionTop.y - TUBE_GAP - bottom.getHeight();

        rectTop = new Rectangle(0, positionTop.y, top.getWidth(), top.getHeight());
        rectBottom = new Rectangle(0, positionBottom.y, bottom.getWidth(), bottom.getHeight());
        rectMiddle = new Rectangle(
                0, positionTop.y - bottom.getHeight() + 2 * TUBE_GAP - 2, top.getWidth(), TUBE_GAP + 1
        );
    }

    public void moveX(float x) {
        positionTop.x = x;
        positionBottom.x = x;

        rectTop.x = positionTop.x;
        rectMiddle.x = positionTop.x;
        rectBottom.x = positionBottom.x;
    }

    public void respawn(float x) {
        moveX(x);

        positionTop.y = rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING;
        positionBottom.y = positionTop.y - TUBE_GAP - bottom.getHeight();

        rectTop.y = positionTop.y;
        rectMiddle.y = positionTop.y - bottom.getHeight() + 2 * TUBE_GAP - 2;
        rectBottom.y = positionBottom.y;

        isScored = false;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(bottom, positionBottom.x, positionBottom.y);
        batch.draw(top, positionTop.x, positionTop.y);
    }

    @Override
    public void update(OrthographicCamera camera, float delta) {
        if (camera.position.x - (camera.viewportWidth / 2) > positionTop.x + top.getWidth()) {
            respawn(positionTop.x + ((TUBE_WIDTH + PlayState.TUBE_SPACING) * PlayState.TUBE_COUNT));
        }
    }

    public boolean isCollides(Rectangle player) {
        return player.overlaps(rectTop) || player.overlaps(rectBottom);
    }

    public boolean isScores(Rectangle player) {
        Gdx.app.debug("bird", player.x + ", " + player.y);
        Gdx.app.debug("tube", rectMiddle.x + ", " + rectMiddle.y);

        return !isScored && rectMiddle.overlaps(player);
    }

    public void setScored(boolean isScored) {
        this.isScored = isScored;
    }

    public Rectangle getRect() {
        return rectMiddle;
    }
}
