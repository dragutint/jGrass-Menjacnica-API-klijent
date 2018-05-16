package menjacnica.klase;

import java.util.LinkedList;

import menjacnica.sistemskeoperacije.SOVratiKurs;
import menjacnica.sistemskeoperacije.SOVratiZemlje;

public class Menjacnica {
	private LinkedList<Zemlja> zemlje;
	
	public Menjacnica() {
		zemlje = SOVratiZemlje.izvrsi();
	}

	public LinkedList<Zemlja> getZemlje() {
		return zemlje;
	}

	public void setZemlje(LinkedList<Zemlja> zemlje) {
		this.zemlje = zemlje;
	}
	
	public double vratiKurs(String iz, String u) {
		return SOVratiKurs.izvrsi(iz, u);
	}
}
