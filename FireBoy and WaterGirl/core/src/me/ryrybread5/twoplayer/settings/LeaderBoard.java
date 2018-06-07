package me.ryrybread5.twoplayer.settings;

import java.sql.Array;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.aidanmurphey.scoremanager.ApiResponse;
import com.aidanmurphey.scoremanager.Record;
import com.aidanmurphey.scoremanager.ScoreManager;

import me.ryrybread5.twoplayer.entity.Entity;

public class LeaderBoard {
	String apiKey = "ZiQD6wzeCtmg3tr0dnqDpUgpcSN6Uulk"; //32 character string, given in your profile page
	int appID = 7; //Integer, given in your profile page. Different for each app you register with the service
	JOptionPane optionPane = new JOptionPane();
	Entity e;
	JFrame a = new JFrame();
	public void sendit(int score)
	{

		score*=e.extra;
		//a.setVisible(true);
		a.setAlwaysOnTop(true);


		String[] scores= new String[12];
		ScoreManager scoreManager = new ScoreManager(apiKey, appID);
		JOptionPane.showMessageDialog(a, "You Died :(");

		String nameOfRecordHolder = JOptionPane.showInputDialog(a, "Enter Your Name");


		ApiResponse response = scoreManager.submitRecord(nameOfRecordHolder, score);
		if (response.getFailed()) {
			System.out.println(response.getError());
		} else {
			System.out.println("Successfully added new record to leaderboards!");
		}


		ApiResponse response2 = scoreManager.requestRecords();
		if (response2.getFailed()) {
			System.out.println(response2.getError());
		} else {
			ArrayList<Record> records = response2.getRecords();
			records.forEach(record -> {
				int rank = records.indexOf(record) + 1;
				if(rank<=10)
					scores[rank]=rank + ") " + record.getName() + " = " + record.getScore();

			});
		}
		JOptionPane.showMessageDialog(a, scores);
		System.exit(0);
	}
}
