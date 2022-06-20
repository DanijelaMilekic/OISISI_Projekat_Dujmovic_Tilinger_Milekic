package gui.render;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class JEditRender extends JDialog {
	
	private static final long serialVersionUID = -4975791820967261865L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfMaterijali;
	private JTextField tfKamere;
	private JTextField tfSvetlo;
	private JTextField tfObjekti;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JEditRender dialog = new JEditRender();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JEditRender() {
		setTitle("Izmena podataka rendera");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dimension.width * 1/4, dimension.height * 1/4, dimension.width * 1/2, dimension.height * 1/2);
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
			btnAddMaterijal.setBounds(783, 62, 142, 25);
			contentPanel.add(btnAddMaterijal);
		}
		{
			JList<String> listMaterijali = new JList<>();
			listMaterijali.setBounds(142, 94, 633, 81);
			listMaterijali.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			contentPanel.add(listMaterijali);
		}
		{
			JButton btnRemoveMaterijal = new JButton("Ukloni materijal");
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
			btnAddKamera.setBounds(783, 182, 132, 25);
			contentPanel.add(btnAddKamera);
		}
		{
			JList<String> listKamere = new JList<>();
			listKamere.setBounds(142, 214, 633, 81);
			listKamere.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			contentPanel.add(listKamere);
		}
		{
			JButton btnRemoveKamera = new JButton("Ukloni kameru");
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
			btnAddObjekat.setBounds(783, 328, 133, 25);
			contentPanel.add(btnAddObjekat);
		}
		{
			JList<String> listObjekti = new JList<>();
			listObjekti.setBounds(142, 360, 633, 81);
			listObjekti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			contentPanel.add(listObjekti);
		}
		{
			JButton btnNewButton_5 = new JButton("Ukloni objekat");
			btnNewButton_5.setBounds(783, 360, 136, 25);
			contentPanel.add(btnNewButton_5);
		}
		{
			JLabel lblPopuniteSvaPolja = new JLabel("Popunite sva polja!");
			lblPopuniteSvaPolja.setBounds(390, 448, 136, 15);
			lblPopuniteSvaPolja.setVisible(false);
			lblPopuniteSvaPolja.setForeground(Color.RED);
			lblPopuniteSvaPolja.setHorizontalTextPosition(SwingConstants.CENTER);
			lblPopuniteSvaPolja.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblPopuniteSvaPolja);
		}
		
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
	}

}
