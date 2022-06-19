package gui.cetkica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



import crud.CetkicaCrud;
import gui.Refreshable;

public class JCreateCetkica extends JDialog {

	private static final long serialVersionUID = 9040713049020031322L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNaziv;
	private JTextField tfNamena;
	private JDialog thisDialog = this;
	private Color selectedColor;
	private JLabel lblError;
	private JLabel lblErrorNaziv;
	
	private boolean somethingEmpty = false;
	private boolean notUnique = false;


	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { try { JCreateCetkica dialog = new
	 * JCreateCetkica(); dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * Create the dialog.
	 */
	public JCreateCetkica(Refreshable main) {
		setTitle("Kreiranje nove cetkice");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dimension.width * 3 / 9, dimension.height * 3 / 9, dimension.width * 3 / 9, dimension.height * 3 / 9);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNaziv = new JLabel("Naziv");
			lblNaziv.setBounds(40, 54, 38, 15);
			contentPanel.add(lblNaziv);
		}
		{
			tfNaziv = new JTextField();
			tfNaziv.setBounds(133, 52, 114, 19);
			contentPanel.add(tfNaziv);
			tfNaziv.setColumns(10);
		}
		{
			lblErrorNaziv = new JLabel("Cetkica sa tim imenom vec postoji!");
			lblErrorNaziv.setBounds(338, 54, 243, 15);
			lblErrorNaziv.setVisible(false);
			lblErrorNaziv.setForeground(Color.RED);
			contentPanel.add(lblErrorNaziv);
		}
		{
			JLabel lblNamena = new JLabel("Namena");
			lblNamena.setBounds(40, 80, 58, 15);
			contentPanel.add(lblNamena);
		}
		{
			tfNamena = new JTextField();
			tfNamena.setBounds(133, 78, 114, 19);
			contentPanel.add(tfNamena);
			tfNamena.setColumns(10);
		}

		JLabel lblBoja = new JLabel("Boja");
		lblBoja.setBounds(40, 109, 58, 15);
		contentPanel.add(lblBoja);

		JButton btnBoja = new JButton("Izaberi boju");
		btnBoja.setBounds(133, 104, 118, 25);
		btnBoja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedColor = JColorChooser.showDialog(thisDialog, "Izaberite boju", Color.ORANGE);
			}
		});
		contentPanel.add(btnBoja);
		{
			lblError = new JLabel("Popunite sva polja!");
			lblError.setBounds(148, 136, 140, 15);
			lblError.setVisible(false);
			lblError.setForeground(Color.RED);
			contentPanel.add(lblError);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout((LayoutManager) new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						validateIput();
						if (!somethingEmpty) {
							CetkicaCrud.createCetkica(tfNaziv.getText(), tfNamena.getText(), selectedColor);
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
	
	private void validateIput() {
		notUnique = false;
		somethingEmpty = tfNaziv.getText() == null || tfNaziv.getText().isBlank() || (notUnique = CetkicaCrud.getCetkicaByID(tfNaziv.getText()) != null) || tfNamena.getText() == null
				|| tfNamena.getText().isBlank();
	}

}
