package local.morph.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import local.morph.game.FlappyDemo;
import local.morph.game.ResourceManager;

/**
 * Created by morph on 07.01.2016.
 */
public class MenuState extends State {
    public MenuState(StateManager stateManager, ResourceManager resourceManager) {
        super(stateManager, resourceManager);

        camera.setToOrtho(false, FlappyDemo.WIDTH / 2, FlappyDemo.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            stateManager.set(new PlayState(stateManager, resourceManager));
        }
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);

        final Texture background = resourceManager.getTexture("background.png");
        final Texture playButton = resourceManager.getTexture("play.png");
        final BitmapFont font = resourceManager.getFont("font.fnt");

        font.getData().setScale(1.15f, 1.15f);

        final GlyphLayout layout = new GlyphLayout(font, "Flappy Demo");
        final float fontX = camera.viewportWidth / 2 - (layout.width / 2);

        batch.begin();

        batch.draw(background, 0, 0);
        batch.draw(
                playButton,
                camera.position.x - (playButton.getWidth() / 2),
                camera.position.y
        );

        font.draw(batch, layout, fontX, 330);

        batch.end();
    }

    @Override
    public void dispose() {

    }
}
