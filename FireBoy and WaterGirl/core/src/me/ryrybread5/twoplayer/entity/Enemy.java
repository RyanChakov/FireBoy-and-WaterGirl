package me.ryrybread5.twoplayer.entity;

import javax.swing.JOptionPane;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import me.ryrybread5.twoplayer.Adventure;
import me.ryrybread5.twoplayer.world.GameMap;


public class Enemy extends Entity {

	
	private static final int SPEED =80;
	private static final int JUMP_VELOCITY =5;
	private boolean left,right;
	private int x=0,temp;
	Texture [] imageR= new Texture[5];
	Texture [] imageL= new Texture[5];
	Texture [] imageS= new Texture[5];
	Adventure g = new Adventure();
	BitmapFont font = new BitmapFont();
	public boolean rooms=false;
	int ran=0;
	Player p;
	Sound jump;
	public Enemy(float x, float y, GameMap map) {
		super(x, y, EntityType.ENEMY, map);

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
		
		if((Gdx.input.isKeyPressed(Keys.UP))&& grounded)
		{
			this.velocityY += JUMP_VELOCITY * getWeight();
			jump.play();
		}
			
		else if((Gdx.input.isKeyPressed(Keys.UP) ) && !grounded && this.velocityY>0)// jump higher longer hold
		{
			this.velocityY += JUMP_VELOCITY * getWeight() * deltaTime;
		}
		super.update(deltaTime, gravity);//Applies gravity
		left=false;
		right=false;

		if(Gdx.input.isKeyPressed(Keys.LEFT))
		{
			left=true;

			moveX(-SPEED * deltaTime);
		}


		if(Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			right=true;

			moveX(SPEED * deltaTime);
		}
		
		
			
		

	}
	@Override
	public void render(SpriteBatch batch) 
	{
		map.doesCollideWithCoinE(pos.x, pos.y, m.count, batch);
		font.draw(batch,String.valueOf(m.scoreE) , 1550, 1050);
		x++;
		
		if(!map.dead)
		{
			
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
			JOptionPane.showMessageDialog(null, "You Died :(");
			
			System.exit(0);
		}
		else if(map.dead)
		{
			temp=x;
		}
	}


	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}
	


}
