package local.morph.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import local.morph.game.FlappyDemo;
import local.morph.game.ResourceManager;
import local.morph.game.sprites.Ground;
import local.morph.game.sprites.SceneManager;
import local.morph.game.sprites.Bird;
import local.morph.game.sprites.Score;
import local.morph.game.sprites.Tube;

/**
 * Created by morph on 07.01.2016.
 */
public class PlayState extends State {
    public static final int TUBE_SPACING = 125;
    public static final int TUBE_COUNT = 4;

    private Ground ground;
    private Score score;
    private Bird bird;
    private Array<Tube> tubes;
    private SceneManager sceneManager;

    public PlayState(StateManager stateManager, ResourceManager resourceManager) {
        super(stateManager, resourceManager);

        sceneManager = new SceneManager();

        bird = new Bird(resourceManager);
        bird.moveTo(50, 300);

        sceneManager.add(bird);

        tubes = new Array<Tube>();

        for (int i = 0; i < TUBE_COUNT; i++) {
            Tube tube = new Tube(resourceManager);

            tubes.add(tube);
            sceneManager.add(tube);

            tube.moveX((TUBE_SPACING + Tube.TUBE_WIDTH) * (i + 1));
        }

        ground = new Ground(resourceManager);
        sceneManager.add(ground);

        score = new Score(resourceManager);
        sceneManager.add(score);

        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float delta) {
        handleInput();

        sceneManager.update(camera, delta);

        for (Tube tube: tubes) {
            if (tube.isCollides(bird.getRect())) {
                stateManager.set(new GameOverState(stateManager, resourceManager, score));
            }

            if (tube.isScores(bird.getRect())) {
                score.score();
                tube.setScored(true);
            }
        }

        if (ground.isCollided(bird.getRect())) {
            stateManager.set(new GameOverState(stateManager, resourceManager, score));
        }

        camera.position.x = bird.getPosition().x + 80;
        camera.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        final Texture background = resourceManager.getTexture("background.png");

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        sceneManager.draw(batch);

        batch.end();
    }

    @Override
    public void dispose() {

    }
}
