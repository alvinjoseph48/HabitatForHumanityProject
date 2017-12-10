package model;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Item> cartList;

	public Cart() {
		cartList = new ArrayList<Item>();
	}

	public boolean addItem(Item item) {
		return cartList.add(item);
		
	}
	public Item getItem(String productName) {
		for (int i = 0; i < cartList.size(); i++) {
			if (productName.equals(cartList.get(i).getProductName())) {
				return cartList.get(i);
			}
		}
		return null;
	}
	public Item removeItem(String productName) {
		for (int i = 0; i < cartList.size(); i++) {
			if (productName.equals(cartList.get(i).getProductName())) {
				return cartList.remove(i);
			}
		}
		return null;
	}
	public ArrayList<Item> getCart(){
		return cartList;
	}
}
