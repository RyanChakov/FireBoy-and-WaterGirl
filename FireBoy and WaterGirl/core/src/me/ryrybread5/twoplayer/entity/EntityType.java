package me.ryrybread5.twoplayer.entity;


public enum EntityType 
{
	PLAYER("player", 14, 32, 30),
	ENEMY("enemy", 14, 32, 30);
	private String id;
	private int width, height;
	private float weight;


	private EntityType(String id, int width, int height, float weight) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.weight = weight;
	}


	public String getId() {
		return id;
	}


	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}


	public float getWeight() {
		return weight;
	}

	public void setWeight(float nweight)
	{
		this.weight= nweight;	
	}


}