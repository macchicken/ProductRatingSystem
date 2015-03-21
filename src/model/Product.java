package model;


public class Product {

	private String name;
	private String imgFile;
	private String authors;
	private int[] ratings=new int[5];
	private float averageRating;
	private int totalRating;
	private float price;
	private int paperBack;
	private Publisher publisher;
	private String language;
	private String isbn10;
	private String isbn13;

	public Product(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getPaperBack() {
		return paperBack;
	}

	public void setPaperBack(int paperBack) {
		this.paperBack = paperBack;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getIsbn10() {
		return isbn10;
	}

	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public int[] getRatings() {
		return ratings;
	}

	public void addRating(int rating){
		ratings[rating]=ratings[rating]+1;
	}
	
	public int getTotalRating(){
		totalRating=0;
		for (int i:ratings){totalRating+=i;}
		return totalRating;
	}
	
	public float getAverageRating(){
		int total=0;
		for (int i=0;i<ratings.length;i++){total=total+(i+1)*ratings[i];}
		int totalR=getTotalRating();
		if (totalR==0){return 0;}
		return averageRating=(Float.valueOf(String.valueOf(total)))/totalR;
	}

	public String getImgFile() {
		return imgFile;
	}

	public void setImgFile(String imgFile) {
		this.imgFile = imgFile;
	}

}
