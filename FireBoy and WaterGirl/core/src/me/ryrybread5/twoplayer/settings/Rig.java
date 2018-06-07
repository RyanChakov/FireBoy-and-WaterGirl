package me.ryrybread5.twoplayer.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import me.ryrybread5.twoplayer.Adventure;
import me.ryrybread5.twoplayer.Screenrun;
import me.ryrybread5.twoplayer.world.GameMap;
import me.ryrybread5.twoplayer.world.TiledGameMap;

public class Rig implements Screen {
	final Screenrun game;

		
	OrthographicCamera camera;
	Texture p=new Texture("Perfect.png");
	TextureRegion[][] tmp = new TextureRegion(p).split(p.getWidth() / 1,   p.getHeight() / 3);
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


		game.batch.draw(new Texture("background.png"),0,0);

		game.batch.draw(tmp[0][0],300,210);
		game.batch.draw(tmp[1][0],300,210-tmp[0][0].getRegionHeight());
		game.batch.draw(tmp[2][0],300,210-tmp[0][0].getRegionHeight()-tmp[0][0].getRegionHeight());


		game.batch.draw(new Texture("Earth Boy.png"),0,400); 

		game.batch.draw(new Texture("Air Girl.png"),465,400);
		game.batch.draw(new Texture("and.png"),420,410);


		game.batch.end();

		if (Gdx.input.isTouched()) {
			System.out.println(Gdx.input.getX());
			System.out.println(Gdx.input.getY());

			if((Gdx.input.getX()>720 && Gdx.input.getX()<=1175) &&(Gdx.input.getY()>388 && Gdx.input.getY()<=388+200) )
			{
				//play
				game.setScreen(new Adventure());
				System.out.println("COOL");
			}
			else if(Gdx.input.getX()>720 && Gdx.input.getX()<=1415 &&(Gdx.input.getY()>611 && Gdx.input.getY()<=611+200))
			{
				//options
				game.setScreen(new MainMenuSample(game));
			}
			else if(Gdx.input.getX()>720 && Gdx.input.getX()<=1367 &&(Gdx.input.getY()>834 && Gdx.input.getY()<=834+200))
			{
				//Levels
			}
			
			
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
