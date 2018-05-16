package menjacnica.gui;

import java.awt.EventQueue;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import menjacnica.klase.Menjacnica;
import menjacnica.klase.Zemlja;
import menjacnica.sistemskeoperacije.SONapraviLog;
import menjacnica.sistemskeoperacije.SOVratiKurs;

public class GUIKontroler {
	public static Menjacnica sistem;
	public static MenjacnicaGUI gp;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sistem = new Menjacnica();
					gp = new MenjacnicaGUI();
					gp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static double vratiKurs(Zemlja iz, Zemlja u) {
		double kurs = SOVratiKurs.izvrsi(iz.getCurrencyID(), u.getCurrencyID());
		SONapraviLog.izvrsi(new GregorianCalendar(), iz.getCurrencyID(), u.getCurrencyID(), kurs);
		return kurs;
	}
	
	public static LinkedList<Zemlja> vratiZemlje(){
		return sistem.getZemlje();
	}
	
	public static boolean proveriUnos(String unos) {
		char[] u = unos.toCharArray();
		for(int i = 0; i < u.length; i++) {
			if( !Character.isDigit(u[i]) ) {
				return false;
			}
		}
		return true;
	}
	
	public static String konvertuj(String unos, Zemlja iz, Zemlja u) {
		String rez = "";
		if(unos.isEmpty()) {
			JOptionPane.showMessageDialog(GUIKontroler.gp, "Niste uneli iznos za konverziju", "Greska", JOptionPane.WARNING_MESSAGE);
		} else if(!proveriUnos(unos)){
			JOptionPane.showMessageDialog(GUIKontroler.gp, "Iznos koji ste uneli nije validan", "Greska", JOptionPane.WARNING_MESSAGE);
		} else {
			double kurs = vratiKurs(iz, u);
			
			if(kurs != -1) {
				double broj = Double.parseDouble(unos) * kurs;
				rez += String.valueOf(broj);
			} else {
				JOptionPane.showMessageDialog(GUIKontroler.gp, "Nije moguca konverzija ove dve valute", "Greska", JOptionPane.WARNING_MESSAGE);
			}
		}
		return rez;
	}
}
