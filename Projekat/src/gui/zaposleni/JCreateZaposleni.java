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
import util.Formating;

public class JCreateZaposleni extends JDialog {

	private static final long serialVersionUID = -6576572204556705752L;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfJMBG;
	private JTextField tfDatumRodj;
	private JTextField tfEmail;
	private JTextField tfBroj;
	private JTextField tfUlica;
	private JTextField tfGrad;
	private JLabel lblError;
	private JLabel lblErrorJMBG;
	private JLabel lblDatumError;
	private JLabel lblErrorNumber;
	private JDialog thisDialog = this;

	private JList<Softver> listSoftveri;
	
 /* Rad sa JComboBox-om na osnovu sledeceg linka: https://www.geeksforgeeks.org/java-swing-jcombobox-examples/*/
	
	private JComboBox<RadnoMesto> cbRadnoMesto;

	private boolean somethingEmpty = false;
	private boolean badFormating = false;
	private boolean notUnique = false;
	private boolean notNumber = false;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { try { JCreateZaposleni dialog = new
	 * JCreateZaposleni();
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * Create the dialog.
	 */
	public JCreateZaposleni(Refreshable main) {
		setTitle("Kreiranje novog zaposlenog");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dimension.width * 1 / 4, dimension.height * 1 / 4, dimension.width * 1 / 2, dimension.height * 1 / 2);

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
							ZaposleniCrud.createZaposleni(tfJMBG.getText(), tfIme.getText(), tfPrezime.getText(),
									Formating.parseDate(tfDatumRodj.getText()), tfEmail.getText(),
									new Adresa(Integer.parseInt(tfBroj.getText()), tfUlica.getText(), tfGrad.getText()),
									listSoftveri.getSelectedValuesList(), (RadnoMesto) cbRadnoMesto.getSelectedItem());
							main.refresh(thisDialog);
						} else {
							lblError.setVisible(somethingEmpty);
							lblErrorJMBG.setVisible(notUnique);
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
			lblIme.setBounds(40, 54, 25, 15);
			tfIme = new JTextField();
			tfIme.setBounds(208, 52, 114, 19);
			tfIme.setColumns(10);
			panel.setLayout(null);
			panel.add(lblIme);
			panel.add(tfIme);

			JLabel lblPrezime = new JLabel("Prezime");
			lblPrezime.setBounds(40, 80, 148, 15);
			panel.add(lblPrezime);

			tfPrezime = new JTextField();
			tfPrezime.setBounds(208, 78, 114, 19);
			panel.add(tfPrezime);
			tfPrezime.setColumns(10);

			JLabel lblJMBG = new JLabel("JMBG");
			lblJMBG.setBounds(40, 106, 148, 15);
			panel.add(lblJMBG);

			tfJMBG = new JTextField();
			tfJMBG.setBounds(208, 104, 114, 19);
			panel.add(tfJMBG);
			tfJMBG.setColumns(10);

			lblErrorJMBG = new JLabel("Vec postoji zaposleni sa istim JMBGom!");
			lblErrorJMBG.setVisible(false);
			lblErrorJMBG.setBounds(440, 106, 501, 15);
			lblErrorJMBG.setForeground(Color.RED);
			panel.add(lblErrorJMBG);

			JLabel lblDatumRodj = new JLabel("Datum rodjenja");
			lblDatumRodj.setBounds(40, 132, 148, 15);
			panel.add(lblDatumRodj);

			tfDatumRodj = new JTextField();
			tfDatumRodj.setBounds(208, 130, 114, 19);
			tfDatumRodj.setToolTipText("");
			panel.add(tfDatumRodj);
			tfDatumRodj.setColumns(10);

			lblDatumError = new JLabel("Datum mora biti u formatu dd.MM.yyyy!");
			lblDatumError.setVisible(false);
			lblDatumError.setBounds(440, 132, 501, 15);
			lblDatumError.setForeground(Color.RED);
			panel.add(lblDatumError);

			JLabel lblEmail = new JLabel("Email");
			lblEmail.setBounds(40, 158, 148, 15);
			panel.add(lblEmail);

			tfEmail = new JTextField();
			tfEmail.setBounds(208, 156, 114, 19);
			panel.add(tfEmail);
			tfEmail.setColumns(10);

			JLabel lblAdresaStanovanja = new JLabel("Adresa stanovanja");
			lblAdresaStanovanja.setBounds(40, 182, 148, 15);
			panel.add(lblAdresaStanovanja);

			JLabel lblBroj = new JLabel("Broj");
			lblBroj.setBounds(208, 182, 232, 15);
			panel.add(lblBroj);

			JLabel lblUlica = new JLabel("Ulica");
			lblUlica.setBounds(459, 182, 34, 15);
			panel.add(lblUlica);

			JLabel lblGrad = new JLabel("Grad");
			lblGrad.setBounds(709, 182, 34, 15);
			panel.add(lblGrad);

			tfBroj = new JTextField();
			tfBroj.setBounds(208, 204, 114, 19);
			panel.add(tfBroj);
			tfBroj.setColumns(10);

			tfUlica = new JTextField();
			tfUlica.setBounds(459, 204, 114, 19);
			panel.add(tfUlica);
			tfUlica.setColumns(10);

			tfGrad = new JTextField();
			tfGrad.setBounds(709, 204, 114, 19);
			panel.add(tfGrad);
			tfGrad.setColumns(10);

			JLabel lblSoftveri = new JLabel("Softveri");
			lblSoftveri.setBounds(40, 315, 148, 15);
			panel.add(lblSoftveri);

			listSoftveri = new JList<>();
			DefaultListModel<Softver> modelSoftveri = new DefaultListModel<>();
			modelSoftveri.addAll(SoftverCrud.getAllSoftveri());
			listSoftveri.setModel(modelSoftveri);
			listSoftveri.setBounds(208, 230, 703, 185);
			panel.add(listSoftveri);

			lblError = new JLabel("Popunite svako polje!");
			lblError.setVisible(false);
			lblError.setBounds(208, 453, 482, 15);

			JLabel lblRadnoMesto = new JLabel("Radno mesto");
			lblRadnoMesto.setBounds(40, 426, 148, 15);
			panel.add(lblRadnoMesto);

			cbRadnoMesto = new JComboBox<>(RadnoMesto.values());
//			cbRadnoMesto = new JComboBox<>();
			cbRadnoMesto.setBounds(208, 422, 232, 24);
			panel.add(cbRadnoMesto);
			lblError.setForeground(Color.RED);
			lblError.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblError);

			JLabel lblddMMyyyy = new JLabel("dd.MM.yyyy.");
			lblddMMyyyy.setBounds(331, 132, 97, 15);
			panel.add(lblddMMyyyy);

			lblErrorNumber = new JLabel("Unesite broj!");
			lblErrorNumber.setForeground(Color.RED);
			lblErrorNumber.setVisible(false);
			lblErrorNumber.setBounds(340, 206, 100, 15);
			panel.add(lblErrorNumber);
		}
	}

	private void validateInput() {
		badFormating = false;
		notNumber = false;
		notUnique = false;
		somethingEmpty = tfDatumRodj.getText() == null || tfDatumRodj.getText().isBlank() || /***/
				(badFormating = !Formating.checkFormat(tfDatumRodj.getText())) /***/
				|| tfIme.getText() == null || tfIme.getText().isBlank() || tfPrezime.getText() == null
				|| tfPrezime.getText().isBlank() || tfJMBG.getText() == null || tfJMBG.getText().isBlank()
				|| (notUnique = ZaposleniCrud.getZaposleniByID(tfJMBG.getText()) != null) || tfEmail.getText() == null
				|| tfEmail.getText().isBlank() || tfBroj.getText() == null || tfBroj.getText().isBlank()
				|| (notNumber = !Formating.checkNumber(tfBroj.getText())) || tfUlica.getText() == null
				|| tfUlica.getText().isBlank() || tfGrad.getText() == null || tfGrad.getText().isBlank()
				|| listSoftveri.getSelectedValuesList().isEmpty() || cbRadnoMesto.getSelectedIndex() == -1;
	}
}
