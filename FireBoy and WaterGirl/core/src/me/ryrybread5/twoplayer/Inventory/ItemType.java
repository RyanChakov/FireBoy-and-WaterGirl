package me.ryrybread5.twoplayer.Inventory;


public enum ItemType 
{

	//higher the number the lighter it will later become

	LIGHTERPANT("Lighter Pants", "Armor",  15),
	ONEHITSW("One Hit Kill Sword", "Weapon",  0),
	DOUBLEJUMP("Double Jump Boots", "Armor",  0),
	IRONSW("Iron Sword", "Weapon",  -8),
	STONESW("Stone Sword", "Weapon",  -4),
	LIGHTPANT("Light Pants", "Armor",  7),
	BALLOONSW("Balloon Sword", "Weapon",  5),
	HEAVYBOOT("Heavy Boots", "Armor",  -12);
	
	
	
	private String id, type;
	private float weight;
	private ItemType(String id, String type, float weight) {
		this.id = id;
		this.type= type;
		this.weight = weight;
	}


	public String getId() {
		return id;
	}


	public String getType() {
		return type;
	}

	public float getWeight() {
		return weight;
	}


}