package me.ryrybread5.twoplayer.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import me.ryrybread5.twoplayer.Adventure;
import me.ryrybread5.twoplayer.Screenrun;
import me.ryrybread5.twoplayer.world.GameMap;
import me.ryrybread5.twoplayer.world.TiledGameMap;

public class Rig implements Screen {
	final Screenrun game;
	Adventure s;
	OrthographicCamera camera;
	BitmapFont	font = new BitmapFont();
	public Rig(final Screenrun screenrun) {
		game = screenrun;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

	}

	@Override
	public void render(float delta) {

	
		Gdx.gl.glClearColor(0, .1f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();

	
		
		font.draw(game.batch, "______________________________", 300,325);
		font.draw(game.batch, "           Welcome to My Game!!! ", 300,300);
		font.draw(game.batch, "          ---------------------------------", 300,275);
		font.draw(game.batch, "______________________", 300,225);
		font.draw(game.batch, "Tap anywhere to begin!", 300,200);
		font.draw(game.batch, "----------------------", 300,175);
	
		game.batch.end();

		if (Gdx.input.isTouched()) {
		


			game.setScreen(new Adventure());
			dispose();
		}

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		System.out.println("GOE");
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}
