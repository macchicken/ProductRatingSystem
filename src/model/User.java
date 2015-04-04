package model;

import java.util.Arrays;

public class User {

	private int[] productRating;

	public User(int length) {
		super();
		this.productRating = new int[length];
	}
	
	public int[] getProductRating() {
		if (productRating!=null) {
			return Arrays.copyOf(productRating, productRating.length);
		}else{return null;}
	}
	
	public void setProductRating(int index,int rating) {
		productRating[index]=rating;
	}

}
