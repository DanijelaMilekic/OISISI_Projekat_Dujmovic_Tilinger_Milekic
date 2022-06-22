package gui.render;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.awt.FlowLayout;

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
import model.Render;

public class JEditRender extends JDialog {


	private final JPanel contentPanel = new JPanel();
	private JTextField tfMaterijali;
	private JTextField tfKamere;
	private JTextField tfSvetlo;
	private JTextField tfObjekti;
	private JLabel lblError;
	private JDialog thisDialog = this;
	

	private JList<String> listMaterijali;
	private DefaultListModel<String> modelMaterijali;

	private JList<String> listKamere;
	private DefaultListModel<String> modelKamere;

	private JList<String> listObjekti;
	private DefaultListModel<String> modelObjekti;

	private boolean somethingEmpty = false;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			JEditRender dialog = new JEditRender();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public JEditRender(Render render, Refreshable main) {
		setTitle("Izmena podataka rendera");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dimension.width * 1 / 8, dimension.height * 1 / 8, dimension.width * 2/ 3, dimension.height * 2 /3);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMaterijali = new JLabel("Materijali");
			lblMaterijali.setBounds(40, 62, 67, 15);
			contentPanel.add(lblMaterijali);
		}
		{
			tfMaterijali = new JTextField();
			tfMaterijali.setBounds(142, 65, 633, 19);
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
			btnAddMaterijal.setBounds(783, 62, 142, 25);
			contentPanel.add(btnAddMaterijal);
		}
		{
			listMaterijali = new JList<>();
			modelMaterijali = new DefaultListModel<>();
			modelMaterijali.addAll(render.getMaterijali());
			listMaterijali.setModel(modelMaterijali); //u materijale dodajemo sve one koji postoje u renderu
			listMaterijali.setBounds(142, 94, 633, 81);
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
			btnRemoveMaterijal.setBounds(783, 94, 145, 25);
			contentPanel.add(btnRemoveMaterijal);
		}
		{
			JLabel lblKamere = new JLabel("Kamere");
			lblKamere.setBounds(40, 187, 67, 15);
			contentPanel.add(lblKamere);
		}
		{
			tfKamere = new JTextField();
			tfKamere.setBounds(142, 185, 633, 19);
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
			btnAddKamera.setBounds(783, 182, 132, 25);
			contentPanel.add(btnAddKamera);
		}
		{
			listKamere = new JList<>();
			modelKamere = new DefaultListModel<>();
			modelKamere.addAll(render.getKamere());
			listKamere.setModel(modelKamere);
			listKamere.setBounds(142, 214, 633, 81);
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
			btnRemoveKamera.setBounds(783, 214, 135, 25);
			contentPanel.add(btnRemoveKamera);
		}
		{
			JLabel lblSvetlo = new JLabel("Svetlo");
			lblSvetlo.setBounds(40, 304, 67, 15);
			contentPanel.add(lblSvetlo);
		}
		{
			tfSvetlo = new JTextField();
			tfSvetlo.setText(render.getSvetlo());
			tfSvetlo.setBounds(142, 302, 633, 19);
			contentPanel.add(tfSvetlo);
			tfSvetlo.setColumns(10);
		}
		{
			JLabel lblObjekti = new JLabel("Objekti");
			lblObjekti.setBounds(40, 333, 67, 15);
			contentPanel.add(lblObjekti);
		}
		{
			tfObjekti = new JTextField();
			tfObjekti.setBounds(142, 331, 633, 19);
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
			btnAddObjekat.setBounds(783, 328, 133, 25);
			contentPanel.add(btnAddObjekat);
		}
		{
			listObjekti = new JList<>();
			modelObjekti = new DefaultListModel<>();
			modelObjekti.addAll(render.getObjekti());
			listObjekti.setModel(modelObjekti);
			listObjekti.setBounds(142, 360, 633, 81);
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
			btnRemoveObjekat.setBounds(783, 360, 136, 25);
			contentPanel.add(btnRemoveObjekat);
		}
		{
			lblError = new JLabel("Popunite sva polja!");
			lblError.setBounds(390, 448, 136, 15);
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
						if (!somethingEmpty) {
							render.setMaterijali(Collections.list(modelMaterijali.elements()));
							render.setKamere(Collections.list(modelKamere.elements()));
							render.setObjekti(Collections.list(modelObjekti.elements()));
							render.setSvetlo(tfSvetlo.getText());
							RenderCrud.updateRender(render);
							main.refresh(thisDialog);
						} else {
							lblError.setVisible(somethingEmpty);
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
		somethingEmpty = modelMaterijali.isEmpty() || modelKamere.isEmpty()
				|| modelObjekti.isEmpty() || tfSvetlo.getText() == null || tfSvetlo.getText().isBlank();

	}

}
