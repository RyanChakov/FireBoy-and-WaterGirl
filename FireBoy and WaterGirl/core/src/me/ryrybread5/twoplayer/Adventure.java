package me.ryrybread5.twoplayer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.ryrybread5.twoplayer.world.GameMap;
import me.ryrybread5.twoplayer.world.TiledGameMap;

public class Adventure extends Game implements Screen 
{

	public static SpriteBatch batch;

	public OrthographicCamera camera;

	GameMap gameMap;
	public static boolean Times=false;
	float deltaX, deltaY;
	static int x=0;
	@Override
	public void create () {

		batch = new SpriteBatch();



		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false,w-320,h+20);
		camera.update();

		gameMap = new TiledGameMap();

	}



	@Override
	public void dispose () {
	
		batch.dispose();
		gameMap.dispose();
	}

	@Override
	public void show() {


	}

	@Override
	public void render(float delta) {

		if(x==0||Times)
		{
			
			create();
		}
		Times=false;
		x++;
		
		camera.update();
		gameMap.update(Gdx.graphics.getDeltaTime());
		gameMap.render(camera, batch);

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}
}