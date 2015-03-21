package model;

public class User {

	private int[] productRating;

	public User(int length) {
		super();
		this.productRating = new int[length];
	}
	
	public int[] getProductRating() {
		return productRating;
	}
	
	public void setProductRating(int index,int rating) {
		productRating[index]=rating;
	}

}
