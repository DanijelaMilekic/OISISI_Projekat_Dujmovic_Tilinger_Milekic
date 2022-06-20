package gui.softver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Cetkica;
import model.Render;

public class JEditSoftver extends JDialog {

	private static final long serialVersionUID = 78604694571929511L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfFajlFormat;
	private JTextField tfAnimationTool;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JEditSoftver dialog = new JEditSoftver();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JEditSoftver() {
		setTitle("Izmena podataka softvera");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dimension.width * 1/4, dimension.height * 1/4, dimension.width * 1/2, dimension.height * 1/2);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCetkice = new JLabel("Cetkice");
			lblCetkice.setBounds(24, 102, 52, 15);
			contentPanel.add(lblCetkice);
		}
		{
			JList<Cetkica> listCetkice = new JList<Cetkica>();
			listCetkice.setBounds(191, 34, 616, 151);
			contentPanel.add(listCetkice);
		}
		{
			JLabel lblFajlFormat = new JLabel("Fajl format");
			lblFajlFormat.setBounds(24, 194, 76, 15);
			contentPanel.add(lblFajlFormat);
		}
		{
			tfFajlFormat = new JTextField();
			tfFajlFormat.setBounds(191, 192, 114, 19);
			contentPanel.add(tfFajlFormat);
			tfFajlFormat.setColumns(10);
		}
		{
			JLabel lblAnimationsTool = new JLabel("Alati za animaciju");
			lblAnimationsTool.setBounds(24, 223, 124, 15);
			contentPanel.add(lblAnimationsTool);
		}
		{
			tfAnimationTool = new JTextField();
			tfAnimationTool.setBounds(191, 221, 114, 19);
			contentPanel.add(tfAnimationTool);
			tfAnimationTool.setColumns(10);
		}
		{
			JButton btnAddTool = new JButton("Dodaj alat");
			btnAddTool.setBounds(826, 218, 107, 25);
			contentPanel.add(btnAddTool);
		}
		{
			JList<String> listTools = new JList<>();
			listTools.setBounds(191, 250, 616, 160);
			listTools.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			contentPanel.add(listTools);
		}
		{
			JButton btnRemoveTool = new JButton("Ukloni alat");
			btnRemoveTool.setBounds(826, 250, 110, 25);
			contentPanel.add(btnRemoveTool);
		}
		{
			JLabel lblRender = new JLabel("Render");
			lblRender.setBounds(24, 421, 51, 15);
			contentPanel.add(lblRender);
		}
		{
			JComboBox<Render> cbRender = new JComboBox<>();
			cbRender.setBounds(191, 417, 616, 24);
			contentPanel.add(cbRender);
		}
		{
			JLabel lblSvePopuni = new JLabel("Popunite sva polja!");
			lblSvePopuni.setBounds(191, 448, 635, 15);
			lblSvePopuni.setVisible(false);
			lblSvePopuni.setForeground(Color.RED);
			lblSvePopuni.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblSvePopuni);
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
