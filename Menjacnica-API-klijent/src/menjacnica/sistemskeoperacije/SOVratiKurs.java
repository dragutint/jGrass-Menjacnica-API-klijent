package menjacnica.sistemskeoperacije;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import menjacnica.util.URLConnectionUtil;

public class SOVratiKurs {
	public static double izvrsi(String iz, String u) {
		String url = "http://free.currencyconverterapi.com/api/v3/convert?q=" + iz + "_" + u;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String rezultat = null;

		try {
			rezultat = URLConnectionUtil.getContent(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JsonObject obj = gson.fromJson(rezultat, JsonObject.class);
		JsonObject query = obj.get("query").getAsJsonObject();
		JsonObject results = obj.get("results").getAsJsonObject();
		
		if(Integer.parseInt(query.get("count").toString()) == 0) {
			return -1;
		} else {
			JsonObject p = (JsonObject) results.get(iz + "_" + u);
			return Double.parseDouble(p.get("val").toString());
		}
	}
}
