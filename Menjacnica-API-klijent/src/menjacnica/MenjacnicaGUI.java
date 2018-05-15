package menjacnica;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import menjacnica.util.URLConnectionUtil;
import javax.swing.JButton;

public class MenjacnicaGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblIzValuteZemlje;
	private JLabel lblUValutuZemlje;
	private JTextField tfIz;
	private JTextField tfU;
	private JComboBox comboBoxIz;
	private JComboBox comboBoxU;

	private LinkedList<Zemlja> listaZemalja;
	private JButton btnKonvertuj;
	private LinkedList<Zemlja> zemlje = vratiZemlje();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenjacnicaGUI frame = new MenjacnicaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenjacnicaGUI() {
		setTitle("Menjacnica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblIzValuteZemlje());
		contentPane.add(getLblUValutuZemlje());
		contentPane.add(getTfIz());
		contentPane.add(getTfU());
		contentPane.add(getComboBoxIz());
		contentPane.add(getComboBoxU());
		contentPane.add(getBtnKonvertuj());
	}

	private JLabel getLblIzValuteZemlje() {
		if (lblIzValuteZemlje == null) {
			lblIzValuteZemlje = new JLabel("Iz valute zemlje:");
			lblIzValuteZemlje.setBounds(59, 57, 123, 16);
		}
		return lblIzValuteZemlje;
	}

	private JLabel getLblUValutuZemlje() {
		if (lblUValutuZemlje == null) {
			lblUValutuZemlje = new JLabel("U valutu zemlje:");
			lblUValutuZemlje.setBounds(241, 57, 123, 16);
		}
		return lblUValutuZemlje;
	}

	private JTextField getTfIz() {
		if (tfIz == null) {
			tfIz = new JTextField();
			tfIz.setBounds(59, 161, 123, 22);
			tfIz.setColumns(10);
		}
		return tfIz;
	}

	private JTextField getTfU() {
		if (tfU == null) {
			tfU = new JTextField();
			tfU.setColumns(10);
			tfU.setBounds(241, 161, 123, 22);
		}
		return tfU;
	}

	private JComboBox getComboBoxIz() {
		if (comboBoxIz == null) {
			comboBoxIz = new JComboBox();
			comboBoxIz.setBounds(59, 107, 123, 22);

			
			for (int i = 0; i < zemlje.size(); i++) {
				comboBoxIz.addItem(zemlje.get(i));
			}
		}
		return comboBoxIz;
	}

	private JComboBox getComboBoxU() {
		if (comboBoxU == null) {
			comboBoxU = new JComboBox();
			comboBoxU.setBounds(241, 107, 123, 22);
			
			for (int i = 0; i < zemlje.size(); i++) {
				comboBoxU.addItem(zemlje.get(i));
			}
		}
		return comboBoxU;
	}

	private JButton getBtnKonvertuj() {
		if (btnKonvertuj == null) {
			btnKonvertuj = new JButton("Konvertuj");
			btnKonvertuj.setBounds(160, 215, 97, 25);
		}
		return btnKonvertuj;
	}
	
	public LinkedList<Zemlja> vratiZemlje() {
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
		    z.setAlpha3(v.get("alpha3").toString());
		    z.setCurrencyID(v.get("currencyId").toString());
		    z.setCurrencyName(v.get("currencyName").toString());
		    z.setName(v.get("name").toString());
		    z.setId(v.get("id").toString());
		    
		    zemlje.add(z);
		}
		
		return zemlje;
	}

	
}
