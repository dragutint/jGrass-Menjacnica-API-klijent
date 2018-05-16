package menjacnica.sistemskeoperacije;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import menjacnica.klase.Zemlja;
import menjacnica.util.URLConnectionUtil;

public class SOVratiZemlje {
	
	public static LinkedList<Zemlja> izvrsi() {
		LinkedList<Zemlja> zemlje = new LinkedList<Zemlja>();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String rezultat = null;

		try {
			rezultat = URLConnectionUtil.getContent("http://free.currencyconverterapi.com/api/v3/countries");
		} catch (IOException e) {
			e.printStackTrace();
		}

		JsonObject res = gson.fromJson(rezultat, JsonObject.class);
		JsonObject obj = res.get("results").getAsJsonObject();

		Set<Entry<String, JsonElement>> attributeEntres = obj.entrySet();
		
		for (Entry<String, JsonElement> entry : obj.entrySet()) {
		    String n = entry.getKey();
		    JsonObject v = (JsonObject) entry.getValue();
		    
		    Zemlja z = new Zemlja();
		    z.setAlpha3(v.get("alpha3").toString().replaceAll("\"", ""));
		    z.setCurrencyID(v.get("currencyId").toString().replaceAll("\"", ""));
		    z.setCurrencyName(v.get("currencyName").toString().replaceAll("\"", ""));
		    z.setName(v.get("name").toString().replaceAll("\"", ""));
		    z.setId(v.get("id").toString().replaceAll("\"", ""));
		    
		    zemlje.add(z);
		}
		
		return zemlje;
	}
	
}
