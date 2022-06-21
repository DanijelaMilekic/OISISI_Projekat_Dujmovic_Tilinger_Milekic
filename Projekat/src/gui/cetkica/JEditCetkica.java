package gui.cetkica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import model.Cetkica;

public class JEditCetkica extends JDialog {

	private static final long serialVersionUID = -7224389510539177690L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNamena;
	private JDialog thisDialog = this;
	private Color selectedColor;
	private JLabel lblError;
	
	private boolean somethingEmpty = false;
	private JTextField tfColor;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { try { JEditCetkica dialog = new
	 * JEditCetkica(); dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * Create the dialog.
	 */
	public JEditCetkica(Cetkica cetkica, Refreshable main) {
		selectedColor = cetkica.getBoja();
		setTitle("Izmena podataka cetkice");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dimension.width * 3 / 8, dimension.height * 3 / 8, dimension.width * 1 / 4, dimension.height * 1 / 4);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNamena = new JLabel("Namena");
			lblNamena.setBounds(40, 64, 58, 15);
			contentPanel.add(lblNamena);
		}
		{
			tfNamena = new JTextField();
			tfNamena.setText(cetkica.getNamena());
			tfNamena.setBounds(133, 62, 114, 19);
			contentPanel.add(tfNamena);
			tfNamena.setColumns(10);
		}

		JLabel lblBoja = new JLabel("Boja");
		lblBoja.setBounds(40, 93, 58, 15);
		contentPanel.add(lblBoja);

		JButton btnBoja = new JButton("Izaberi boju");
		btnBoja.setBounds(133, 88, 118, 25);
		btnBoja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedColor = JColorChooser.showDialog(thisDialog, "Izaberite boju", cetkica.getBoja());
				tfColor.setBackground(selectedColor);
			}
		});
		contentPanel.add(btnBoja);
		{
			lblError = new JLabel("Popunite sva poolja");
			lblError.setBounds(122, 120, 140, 15);
			lblError.setVisible(false);
			lblError.setForeground(Color.RED);
			contentPanel.add(lblError);
		}
		{
			tfColor = new JTextField();
			tfColor.setEnabled(false);
			tfColor.setBackground(selectedColor);
			tfColor.setBounds(280, 91, 114, 19);
			contentPanel.add(tfColor);
			tfColor.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						validateIput();
						if (!somethingEmpty) {
							cetkica.setNamena(tfNamena.getText());
							cetkica.setBoja(selectedColor);
							CetkicaCrud.updateCetkica(cetkica);
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

	private void validateIput() {
		somethingEmpty = tfNamena.getText() == null || tfNamena.getText().isBlank();
	}
}