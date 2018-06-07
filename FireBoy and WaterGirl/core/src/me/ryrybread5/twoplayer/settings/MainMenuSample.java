
package me.ryrybread5.twoplayer.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import me.ryrybread5.twoplayer.Adventure;
import me.ryrybread5.twoplayer.Screenrun;
import me.ryrybread5.twoplayer.world.TiledGameMap;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import javax.swing.JRadioButton;

import com.badlogic.gdx.Game;

public class MainMenuSample extends Game implements Screen {
	private static final String TAG = "BasicMenuSample";
	private static final int VIRTUAL_WIDTH = 1280;
	private static final int VIRTUAL_HEIGHT = 720;

	private Viewport viewport;
	private TextButton textButton;
	private Slider slider;
	private ButtonGroup bg;
	private BitmapFont font;
	private Label label;
	private Table table;
	private Stage stage;
	private ImageTextButton imageTextButton;
	private CheckBox checkbox, checkbox2, checkbox3;
	private Texture sliderBackgroundTex, sliderKnobTex,actor, checkBoxOn, checkBoxOff; 
	
	final Screenrun game;
	Adventure s = new Adventure();
	TiledGameMap ts= new TiledGameMap();
	OrthographicCamera camera;
	
	Skin skin=new Skin();
	public MainMenuSample(final Screenrun screenrun) {
		game = screenrun;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

	}
	@Override
	public void create () {
		viewport = new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);
		font = new BitmapFont();
		int middlepointX = VIRTUAL_WIDTH>>1;
		int middlepointY = VIRTUAL_HEIGHT>>1;


		//Label Music Volume
	
		Label.LabelStyle ls = new Label.LabelStyle(font, Color.WHITE);
		label = new Label("Music Volume", ls);
		label.setPosition(middlepointX - (label.getWidth()*0.5f)-225,middlepointY - (label.getHeight()*0.5f)+15);
		//Slider Music Volume
		sliderBackgroundTex =new Texture("slider_background.png");
		sliderKnobTex = new Texture("slider_knob.png");
		Slider.SliderStyle ss = new Slider.SliderStyle();
		ss.background = new TextureRegionDrawable(new TextureRegion(sliderBackgroundTex));
		ss.knob = new TextureRegionDrawable(new TextureRegion(sliderKnobTex));
		slider = new Slider(0f, 5f, .1f, false, ss);

		//Text Button
		float firstRowY = 10;
		actor = new Texture(Gdx.files.internal("and.png"));
		TextButton.TextButtonStyle tbs = new TextButton.TextButtonStyle();
		tbs.font = font;
		tbs.up = new TextureRegionDrawable(new TextureRegion(actor));
		textButton = new TextButton("TextButton", tbs);
		textButton.setPosition(50,50);
		textButton.addListener( new ClickListener() {             
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(TAG, "TextButton clicked");
			};
		});
		//Check Box

		checkBoxOn = new Texture("checkBoxOn.png");
		checkBoxOff = new Texture("checkBoxOff.png");
		CheckBox.CheckBoxStyle cbs = new CheckBox.CheckBoxStyle();
		cbs.checkboxOn = new TextureRegionDrawable(new TextureRegion(checkBoxOn));
		cbs.checkboxOff = new TextureRegionDrawable(new TextureRegion(checkBoxOff));
	
		cbs.font = font;
		cbs.font.getData().setScale(2, 2);
		cbs.fontColor = Color.WHITE;
		checkbox = new CheckBox("FullScreen", cbs);
		
		checkbox2 = new CheckBox("Borderless FullScreen", cbs);
		checkbox2.setPosition(checkbox.getX(), checkbox.getY()+checkbox.getHeight());
		
		checkbox3 = new CheckBox("Windowed", cbs);
		checkbox3.setPosition(checkbox2.getX(), checkbox2.getY()+checkbox2.getHeight());
		bg = new ButtonGroup();
		bg.add(checkbox, checkbox2, checkbox3);
		bg.setUncheckLast(true);
		bg.setMaxCheckCount(1);
		
		bg.setMinCheckCount(0);
		float checkBoxX = middlepointX-60;
		checkbox.setPosition(checkBoxX, middlepointY-50);
		checkbox2.setPosition(checkBoxX, checkbox.getY()-checkbox2.getHeight());
		checkbox3.setPosition(checkBoxX, checkbox2.getY()-checkbox3.getHeight()); 
		//Checkbox - interaction
		checkbox3.addListener(new ChangeListener() {
		    @Override
		    public void changed (ChangeEvent event, Actor actor) {
		     
		    	   if(bg.getCheckedIndex()==2)
			       {
		    		   System.out.println("SD");
		    		   Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		    		  System.setProperty("org.lwjgl.opengl.Window.undecorated", "false");
		    		   
		    		   Gdx.graphics.setWindowedMode(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		    		
			       }
		    }
		});
		checkbox.addListener(new ChangeListener() {
		    @Override
		    public void changed (ChangeEvent event, Actor actor) {
		     
		       if(bg.getCheckedIndex()==0)
		       {
		    	   System.out.println("D");
		    	   Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		       }
		    }
		});
		
		checkbox2.addListener(new ChangeListener() {
		    @Override
		    public void changed (ChangeEvent event, Actor actor) {
		     
		    	   if(bg.getCheckedIndex()==1)
			       {
		    		   System.out.println("SsD");
		    		   Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		    		   System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		    		   Gdx.graphics.setWindowedMode(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			       }
		    }
		});
		
		
		
		

		// Create table
		table = new Table();
		table.debug(); //Enables debug

		// Set table structure

		table.row();
		//table.add(MusicVol);
		table.add(slider).center();
		table.row();
		stage.addActor(label);
		stage.addActor(textButton);
		table.padBottom(30f);

		// Pack table
		table.setFillParent(true);
		table.pack();

		// Set table's alpha to 0
		table.getColor().a = 0f;



		// Slider listener
		slider.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				TiledGameMap.volume=slider.getValue();
				// Set volume to slider.getValue();
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			};
		});

		// Adds created table to stage
		stage.addActor(table);
		checkbox.setChecked(false);
		
		stage.addActor(checkbox3);
		stage.addActor(checkbox);
		stage.addActor(checkbox2);
		// To make the table appear smoothly
		table.addAction(fadeIn(1f));

	}




	@Override
	public void dispose() {
		sliderBackgroundTex.dispose();
		sliderKnobTex.dispose(); 

		font.dispose();
		stage.dispose();
	}


	@Override
	public void show() {

		create();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 60f));
		stage.draw();
		if(Gdx.input.isKeyJustPressed(Keys.B))
		{
			System.out.println("WTF");
			game.setScreen(new Rig(game));
		}


	}

	@Override
	public void hide() {
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
}
