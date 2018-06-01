/**
 * 
 */
package me.ryrybread5.twoplayer.world;

import java.util.ArrayList;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.ryrybread5.twoplayer.entity.Enemy;
import me.ryrybread5.twoplayer.entity.Entity;
import me.ryrybread5.twoplayer.entity.EntityType;
import me.ryrybread5.twoplayer.entity.Player;

/**
 * @author Ryans
 *
 */
public abstract class GameMap extends Game implements Screen{
	public TileType t;

	protected ArrayList<Entity>entities;
	public boolean interact,dead,next;
	Enemy E;
	public static int scoreE=0;
	public static int scoreP=0;
	Player p;
	Sound die;
	int x=0;
	protected static TiledGameMap ty;
	public GameMap()
	{
		die = Gdx.audio.newSound(Gdx.files.internal("death.wav"));
		entities= new ArrayList<Entity>();
		entities.add(new Player(40,550,this));
		entities.add(new Enemy(1540,550,this));
	}

	public void render(OrthographicCamera camera, SpriteBatch batch)
	{
		for(Entity entity: entities)
		{
			entity.render(batch);
		}

	}
	public void update(float delta)

	{
		for(Entity entity: entities)
		{
			entity.update(delta, -9.8f);
		}
	}
	public abstract void dispose();

	/**
	 *  Gets a tile by pixel position within the game world at a specified layer
	 * @param layer
	 * @param x
	 * @param y
	 * @return
	 */


	public TileType getTileTypeByLocation(int layer, float x, float y) {
		return this.getTileTypeByCoordinate(layer, (int) (x/TileType.TILE_SIZE), (int) (y/TileType.TILE_SIZE));
	}
	/**
	 * Gets a tile at its coordinate within the map at a specified layer
	 * @param layer
	 * @param col
	 * @param rol
	 * @return
	 */
	public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);
	public boolean doesrectcollideWithMap(float x, float y, int width, int height)
	{
	
		if(x<0 || y<0|| x+width > getPixelWidth()|| y+ height > getPixelHeight())
		{
			return true;
		}
		for(int row=(int) (y/TileType.TILE_SIZE); row<Math.ceil((y+height)/ TileType.TILE_SIZE); row++) //math.ceil always roudn up liek 5.2 to 6 to find the box it collides with
		{
			for(int col=(int) (x/TileType.TILE_SIZE); col<Math.ceil((x+width)/ TileType.TILE_SIZE); col++) 
			{
				
				for(int layer=0; layer<getLayers(); layer++)
				{
					TileType type = getTileTypeByCoordinate(layer,col,row);
					if(type!=null && type.isCollidable())
					{
						if(type.isinteractable())
						{
							t=type;
							interact=true;	
						}

						return true;
					}
					else
					{
						if(type==type.LAVA || type==type.LAVAA)
						{
							dead=true;

							die.play();
						}
						else if(type==type.MULTIDOOR)
						{
							next=true;

						}
					}
				}
			}
		}
		return false;
	}




	public abstract int getWidth();
	public abstract int getHeight();
	public abstract int getLayers();

	public int getPixelWidth() {
		return this.getWidth() * TileType.TILE_SIZE;
	}
	public void doesCollideWithCoinP(float x, float y,int count,SpriteBatch batch) {

		batch.end();
		batch.begin();
		for(int c=0; c<count; c++)
		{
			//SET VISABLITY HERE
			
			if((TiledGameMap.character[c].isVisible())&&(x>TiledGameMap.character[c].getX() && x<TiledGameMap.character[c].getX()+16) && (y==TiledGameMap.character[c].getY() ))
			{
				
				TiledGameMap.character[c].setVisible(false);
				scoreP++;
			}
			else 
			{
				if((TiledGameMap.character[c].isVisible()))
					batch.draw(ty.character[c].getTextureRegion(), ty.character[c].getX(), ty.character[c].getY());
				//batch.draw(TiledGameMap.character[c].getTextureRegion(), 0, 0);
				//	batch.draw(ty.character[c].getTextureRegion(), ty.character[c].getX(), ty.character[c].getY());

			}
		}

	}
	public void doesCollideWithCoinE(float x, float y,int count,SpriteBatch batch) {

		batch.end();
		batch.begin();
		for(int c=0; c<count; c++)
		{
			//SET VISABLITY HERE
			
			if((TiledGameMap.character[c].isVisible())&&(x>TiledGameMap.character[c].getX() && x<TiledGameMap.character[c].getX()+16) && (y==TiledGameMap.character[c].getY() ))
			{
				System.out.println("HER I PRESUME");
				TiledGameMap.character[c].setVisible(false);
				scoreE++;
			}
			else 
			{
				if((TiledGameMap.character[c].isVisible()))
					batch.draw(ty.character[c].getTextureRegion(), ty.character[c].getX(), ty.character[c].getY());
				//batch.draw(TiledGameMap.character[c].getTextureRegion(), 0, 0);
				//	batch.draw(ty.character[c].getTextureRegion(), ty.character[c].getX(), ty.character[c].getY());

			}
		}

	}

	public int getPixelHeight() {
		return this.getHeight() * TileType.TILE_SIZE;
	}

}
