package local.morph.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import local.morph.game.states.MenuState;
import local.morph.game.states.StateManager;

public class FlappyDemo extends Game {
	public static final String TITLE = "Flappy Demo";
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	private SpriteBatch batch;
	private StateManager stateManager;
	private ResourceManager resourceManager;

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		batch = new SpriteBatch();
		stateManager = new StateManager();
		resourceManager = new ResourceManager();

		resourceManager.loadTexture("background.png");
		resourceManager.loadTexture("play.png");
		resourceManager.loadTexture("bird.png");
		resourceManager.loadTexture("top.png");
		resourceManager.loadTexture("bottom.png");
		resourceManager.loadTexture("ground.png");
		resourceManager.loadTexture("gameover.png");

		resourceManager.loadFont("font.fnt");

		resourceManager.loadSound("sfx_wing.ogg");

		Music music = resourceManager.loadMusic("music.mp3");
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();

		Gdx.gl.glClearColor(1, 0, 0, 1);

		stateManager.push(new MenuState(stateManager, resourceManager));
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stateManager.update(Gdx.graphics.getDeltaTime());
		stateManager.render(batch);
	}

	@Override
	public void dispose() {
		super.dispose();

		resourceManager.dispose();
	}
}
