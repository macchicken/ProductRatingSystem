package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Publisher {

	private String press;
	private int edition;
	private Date publishDate;
	private SimpleDateFormat formator=new SimpleDateFormat("MMMMMMMMM dd,yyyy",Locale.ENGLISH);
	
	public Publisher(String press, int edition, Date publishDate) {
		super();
		this.press = press;
		this.edition = edition;
		this.publishDate = publishDate;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public Date getPublishDate() {
		return (Date) publishDate.clone();
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public String getGetDisplayPublishDate() {
		if (publishDate!=null){return formator.format(publishDate);}
		return "";
	}
}
