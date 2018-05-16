package menjacnica.klase;

import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Zemlja {
	private String alpha3;
	private String currencyID;
	private String currencyName;
	private String id;
	private String name;

	public Zemlja(String alpha3, String currencyID, String currencyName, String id,
			String name) {
		super();
		this.alpha3 = alpha3;
		this.currencyID = currencyID;
		this.currencyName = currencyName;
		this.id = id;
		this.name = name;
	}

	public Zemlja() {

	}

	public String getAlpha3() {
		return alpha3;
	}

	public void setAlpha3(String alpha3) {
		this.alpha3 = alpha3;
	}

	public String getCurrencyID() {
		return currencyID;
	}

	public void setCurrencyID(String currencyID) {
		this.currencyID = currencyID;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
