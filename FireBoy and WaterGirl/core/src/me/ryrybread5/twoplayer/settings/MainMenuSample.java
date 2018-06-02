
package me.ryrybread5.twoplayer.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import me.ryrybread5.twoplayer.Adventure;
import me.ryrybread5.twoplayer.Screenrun;
import me.ryrybread5.twoplayer.world.TiledGameMap;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.Game;

public class MainMenuSample extends Game implements Screen {
	private static final String TAG = "BasicMenuSample";
	private static final int VIRTUAL_WIDTH = 1280;
	private static final int VIRTUAL_HEIGHT = 720;

	private Viewport viewport;

	private Slider slider;

	private BitmapFont font;

	private Table table;
	private Stage stage;
	private TextField a;

	private Texture sliderBackgroundTex, sliderKnobTex; 

	final Screenrun game;
	Adventure s = new Adventure();
	TiledGameMap ts= new TiledGameMap();
	OrthographicCamera camera;

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

		//Slider
		sliderBackgroundTex =new Texture("slider_background.png");
		sliderKnobTex = new Texture("slider_knob.png");
		Slider.SliderStyle ss = new Slider.SliderStyle();

		ss.background = new TextureRegionDrawable(new TextureRegion(sliderBackgroundTex));
		ss.knob = new TextureRegionDrawable(new TextureRegion(sliderKnobTex));
		slider = new Slider(0f, 5f, .1f, false, ss);


		// Create table
		table = new Table();
		table.debug(); //Enables debug

		// Set table structure


		table.row();
		table.add(slider).center().colspan(2).expand().pad(5f, 5f, 5f, 5f);
		table.row();

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
}
