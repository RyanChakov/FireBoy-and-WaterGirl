package me.ryrybread5.twoplayer.world;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import me.ryrybread5.twoplayer.world.GameMap;

public class TiledGameMap extends GameMap {

	private static Scanner x;
	public TiledMap tiledMap;
	OrthogonalTiledMapRenderer tiledMapRenderer;
	boolean ran=false;
	GameMap mp;
	TextureRegion textureRegion;
	MapLayer objectLayer;
	public static int Cvalue=0,count=0 ;
	public static float volume=.3f;
	public static Music [] levels= new Music[5];

	public static TextureMapObject [] character= new TextureMapObject[10];
	public TiledGameMap()
	{

		tiledMap= new TmxMapLoader().load("Level 1.tmx");
		newGame();

		levels[1] = Gdx.audio.newMusic(Gdx.files.internal("Boss Theme.mp3"));
		levels[2] = Gdx.audio.newMusic(Gdx.files.internal("Desert Theme.mp3"));
		levels[3] = Gdx.audio.newMusic(Gdx.files.internal("Iceland Theme.mp3"));
		levels[4] = Gdx.audio.newMusic(Gdx.files.internal("Iceland Theme.mp3"));


	}
	@Override
	public void render(OrthographicCamera camera, SpriteBatch batch) {
		int level= Level();
		levels[level].play();
		levels[level].setVolume(volume);

		tiledMap= new TmxMapLoader().load("Level "+level+".tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		batch.setProjectionMatrix(camera.combined); 
		batch.begin();
		super.render(camera, batch); 
		if(level==4)
		{
			count=tiledMap.getLayers().get("Coins Boy").getObjects().getCount();
			if(!ran)
			{

				for(Cvalue=0; Cvalue<count; Cvalue++)
				{
					if(character[Cvalue]==null||character[Cvalue].isVisible())
					{
						character[Cvalue] = (TextureMapObject)tiledMap.getLayers().get("Coins Boy").getObjects().get(Cvalue);
					}
					else
					{
						character[Cvalue].setVisible(false);
					}


				}
				//ran=true;
			}


			//	System.out.println(character.isVisible());


		}

		batch.end();


	}

	@Override
	public void update(float delta) {
		super.update(delta);

	}

	@Override
	public void dispose() {
		tiledMap.dispose();

	}

	@Override
	public TileType getTileTypeByCoordinate(int layer, int col, int row) {

		Cell cell=null;

		if(layer<=2)
		{

			cell = ((TiledMapTileLayer) tiledMap.getLayers().get(layer)).getCell(col,row);

			if(cell!=null)
			{
				TiledMapTile tile = cell.getTile();

				if(tile!=null)
				{
					int id=tile.getId();
					return TileType.getTileTypeByid(id);
				}
			}
		}
		return null;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
	}

	@Override
	public int getHeight() {
		return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
	}

	@Override
	public int getLayers() {
		// TODO Auto-generated method stub
		return tiledMap.getLayers().getCount();
	}
	public void newGame()
	{
		try (Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("Level.txt"), "UTF-8")))
		{
			writer.write("Level=1");
		}
		catch (Exception e) 
		{

		}
	}
	public int Level()
	{
		boolean found = false;
		String intname="",value="";
		int level=1;
		try {
			x = new Scanner(new File("Level.txt"));
			x.useDelimiter("[=\n]");

			while(x.hasNext()&& !found)
			{
				intname = x.next();
				value = x.next();
				if(intname.trim().equals("Level"))
				{

					found=true;
					level= Integer.parseInt(value);
				}
			}
			x.close();
		} 
		catch (FileNotFoundException e) 
		{
		}
		return level;
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub

	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}
	@Override
	public void create() {
		// TODO Auto-generated method stub

	}

}
