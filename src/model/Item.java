package model;

public class Item {
	private String productName;
	private String modelNum;
	private String brand;
	private String color;
	private String price;
	private String itemDemensions;
	private String imageUrl;
	private String category;
	private String qty;

	public Item(String productName, String modelNum, String brand, String color, String price, String itemDemensions,
			String imageUrl, String category,String qty) {
		super();
		this.productName = productName;
		this.modelNum = modelNum;
		this.brand = brand;
		this.color = color;
		this.price = price;
		this.itemDemensions = itemDemensions;
		this.imageUrl = imageUrl;
		this.category = category;
		this.qty = qty;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getProductName() {
		return productName;
	}

	public String getModelNum() {
		return modelNum;
	}

	public String getBrand() {
		return brand;
	}

	public String getColor() {
		return color;
	}

	public String getPrice() {
		return price;
	}

	public String getItemDemensions() {
		return itemDemensions;
	}

	public String getCategory() {
		return category;
	}
	public String getQty() {
		return qty;
	}


}
