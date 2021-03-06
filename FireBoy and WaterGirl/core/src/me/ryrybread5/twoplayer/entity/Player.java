package me.ryrybread5.twoplayer.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

import com.aidanmurphey.scoremanager.ApiResponse;
import com.aidanmurphey.scoremanager.Record;
import com.aidanmurphey.scoremanager.ScoreManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.ryrybread5.twoplayer.Inventory.ItemType;
import me.ryrybread5.twoplayer.settings.LeaderBoard;
import me.ryrybread5.twoplayer.world.GameMap;
import me.ryrybread5.twoplayer.world.TiledGameMap;


public class Player extends Entity {
	private static Scanner a;
	Enemy posit; 
LeaderBoard l= new LeaderBoard();
	protected TiledGameMap m;
	public static float totalTime = 0; 
	private static final int SPEED =80;
	private static final int JUMP_VELOCITY =5;
	private boolean left,right;
	private int x=0,temp;
	BitmapFont font = new BitmapFont();
	Texture [] imageR= new Texture[5];
	Texture [] imageL= new Texture[5];
	Texture [] imageS= new Texture[5];
	ItemType item;
	
	public boolean room= false;
	Sound jump;

	public Player(float x, float y, GameMap map) {
		super(x, y, EntityType.PLAYER, map);

		
		

		imageL[0]= new Texture("StillLeft.png");
		imageL[1]= new Texture("Left 1.png");
		imageL[2]= new Texture("Left 2.png");
		imageL[3]= new Texture("Left 3.png");
		imageL[4]= new Texture("Left 4.png");
		imageR[0]= new Texture("StillRight.png");
		imageR[1]= new Texture("Right1.png");
		imageR[2]= new Texture("Right2.png");
		imageR[3]= new Texture("Right3.png");
		imageR[4]= new Texture("Right4.png");
		imageS[0]= new Texture("player.png");
		imageS[1]= new Texture("player.png");
		imageS[2]= new Texture("player.png");
		imageS[3]= new Texture("player.png");
		imageS[4]= new Texture("player.png");

		jump = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
		//	image = new Texture("player.png");
		// TODO Auto-generated constructor stub
	}


	@Override
	public void update(float deltaTime, float gravity) 
	{


		if(Gdx.input.isKeyPressed(Keys.W)&& grounded)
		{
			this.velocityY += JUMP_VELOCITY * getWeight();
			jump.play();
		}
		else if(Gdx.input.isKeyPressed(Keys.W) && !grounded && this.velocityY>0)// jump higher longer hold
		{
			this.velocityY += JUMP_VELOCITY * getWeight() * deltaTime;
		}
		super.update(deltaTime, gravity);//Applies gravity
		left=false;
		right=false;

		if(Gdx.input.isKeyPressed(Keys.A))
		{
			left=true;

			moveX(-SPEED * deltaTime);
		}


		if(Gdx.input.isKeyPressed(Keys.D))
		{
			right=true;

			moveX(SPEED * deltaTime);
		}
		if(Gdx.input.isKeyJustPressed(Keys.Q))
		{
			inventory();
		}


	}
	public String time() {
		float eltaTime = Gdx.graphics.getDeltaTime(); //You might prefer getRawDeltaTime()

		totalTime += eltaTime; //if counting down

		int minutes = ((int)totalTime) / 60;
		int seconds = ((int)totalTime) % 60;

		return minutes+":"+seconds;
	}
	@Override
	public void render(SpriteBatch batch) {

		map.doesCollideWithCoinP(pos.x, pos.y, m.count, batch);
		x++;
		if(!map.dead)
		{
			String timer=time();

			font.draw(batch,String.valueOf(m.scoreP) , 10, 1050);

			font.draw(batch,timer , 100, 100);
			time();
			if(right==true)
			{
				batch.draw(imageR[x], pos.x, pos.y, getWidth(), getHeight());

			}
			else if(left==true)
			{
				batch.draw(imageL[x], pos.x, pos.y, getWidth(), getHeight());
			}
			else 
			{
				batch.draw(imageS[x], pos.x, pos.y, getWidth(), getHeight());
			}

		}
		if(x==4)
		{
			x=0;
		}

		else if(map.dead && temp==x-1)
		{
			l.sendit(m.scoreP);

		}
		else if(map.dead)
		{
			temp=x;
		}
	}
	public void inventory()
	{

		String tItem = "";

		ItemType[] test= {item.BALLOONSW,item.DOUBLEJUMP,item.HEAVYBOOT,item.IRONSW,item.LIGHTERPANT,item.LIGHTPANT,item.ONEHITSW,item.STONESW};
		try
		{

			a = new Scanner(new File("Inventory.txt"));
			a.useDelimiter("[,]");

			while(a.hasNext())
			{
				tItem = a.next();
				for(int p=0; p<test.length; p++)
				{

					if((tItem.trim()).equals(test[p].getId()))
					{

						float nweight=type.getWeight()+test[p].getWeight();
						if(nweight<5)
							nweight=7;
						type.setWeight(nweight);

						break;
					}


				}



			}

			a.close();
		}
		catch (Exception e)
		{
			System.out.println("Error");
		}
	}


	@Override
	public void create() {
		// TODO Auto-generated method stub

	}


}
