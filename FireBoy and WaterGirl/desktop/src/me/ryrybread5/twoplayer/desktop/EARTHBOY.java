package me.ryrybread5.twoplayer.desktop;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.ryrybread5.twoplayer.Screenrun;
import me.ryrybread5.twoplayer.settings.MainMenuSample;

public class EARTHBOY {
	static LwjglApplicationConfiguration  config = new LwjglApplicationConfiguration();
	public static void main (String[] arg) {
		
		config.foregroundFPS =30;


		config.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
		config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
		config.fullscreen = false;
		config.resizable=false;
		new LwjglApplication(new Screenrun(), config);

	}
	public void ChangeScreen(int screen)
	{
		//1=FullScreen
		if(screen==1)
		{
			config.fullscreen = true;
			
		}
		else if(screen==2)
		{
			 System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
			//config.fullscreen = true;
			
		}
		else if(screen==3)
		{
			config.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
			config.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
			config.fullscreen = false;
		
		}
		new LwjglApplication(new MainMenuSample(null), config);
		
		//2=BorderLess Fullscreen
		//3=Windowed 
	}
}
