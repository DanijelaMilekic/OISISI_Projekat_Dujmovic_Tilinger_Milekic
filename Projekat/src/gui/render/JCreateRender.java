package gui.render;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import crud.RenderCrud;
import gui.Refreshable;

public class JCreateRender extends JDialog {


	private final JPanel contentPanel = new JPanel();
	private JTextField tfNaziv;
	private JTextField tfMaterijali;
	private JTextField tfKamere;
	private JTextField tfSvetlo;
	private JTextField tfObjekti;
	private JLabel lblError;
	private JLabel lblErrorNaziv;
	private JDialog thisDialog = this;

	private JList<String> listMaterijali;
	private DefaultListModel<String> modelMaterijali;

	private JList<String> listKamere;
	private DefaultListModel<String> modelKamere;

	private JList<String> listObjekti;
	private DefaultListModel<String> modelObjekti;

	private boolean somethingEmpty = false;
	private boolean notUnique = false;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { try { JCreateRender dialog = new
	 * JCreateRender(); dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * Create the dialog.
	 */
	public JCreateRender(Refreshable main) {
		setTitle("Kreiranje novog rendera");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dimension.width * 1 / 8, dimension.height * 1 / 8, dimension.width * 2/ 3, dimension.height * 2 /3);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNaziv = new JLabel("Naziv");
			lblNaziv.setBounds(40, 66, 67, 15);
			contentPanel.add(lblNaziv);
		}
		{
			tfNaziv = new JTextField();
			tfNaziv.setBounds(142, 64, 509, 19);
			contentPanel.add(tfNaziv);
			tfNaziv.setColumns(10);
		}
		{
			lblErrorNaziv = new JLabel("Render sa takvim nazivom vec postoji!");
			lblErrorNaziv.setVisible(false);
			lblErrorNaziv.setBounds(659, 66, 269, 15);
			lblErrorNaziv.setForeground(Color.RED);
			contentPanel.add(lblErrorNaziv);
		}
		{
			JLabel lblMaterijali = new JLabel("Materijali");
			lblMaterijali.setBounds(40, 90, 67, 15);
			contentPanel.add(lblMaterijali);
		}
		{
			tfMaterijali = new JTextField();
			tfMaterijali.setBounds(142, 93, 509, 19);
			contentPanel.add(tfMaterijali);
			tfMaterijali.setColumns(10);
		}
		{
			JButton btnAddMaterijal = new JButton("Dodaj materijal");
			btnAddMaterijal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!modelMaterijali.contains(tfMaterijali.getText())) {
						modelMaterijali.addElement(tfMaterijali.getText());
					}
				}
			});
			btnAddMaterijal.setBounds(659, 90, 142, 25);
			contentPanel.add(btnAddMaterijal);
		}
		{
			listMaterijali = new JList<String>();
			modelMaterijali = new DefaultListModel<>();
			listMaterijali.setModel(modelMaterijali);
			listMaterijali.setBounds(142, 122, 509, 72);
			listMaterijali.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
			contentPanel.add(listMaterijali);
		}
		{
			JButton btnRemoveMaterijal = new JButton("Ukloni materijal");
			btnRemoveMaterijal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modelMaterijali.removeElement(listMaterijali.getSelectedValue()); 
				}
			});
			btnRemoveMaterijal.setBounds(659, 122, 145, 25);
			contentPanel.add(btnRemoveMaterijal);
		}
		{
			JLabel lblKamere = new JLabel("Kamere");
			lblKamere.setBounds(40, 206, 67, 15);
			contentPanel.add(lblKamere);
		}
		{
			tfKamere = new JTextField();
			tfKamere.setBounds(142, 204, 509, 19);
			contentPanel.add(tfKamere);
			tfKamere.setColumns(10);
		}
		{
			JButton btnAddKamera = new JButton("Dodaj kameru");
			btnAddKamera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!modelKamere.contains(tfKamere.getText())) {
						modelKamere.addElement(tfKamere.getText());
					}
				}
			});
			btnAddKamera.setBounds(659, 201, 132, 25);
			contentPanel.add(btnAddKamera);
		}
		{
			listKamere = new JList<String>();
			modelKamere = new DefaultListModel<>();
			listKamere.setModel(modelKamere);
			listKamere.setBounds(142, 233, 509, 71);
			listKamere.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			contentPanel.add(listKamere);
		}
		{
			JButton btnRemoveKamera = new JButton("Ukloni kameru");
			btnRemoveKamera.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modelKamere.removeElement(listKamere.getSelectedValue());
				}
			});
			btnRemoveKamera.setBounds(659, 233, 135, 25);
			contentPanel.add(btnRemoveKamera);
		}
		{
			JLabel lblSvetlo = new JLabel("Svetlo");
			lblSvetlo.setBounds(40, 313, 67, 15);
			contentPanel.add(lblSvetlo);
		}
		{
			tfSvetlo = new JTextField();
			tfSvetlo.setBounds(142, 311, 509, 19);
			contentPanel.add(tfSvetlo);
			tfSvetlo.setColumns(10);
		}
		{
			JLabel lblObjekti = new JLabel("Objekti");
			lblObjekti.setBounds(40, 342, 67, 15);
			contentPanel.add(lblObjekti);
		}
		{
			tfObjekti = new JTextField();
			tfObjekti.setBounds(142, 340, 509, 19);
			contentPanel.add(tfObjekti);
			tfObjekti.setColumns(10);
		}
		{
			JButton btnAddObjekat = new JButton("Dodaj objekat");
			btnAddObjekat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!modelObjekti.contains(tfObjekti.getText())) {
						modelObjekti.addElement(tfObjekti.getText());
					}
				}
			});
			btnAddObjekat.setBounds(659, 337, 133, 25);
			contentPanel.add(btnAddObjekat);
		}
		{
			listObjekti = new JList<>();
			modelObjekti = new DefaultListModel<>();
			listObjekti.setModel(modelObjekti);
			listObjekti.setBounds(142, 369, 509, 72);
			listObjekti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			contentPanel.add(listObjekti);
		}
		{
			JButton btnRemoveObjekat = new JButton("Ukloni objekat");
			btnRemoveObjekat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modelObjekti.removeElement(listObjekti.getSelectedValue());
				}
			});
			btnRemoveObjekat.setBounds(659, 369, 136, 25);
			contentPanel.add(btnRemoveObjekat);
		}
		{
			lblError = new JLabel("Popunite sva polja!");
			lblError.setBounds(328, 448, 136, 15);
			lblError.setVisible(false);
			lblError.setForeground(Color.RED);
			lblError.setHorizontalTextPosition(SwingConstants.CENTER);
			lblError.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblError);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						validateInput();
						if (!somethingEmpty) {							//enumerate pretvara u listu
							RenderCrud.createRender(tfNaziv.getText(), Collections.list(modelMaterijali.elements()),
									Collections.list(modelKamere.elements()), tfSvetlo.getText(),
									Collections.list(modelObjekti.elements())); 
							main.refresh(thisDialog);
						} else {
							lblError.setVisible(somethingEmpty && !notUnique); 
							lblErrorNaziv.setVisible(notUnique);
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
	}

	private void validateInput() {
		notUnique = false;
		somethingEmpty = tfNaziv.getText() == null || tfNaziv.getText().isBlank()
				|| (notUnique = RenderCrud.getRenderByID(tfNaziv.getText()) != null) || modelMaterijali.isEmpty()
				|| modelKamere.isEmpty() || modelObjekti.isEmpty() || tfSvetlo.getText() == null
				|| tfSvetlo.getText().isBlank();

	}

}
