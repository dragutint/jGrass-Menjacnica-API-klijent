package menjacnica.klase;

import java.util.GregorianCalendar;

public class Log {
	private GregorianCalendar datumVreme;
	private String izValuta;
	private String uValuta;
	private double kurs;

	public GregorianCalendar getDatumVreme() {
		return datumVreme;
	}

	public void setDatumVreme(GregorianCalendar datumVreme) {
		this.datumVreme = datumVreme;
	}

	public String getIzValuta() {
		return izValuta;
	}

	public void setIzValuta(String izValuta) {
		this.izValuta = izValuta;
	}

	public String getuValuta() {
		return uValuta;
	}

	public void setuValuta(String uValuta) {
		this.uValuta = uValuta;
	}

	public double getKurs() {
		return kurs;
	}

	public void setKurs(double kurs) {
		this.kurs = kurs;
	}

	@Override
	public String toString() {
		return "Log [datumVreme=" + datumVreme + ", izValuta=" + izValuta + ", uValuta=" + uValuta + ", kurs=" + kurs
				+ "]";
	}
	
}
