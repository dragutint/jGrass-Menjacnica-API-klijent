package menjacnica.sistemskeoperacije;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import menjacnica.klase.Log;
import menjacnica.util.LogsJsonUtility;

public class SONapraviLog {
	public static void izvrsi(GregorianCalendar vreme, String iz, String u, double kurs) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		JsonArray logsArray = null;
		LinkedList<Log> logs = new LinkedList<Log>(); 
		
		//deserijalizacija
		try (FileReader reader = new FileReader("data/log.json")) {
			logsArray = gson.fromJson(reader, JsonArray.class);
			logs = LogsJsonUtility.parseLogs(logsArray);
		} catch (Exception e) {
			System.out.println("Greska: " + e.getMessage());
		}
	
		//dodavanje novog loga
		Log l = new Log();
		l.setDatumVreme(vreme);
		l.setIzValuta(iz);
		l.setuValuta(u);
		l.setKurs(kurs);
		logs.add(l);
		
		// serijalizacija
		JsonArray array = LogsJsonUtility.serializeLogs(logs);
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/log.json")))) {
			String arrayString = gson.toJson(array);
			out.println(arrayString);
		} catch (Exception e) {
			System.out.println("Greska1: " + e.getMessage());
		}
	}
}
