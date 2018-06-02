package me.ryrybread5.twoplayer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import me.ryrybread5.twoplayer.settings.MainMenuSample;
import me.ryrybread5.twoplayer.settings.Rig;
import me.ryrybread5.twoplayer.world.GameMap;

public class Screenrun extends Game {


	public OrthographicCamera camera;

	GameMap gameMap;

	float deltaX, deltaY;
	public 	SpriteBatch batch;
	public BitmapFont font;

	public void create() {
		batch = new SpriteBatch();
		// Use LibGDX's default Arial font.

		this.setScreen(new Rig(this));
	}

	public void render() {
		super.render(); // important!
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE))
		{
			System.out.println("HOPE NOT BROKEN");
			//dispose();
			this.setScreen(new MainMenuSample(this));
		}
	}

	public void dispose() {
		batch.dispose();

	}

}
