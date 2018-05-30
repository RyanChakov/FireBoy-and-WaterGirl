package me.ryrybread5.twoplayer.settings;

import java.awt.Font;

import javax.swing.JOptionPane;

public class Alpha {

	public void Popup() {
		
		
	
		String[] options = new String[] { "Options", "Instructions", "Quit the game", "Cancel"};
		int response = JOptionPane.showOptionDialog(null, "Message", "Title",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options, options[0]);

		// Where response == 0 for Yes, 1 for No, 2 for Maybe and -1 or 3 for Escape/Cancel.
		switch(response)
		{
		case 0:  String[] soptions = new String[] { "Screen", "Font", "Cancel"};
		int sresponse = JOptionPane.showOptionDialog(null, "Message", "Title",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, soptions, soptions[0]);
		switch(sresponse)
		{
		case 0: String[] poptions = new String[] { "FullScreen", "Windowed", "Fullscreen Windowed"};
		int presponse = JOptionPane.showOptionDialog(null, "Message", "Title",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, poptions, poptions[0]);
		switch(presponse)
		{
		case 0://Fullscreen
			break;
		case 1://Windowed
			break;
		case 2://FW
			break;

		}
		break;
		case 1: 	String[] foptions = new String[] { "Dialog", "Calibri", "Georgia", "Times New Roman"};
		int fresponse = JOptionPane.showOptionDialog(null, "Message", "Title",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, foptions, foptions[0]);
		Font fnt;
		switch(sresponse)
		{
		case 0:fnt= new Font("Dialog", Font.PLAIN, 12);
		break;
		case 1:fnt= new Font("Calibri", Font.PLAIN, 12);
		break;
		case 2:fnt= new Font("Georgia", Font.PLAIN, 12);
		break;
		case 3: fnt= new Font("Times New Roman", Font.PLAIN, 12);
		break;

		}
		break;
		case 2:
			break;
		}
		case 1:
			JOptionPane.showMessageDialog(null, "This is a Fire boy and Water Girl type game \n Your goal is to get to level 3 of the game \n When you are jumping on the blocks dont fall in lava or you die and have to restart \n Good luck");
			break;
		case 2: System.exit(0);

		case 3: //quit the screen
			break;
		}
		
	}

}
