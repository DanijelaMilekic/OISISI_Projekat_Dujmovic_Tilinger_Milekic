package gui.zaposleni;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.RadnoMesto;
import model.Softver;

public class JEditZaposleni extends JDialog {

	private static final long serialVersionUID = 1468521783800577806L;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfDatumRodj;
	private JTextField tfEmail;
	private JTextField tfBroj;
	private JTextField tfUlica;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JEditZaposleni dialog = new JEditZaposleni();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JEditZaposleni() {
		setTitle("Izmena podataka zaposlenog");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dimension.width * 1/4, dimension.height * 1/4, dimension.width * 1/2, dimension.height * 1/2);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			JLabel lblIme = new JLabel("Ime");
			lblIme.setBounds(40, 35, 25, 15);
			tfIme = new JTextField();
			tfIme.setBounds(208, 33, 114, 19);
			tfIme.setColumns(10);
			panel.setLayout(null);
			panel.add(lblIme);
			panel.add(tfIme);
			
			JLabel lblPrezime = new JLabel("Prezime");
			lblPrezime.setBounds(40, 61, 148, 15);
			panel.add(lblPrezime);
			
			tfPrezime = new JTextField();
			tfPrezime.setBounds(208, 59, 114, 19);
			panel.add(tfPrezime);
			tfPrezime.setColumns(10);
			
			JLabel lblDatumRodj = new JLabel("Datum rodjenja");
			lblDatumRodj.setBounds(40, 87, 148, 15);
			panel.add(lblDatumRodj);
			
			tfDatumRodj = new JTextField();
			tfDatumRodj.setBounds(208, 85, 114, 19);
			tfDatumRodj.setText("dd.MM.yyyy");
			tfDatumRodj.setToolTipText("");
			panel.add(tfDatumRodj);
			tfDatumRodj.setColumns(10);
			
			JLabel lblDatumError = new JLabel("Datum mora biti u formatu dd.MM.yyyy!");
			lblDatumError.setBounds(440, 87, 501, 15);
			lblDatumError.setVisible(false);
			lblDatumError.setForeground(Color.RED);
			panel.add(lblDatumError);
			
			JLabel lblEmail = new JLabel("Email");
			lblEmail.setBounds(40, 113, 148, 15);
			panel.add(lblEmail);
			
			tfEmail = new JTextField();
			tfEmail.setBounds(208, 111, 114, 19);
			panel.add(tfEmail);
			tfEmail.setColumns(10);
			
			JLabel lblAdresaStanovanja = new JLabel("Adresa stanovanja");
			lblAdresaStanovanja.setBounds(40, 137, 148, 15);
			panel.add(lblAdresaStanovanja);
			
			JLabel lblBroj = new JLabel("Broj");
			lblBroj.setBounds(208, 137, 232, 15);
			panel.add(lblBroj);
			
			JLabel lblUlica = new JLabel("Ulica");
			lblUlica.setBounds(459, 137, 34, 15);
			panel.add(lblUlica);
			
			JLabel lblGrad = new JLabel("Grad");
			lblGrad.setBounds(709, 137, 34, 15);
			panel.add(lblGrad);
			
			tfBroj = new JTextField();
			tfBroj.setBounds(208, 159, 114, 19);
			panel.add(tfBroj);
			tfBroj.setColumns(10);
			
			tfUlica = new JTextField();
			tfUlica.setBounds(459, 159, 114, 19);
			panel.add(tfUlica);
			tfUlica.setColumns(10);
			
			textField = new JTextField();
			textField.setBounds(709, 159, 114, 19);
			panel.add(textField);
			textField.setColumns(10);
			
			JLabel lblSoftveri = new JLabel("Softveri");
			lblSoftveri.setBounds(40, 279, 148, 15);
			panel.add(lblSoftveri);
			
			JList<Softver> list = new JList<>();
			list.setBounds(208, 185, 733, 204);
			panel.add(list);
			
			JLabel lblRadnoMesto = new JLabel("RadnoMesto");
			lblRadnoMesto.setBounds(40, 400, 148, 15);
			panel.add(lblRadnoMesto);
			
			JComboBox<RadnoMesto> cbRadnoMesto = new JComboBox<RadnoMesto>();
			cbRadnoMesto.setBounds(208, 396, 232, 24);
			panel.add(cbRadnoMesto);
			
			JLabel lblSvePopuni = new JLabel("Popunite svako polje!");
			lblSvePopuni.setBounds(188, 427, 521, 15);
			lblSvePopuni.setVisible(false);
			lblSvePopuni.setForeground(Color.RED);
			lblSvePopuni.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblSvePopuni);
		}
	}

}
