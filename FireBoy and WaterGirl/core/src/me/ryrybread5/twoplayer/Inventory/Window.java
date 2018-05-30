package me.ryrybread5.twoplayer.Inventory;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSplitPane;
import javax.swing.table.DefaultTableModel;

import me.ryrybread5.twoplayer.entity.Entity;

import javax.swing.border.LineBorder;
import java.awt.Color;

public class Window extends JFrame {
	private JTable table;
	private JTable armorTable;
	Image background;
	Entity e;
	private static boolean fixer;
	public String[] lootWithout= new String[9];
	/**
	 * Launch the application.
	 */
	public void start()
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Window frame = new Window();
					frame.setVisible(true);

					fixer=true;

				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
				);

	}
	public Window() {

		if(fixer)
		{
			setAlwaysOnTop(true);
			background=new ImageIcon("badlogic.jpg").getImage();
			Icon test = new ImageIcon("badlogic.jpg");
			setMinimumSize(new Dimension(700, 700));

			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			getContentPane().add(tabbedPane, BorderLayout.CENTER);

			JPanel panel = new JPanel();
			tabbedPane.addTab("Inventory", null, panel, null);

			table = new JTable();
			table.setEnabled(false);
			table.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
			table.setRowHeight(200);
			table.setRowSelectionAllowed(false);
			int[] lootR = RandomRarity();
			String[] loot = RandomStuff(lootR);


			saveInventory(loot);
			table.setModel(new DefaultTableModel(new Object[][] {
				{"   "+loot[1], "   "+loot[2], "   "+loot[3],},
				{"   "+loot[0], "   "+loot[4], "   "+loot[5],},
				{"   "+loot[6], "   "+loot[7], "   "+loot[8],},
			},
					new String[] {
							"New column", "New column", "New column"
			}
					));

			table.getColumnModel().getColumn(0).setPreferredWidth(200);
			table.getColumnModel().getColumn(1).setPreferredWidth(200);
			table.getColumnModel().getColumn(2).setPreferredWidth(200);

			panel.add(table);



			JSplitPane splitPane = new JSplitPane();
			tabbedPane.addTab("Armor", null, splitPane, null);

			armorTable = new JTable();
			armorTable.setEnabled(false);
			armorTable.setRowHeight(105);
			armorTable.setModel(new DefaultTableModel(
					new Object[][] {
						{null},
						{null},
						{null},
						{null},
						{null},
					},
					new String[] {
							"New column"
					}
					));
			armorTable.getColumnModel().getColumn(0).setPreferredWidth(110);
			splitPane.setLeftComponent(armorTable);

			JProgressBar progressBar = new JProgressBar();
			progressBar.setIndeterminate(true);
			progressBar.setStringPainted(true);
			splitPane.setRightComponent(progressBar);
		}
	}
	public int[] RandomRarity()
	{
		Random generator = new Random();
		int[] loot = new int[9];
		for(int x=0; x<loot.length; x++)
		{
			loot[x]=generator.nextInt(100);

		}
		for(int x=0; x<loot.length; x++)
		{
			if(loot[x]%30==0)
			{
				//Legendary
				loot[x]=4;
			}
			else if(loot[x]%9==0)
			{
				//Epic
				loot[x]=3;
			}
			else if(loot[x]%7==0)
			{
				//Rare
				loot[x]=2;
			}
			else if(loot[x]%4==0)
			{
				//Common
				loot[x]=1;
			}
			else
			{
				//Nothing
				loot[x]=0;
			}
		}


		return loot;
	}
	public String[] RandomStuff(int[] rarity)
	{
		Random generator = new Random();
		String[] legendary= {"Lighter Pants", "One Hit Kill Sword"};
		String[] epic={"Double Jump Boots", "Iron Sword"};
		String[] rare= {"Stone Sword", "Light Pants"};
		String[] common={"Balloon Sword", "Heavy Boots"};
		String[] nothing= {"Some Dust", "A Broken CD"};
		String[] loot= new String[9];

		int z=0;
		int p=0;
		for(int x=0; x<loot.length; x++)
		{

			z=generator.nextInt(2);
			if(rarity[x]==4)
			{

				loot[x]=legendary[z];
				lootWithout[p]=legendary[z];
				p++;
			}
			else if(rarity[x]==3)
			{

				loot[x]=epic[z];
				lootWithout[p]=epic[z];
				p++;
			}
			else if(rarity[x]==2)
			{

				loot[x]=rare[z];
				lootWithout[p]=rare[z];
				p++;
			}
			else if(rarity[x]==1)
			{

				loot[x]=common[z];
				lootWithout[p]=common[z];
				p++;
			}
			else
			{
				loot[x]=nothing[z];
			}

		}

		return loot;
	}
	public void saveInventory(String [] loot)
	{
		
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter( new FileOutputStream("Inventory.txt"), "UTF-8")))
		{
	
			for(int x=0; x<=lootWithout.length; x++)
			{
				if(lootWithout[x]!=null)
				{

					writer.write(lootWithout[x]+",");
					writer.write("\n");
				}
				System.out.println(lootWithout[x]);
			}

		}
		catch (Exception e) 
		{

		}
	}
}
