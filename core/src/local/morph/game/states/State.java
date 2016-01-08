package local.morph.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;

import local.morph.game.ResourceManager;

/**
 * Created by morph on 07.01.2016.
 */
public abstract class State {
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected StateManager stateManager;
    protected ResourceManager resourceManager;

    public State(StateManager stateManager, ResourceManager resourceManager) {
        this.stateManager = stateManager;
        this.resourceManager = resourceManager;
        this.camera = new OrthographicCamera();
        this.mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float delta);
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
}
