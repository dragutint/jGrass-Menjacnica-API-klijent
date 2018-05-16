package menjacnica.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import menjacnica.klase.Log;

public class LogsJsonUtility {

	public static JsonArray serializeLogs(LinkedList<Log> logs) {
		JsonArray logsArray = new JsonArray();
		
		for (int i = 0; i < logs.size(); i++) {
			Log l = logs.get(i);

			JsonObject logJson = new JsonObject();
			
			GregorianCalendar date = l.getDatumVreme();
			String datum = date.get(GregorianCalendar.YEAR) + "-" + date.get(GregorianCalendar.MONTH) + "-" + date.get(GregorianCalendar.DAY_OF_MONTH);
			datum += " " + date.get(GregorianCalendar.HOUR) + ":" + date.get(GregorianCalendar.MINUTE) + ":" + date.get(GregorianCalendar.SECOND);
			datum += "." + date.get(GregorianCalendar.MILLISECOND);
			logJson.addProperty("datumVreme", datum);
			logJson.addProperty("izValuta", l.getIzValuta());
			logJson.addProperty("uValuta", l.getuValuta());
			logJson.addProperty("kurs", l.getKurs());

			logsArray.add(logJson);
		}
		return logsArray;
	}

	public static LinkedList<Log> parseLogs(JsonArray logsJson) {
		LinkedList<Log> logs = new LinkedList<Log>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		df.setLenient(false);
		
		for (int i = 0; i < logsJson.size(); i++) {
			JsonObject logJson = (JsonObject) logsJson.get(i);

			Log l = new Log();
			try {
				java.util.Date date = df.parse(logJson.get("datumVreme").getAsString());
				Calendar cal = new GregorianCalendar();
				cal.setTime(date);
				l.setDatumVreme((GregorianCalendar) cal);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			l.setIzValuta(logJson.get("izValuta").getAsString());
			l.setuValuta(logJson.get("uValuta").getAsString());
			l.setKurs(logJson.get("kurs").getAsDouble());
			
			logs.add(l);
		}
		
		return logs;
	}
}
