package menjacnica.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import menjacnica.klase.Zemlja;
import menjacnica.sistemskeoperacije.SOVratiZemlje;

public class MenjacnicaGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblIzValuteZemlje;
	private JLabel lblUValutuZemlje;
	private JTextField tfIz;
	private JTextField tfU;
	private JComboBox comboBoxIz;
	private JComboBox comboBoxU;
	private LinkedList<Zemlja> zemlje;
	private JButton btnKonvertuj;

	/**
	 * Create the frame.
	 */
	public MenjacnicaGUI() {
		zemlje = GUIKontroler.vratiZemlje();
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
			btnKonvertuj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					tfU.setText( GUIKontroler.konvertuj(tfIz.getText(), (Zemlja)comboBoxIz.getSelectedItem(), (Zemlja)comboBoxU.getSelectedItem()));
				}
			});
			btnKonvertuj.setBounds(160, 215, 97, 25);
		}
		return btnKonvertuj;
	}
}
