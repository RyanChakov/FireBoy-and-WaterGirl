package me.ryrybread5.twoplayer.world;

import java.util.HashMap;

public enum TileType {

	GRASS(1, true,false, "Grass"),
	DIRT(2, true,false, "Dirt"),
	SKY(3, false,false, "Sky"),
	LAVA(23, false,false, "Lava"),
	CLOUD(5, false,false, "Cloud"),
	STONE(6, true,false, "Stone"),
	WALL(7, false,false, "Wall"),
	CHEST(8, true,true, "Chest"),
	MULTIDOOR(11, false,true, "Door"),
	DOORRED(12, true,true, "DoorRed"),
	DOORBLUE(13, true,true, "DoorBlue"),
	CHESTBLUE(9, true,true, "ChestBlue"),
	CHESTPURPLE(10, true,true, "ChestPurple"),
	LAVAA(14, false,false, "Lava");

public static final int TILE_SIZE= 16;
	private int id;
	private boolean collidable;
	private boolean interactable;
	private String name;
	private float damage;
	private TileType(int id, boolean collidable, boolean interactable, String name)
	{
		this(id,collidable,interactable,name,0);
	}
	private TileType(int id, boolean collidable, boolean interactable, String name, float damage)
	{
		this.id=id;
		this.collidable=collidable;
		this.interactable=interactable;
		this.name=name;
		this.damage=damage;
	}
	public int getId() {
		return id;
	}
	public boolean isCollidable() {
		return collidable;
	}
	public void setInteractable(boolean interactable) {
		this.interactable = interactable;
	}
	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}
	public boolean isinteractable() {
		return interactable;
	}
	public String getName() {
		return name;
	}
	public float getDamage() {
		return damage;
	}

	private static HashMap<Integer, TileType> tileMap;

	static {
		tileMap = new HashMap<Integer, TileType>();
		for(TileType tileType : TileType.values())
		{
			tileMap.put(tileType.getId(), tileType);
		}


	}
	public static TileType getTileTypeByid(int id)
	{
		return tileMap.get(id);
	}
}
