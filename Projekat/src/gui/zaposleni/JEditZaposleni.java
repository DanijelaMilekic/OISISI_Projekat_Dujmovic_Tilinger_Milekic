package gui.zaposleni;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import crud.SoftverCrud;
import crud.ZaposleniCrud;
import gui.Refreshable;
import model.Adresa;
import model.RadnoMesto;
import model.Softver;
import model.Zaposleni;
import util.Formating;

public class JEditZaposleni extends JDialog {

	private static final long serialVersionUID = 1468521783800577806L;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfDatumRodj;
	private JTextField tfEmail;
	private JTextField tfBroj;
	private JTextField tfUlica;
	private JTextField tfGrad;
	private JLabel lblError;
	private JLabel lblDatumError;
	private JLabel lblErrorNumber;
	private JDialog thisDialog = this;
	
	private JList<Softver> listSoftveri;

	private JComboBox<RadnoMesto> cbRadnoMesto;

	private boolean somethingEmpty = false;
	private boolean badFormating = false;
	private boolean notNumber = false;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			JEditZaposleni dialog = new JEditZaposleni();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public JEditZaposleni(Zaposleni zaposleni, Refreshable main) {
		setTitle("Izmena podataka zaposlenog");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dimension.width * 1 / 8, dimension.height * 1 / 8, dimension.width * 2/ 3, dimension.height * 2 /3);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						validateInput();

						if (!somethingEmpty) {
							zaposleni.setIme(tfIme.getText());
							zaposleni.setPrezime(tfPrezime.getText());
							zaposleni.setDatumRodjenja(Formating.parseDate(tfDatumRodj.getText()));
							zaposleni.setEmail(tfEmail.getText());
							zaposleni.setAdresaStanovanja(new Adresa(Integer.parseInt(tfBroj.getText()), tfUlica.getText(), tfGrad.getText()));
							zaposleni.setRadnoMesto((RadnoMesto) cbRadnoMesto.getSelectedItem());
							zaposleni.setSoftveri(listSoftveri.getSelectedValuesList());
							ZaposleniCrud.updateZaposleni(zaposleni);
							main.refresh(thisDialog);
						} else {
							lblError.setVisible(somethingEmpty && !badFormating && !notNumber);
							lblDatumError.setVisible(badFormating);
							lblErrorNumber.setVisible(notNumber);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						thisDialog.dispose();
					}
				});
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
			tfIme.setText(zaposleni.getIme());
			tfIme.setBounds(208, 33, 114, 19);
			tfIme.setColumns(10);
			panel.setLayout(null);
			panel.add(lblIme);
			panel.add(tfIme);
			
			JLabel lblPrezime = new JLabel("Prezime");
			lblPrezime.setBounds(40, 61, 148, 15);
			panel.add(lblPrezime);
			
			tfPrezime = new JTextField();
			tfPrezime.setText(zaposleni.getPrezime());
			tfPrezime.setBounds(208, 59, 114, 19);
			panel.add(tfPrezime);
			tfPrezime.setColumns(10);
			
			JLabel lblDatumRodj = new JLabel("Datum rodjenja");
			lblDatumRodj.setBounds(40, 87, 148, 15);
			panel.add(lblDatumRodj);
			
			tfDatumRodj = new JTextField();
			tfDatumRodj.setText(Formating.formatDate(zaposleni.getDatumRodjenja()));
			tfDatumRodj.setBounds(208, 85, 114, 19);
			tfDatumRodj.setToolTipText("");
			panel.add(tfDatumRodj);
			tfDatumRodj.setColumns(10);
			
			lblDatumError = new JLabel("Datum mora biti u formatu dd.MM.yyyy!");
			lblDatumError.setVisible(false);
			lblDatumError.setBounds(440, 87, 501, 15);
			lblDatumError.setForeground(Color.RED);
			panel.add(lblDatumError);
			
			JLabel lblEmail = new JLabel("Email");
			lblEmail.setBounds(40, 113, 148, 15);
			panel.add(lblEmail);
			
			tfEmail = new JTextField();
			tfEmail.setText(zaposleni.getEmail());
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
			tfBroj.setText(zaposleni.getAdresaStanovanja().getBroj() + "");
			tfBroj.setBounds(208, 159, 114, 19);
			panel.add(tfBroj);
			tfBroj.setColumns(10);
			
			tfUlica = new JTextField();
			tfUlica.setText(zaposleni.getAdresaStanovanja().getUlica());
			tfUlica.setBounds(459, 159, 114, 19);
			panel.add(tfUlica);
			tfUlica.setColumns(10);
			
			tfGrad = new JTextField();
			tfGrad.setText(zaposleni.getAdresaStanovanja().getGrad());
			tfGrad.setBounds(709, 159, 114, 19);
			panel.add(tfGrad);
			tfGrad.setColumns(10);
			
			JLabel lblSoftveri = new JLabel("Softveri");
			lblSoftveri.setBounds(40, 279, 148, 15);
			panel.add(lblSoftveri);
			
			listSoftveri = new JList<>();
			DefaultListModel<Softver> modelSoftveri = new DefaultListModel<>();
			modelSoftveri.addAll(SoftverCrud.getAllSoftveri());
			listSoftveri.setModel(modelSoftveri);
			listSoftveri.setSelectedIndices(SoftverCrud.indices(zaposleni.getSoftveri()));
			listSoftveri.setBounds(208, 185, 733, 204);
			panel.add(listSoftveri);
			
			JLabel lblRadnoMesto = new JLabel("RadnoMesto");
			lblRadnoMesto.setBounds(40, 400, 148, 15);
			panel.add(lblRadnoMesto);
			
			cbRadnoMesto = new JComboBox<>(RadnoMesto.values());
			cbRadnoMesto.setSelectedItem(zaposleni.getRadnoMesto());
//			cbRadnoMesto = new JComboBox<>();
			cbRadnoMesto.setBounds(208, 396, 232, 24);
			panel.add(cbRadnoMesto);
			
			lblError = new JLabel("Popunite svako polje!");
			lblError.setVisible(false);
			lblError.setBounds(188, 427, 521, 15);
			lblError.setForeground(Color.RED);
			lblError.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblError);
			
			lblErrorNumber = new JLabel("Unesite Broj!");
			lblErrorNumber.setForeground(Color.RED);
			lblErrorNumber.setVisible(false);
			lblErrorNumber.setBounds(352, 158, 70, 15);
			panel.add(lblErrorNumber);
		}
	}
	
	private void validateInput() {
		badFormating = false;
		notNumber = false;
		somethingEmpty = tfDatumRodj.getText() == null || tfDatumRodj.getText().isBlank() || /***/
				(badFormating = !Formating.checkFormat(tfDatumRodj.getText())) /***/
				|| tfIme.getText() == null || tfIme.getText().isBlank() || tfPrezime.getText() == null
				|| tfPrezime.getText().isBlank() || tfEmail.getText() == null
				|| tfEmail.getText().isBlank() || tfBroj.getText() == null || tfBroj.getText().isBlank()
				|| (notNumber = !Formating.checkNumber(tfBroj.getText())) || tfUlica.getText() == null
				|| tfUlica.getText().isBlank() || tfGrad.getText() == null || tfGrad.getText().isBlank()
				|| listSoftveri.getSelectedValuesList().isEmpty() || cbRadnoMesto.getSelectedIndex() == -1;
	}
}
