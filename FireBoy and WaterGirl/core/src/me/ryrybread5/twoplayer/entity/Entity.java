package me.ryrybread5.twoplayer.entity;


import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import me.ryrybread5.twoplayer.Adventure;
import me.ryrybread5.twoplayer.Screenrun;
import me.ryrybread5.twoplayer.Inventory.Window;
import me.ryrybread5.twoplayer.world.GameMap;
import me.ryrybread5.twoplayer.world.TileType;
import me.ryrybread5.twoplayer.world.TiledGameMap;

public abstract class Entity extends Game{

	protected Vector2 pos;

	//Keeps track of x and y and lots of math stuffs
	public EntityType type;
	protected TileType tile;
	protected Adventure t;

	protected Window sc= new Window();
	Sound tele,ches,doo;
	protected float velocityY=0; // Y velocity
	protected GameMap map;
	protected static Player y;
	protected TiledGameMap m; //for collisons
	protected boolean grounded=false;
	protected int level=1,pis=0;

	public Entity(float x, float y, EntityType type, GameMap map) {
		this.pos = new Vector2(x,y);
		this.type = type;
		this.map = map;

	}
	public void update(float deltaTime, float gravity)
	{
		tele = Gdx.audio.newSound(Gdx.files.internal("teleport.mp3"));
		ches = Gdx.audio.newSound(Gdx.files.internal("chest.wav"));
		doo = Gdx.audio.newSound(Gdx.files.internal("door.wav"));
		float newY= pos.y;

		this.velocityY+=gravity*deltaTime * getWeight();
		newY+= this.velocityY * deltaTime;


		if(map.doesrectcollideWithMap(pos.x, newY, getWidth(), getHeight()))
		{
			if(velocityY<0) //when hits ground
			{


				this.pos.y=(float) Math.floor(pos.y);//if they his ground while falling sets any number down not up like 5.9 to 5
				grounded=true;


			}
			if(map.interact && (Gdx.input.isKeyJustPressed(Keys.E) ||Gdx.input.isKeyJustPressed(Keys.ENTER)))
			{
				if(map.t==tile.CHEST || map.t==tile.CHESTBLUE || map.t==tile.CHESTPURPLE)
				{

					ches.play();

					if(pis==0)
					{

						pis++;
						sc.start();
					}

				}
				map.t.setInteractable(false);

				if(map.t==tile.DOORBLUE ||map.t==tile.DOORRED )
				{
					map.t.setCollidable(false);
					doo.play();

				}


			}
			if(map.next&& map.interact&& (Gdx.input.isKeyPressed(Keys.R) || Gdx.input.isKeyPressed(Keys.P))||(Gdx.input.isKeyPressed(Keys.R) || Gdx.input.isKeyPressed(Keys.P)))
			{

				y.totalTime=0;
				if(level==1)
					level=2;
				else if(level==2)
					level=3;
				else if(level==3)
					level=4;
				nextLevel();


				if(type==type.PLAYER)
				{
					this.pos.x=40;

					System.out.println("YUP");
				}
				else
				{
					this.pos.x=1540;
				System.out.println("DONE");
				}


				tele.play();
				pos.y=400;
				map.next=false;
			}
			this.velocityY=0;
		}
		else
		{

			this.pos.y=newY;
			grounded=false;
		}



	}
	public abstract void render(SpriteBatch batch);


	protected void moveX(float amount)
	{
		float newX=pos.x +amount;
		if(!map.doesrectcollideWithMap(newX, pos.y, getWidth(), getHeight())) //if doesnt collide
			this.pos.x=newX;
	}
	public Vector2 getPos() {
		return pos;
	}

	public float getX()
	{
		return pos.x;
	}
	public float getY()
	{
		return pos.y;
	}

	public EntityType getType() {
		return type;
	}
	public boolean isGrounded() {
		return grounded;
	}
	public int getWidth()
	{
		return type.getWidth();
	}
	public int getHeight()
	{
		return type.getHeight();
	}
	public float getWeight()
	{
		return type.getWeight();
	}

	public void nextLevel()
	{
		try (Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("Level.txt"), "UTF-8")))
		{
			writer.write("Level="+level);

		}
		catch (Exception e) 
		{

		}
		TiledGameMap.levels[level-1].dispose();
		pos.y=400;
	}
	public void retry()
	{
		try (Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("Level.txt"), "UTF-8")))
		{
			writer.write("Level=1");
		}
		catch (Exception e) 
		{

		}
		if(type==type.ENEMY)
		{
			pos.x=1540;
		}
		if(type==type.PLAYER)
		{
			pos.x=40;
		}
		pos.y=400;
	}


}
