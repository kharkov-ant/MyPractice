package ua.nure.kharkov.SummaryTask3.entity;

import java.util.ArrayList;
import java.util.List;


public class OldCard {

	private String thema, type, country, valuable;
	private Boolean send;
	private int year;
	private List<Author> authors;

	public String getThema() {
		return thema;
	}

	public void setThema(String thema) {
		this.thema = thema;
	}

	public List<Author> getAuthors() {
		if (authors == null) {
			authors = new ArrayList<Author>();
		}
		return authors;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isSend() {
		if (send == null) {
			return false;
		}
		return send;
	}

	public void setSend(Boolean value) {
		this.send = value;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getValuable() {
		return valuable;
	}

	public void setValuable(String valuable) {
		this.valuable = valuable;
	}
}
