package gui.softver;

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
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import crud.CetkicaCrud;
import crud.RenderCrud;
import crud.SoftverCrud;
import gui.Refreshable;
import model.Cetkica;
import model.Render;

public class JCreateSoftver extends JDialog {

	private static final long serialVersionUID = -3941633504685531351L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNaziv;
	private JTextField tfFajlFormat;
	private JTextField tfAnimationTool;
	private JLabel lblError;
	private JLabel lblErrorNaziv;
	private JDialog thisDialog = this;

	private JList<Cetkica> listCetkice;
	private DefaultListModel<Cetkica> modelCetkice;

	private JList<String> listTools;
	private DefaultListModel<String> modelTools;

	private JComboBox<Render> cbRender;

	private boolean somethingEmpty = false;
	private boolean notUnique = false;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { try { JCreateSoftver dialog = new
	 * JCreateSoftver(); dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * Create the dialog.
	 */
	public JCreateSoftver(Refreshable main) {
		setTitle("Kreiranje novog softvera");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(800, 600);		
		setLocationRelativeTo(null);
//		setBounds(dimension.width * 1 / 4, dimension.height * 1 / 4, dimension.width * 1 / 2, dimension.height * 1 / 2);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNaziv = new JLabel("Naziv");
			lblNaziv.setBounds(24, 40, 38, 15);
			lblNaziv.setHorizontalAlignment(SwingConstants.LEFT);
			contentPanel.add(lblNaziv);
		}
		{
			tfNaziv = new JTextField();
			tfNaziv.setBounds(191, 38, 114, 19);
			contentPanel.add(tfNaziv);
			tfNaziv.setColumns(10);
		}
		{
			lblErrorNaziv = new JLabel("Vec postoji softver sa takvim nazivom!");
			lblErrorNaziv.setBounds(377, 40, 270, 15);
			lblErrorNaziv.setVisible(false);
			lblErrorNaziv.setForeground(Color.RED);
			contentPanel.add(lblErrorNaziv);
		}
		{
			JLabel lblCetkice = new JLabel("Cetkice");
			lblCetkice.setBounds(24, 124, 52, 15);
			contentPanel.add(lblCetkice);
		}
		{
			listCetkice = new JList<>();
			modelCetkice = new DefaultListModel<>();
			modelCetkice.addAll(CetkicaCrud.getAllCetkice());
			listCetkice.setModel(modelCetkice);
			listCetkice.setBounds(191, 64, 456, 136);
			contentPanel.add(listCetkice);
		}
		{
			JLabel lblFajlFormat = new JLabel("Fajl format");
			lblFajlFormat.setBounds(24, 209, 76, 15);
			contentPanel.add(lblFajlFormat);
		}
		{
			tfFajlFormat = new JTextField();
			tfFajlFormat.setBounds(191, 207, 114, 19);
			contentPanel.add(tfFajlFormat);
			tfFajlFormat.setColumns(10);
		}
		{
			JLabel lblAnimationsTool = new JLabel("Alati za animaciju");
			lblAnimationsTool.setBounds(24, 238, 124, 15);
			contentPanel.add(lblAnimationsTool);
		}
		{
			tfAnimationTool = new JTextField();
			tfAnimationTool.setBounds(191, 236, 114, 19);
			contentPanel.add(tfAnimationTool);
			tfAnimationTool.setColumns(10);
		}
		{
			JButton btnAddTool = new JButton("Dodaj alat");
			btnAddTool.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!modelTools.contains(tfAnimationTool.getText())) {
						modelTools.addElement(tfAnimationTool.getText());
					}
				}
			});
			btnAddTool.setBounds(666, 233, 107, 25);
			contentPanel.add(btnAddTool);
		}
		{
			listTools = new JList<String>();
			modelTools = new DefaultListModel<>();
			listTools.setModel(modelTools);
			listTools.setBounds(191, 265, 456, 145);
			listTools.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			contentPanel.add(listTools);
		}
		{
			JButton btnRemoveTool = new JButton("Ukloni alat");
			btnRemoveTool.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modelTools.removeElement(listTools.getSelectedValue());
				}
			});
			btnRemoveTool.setBounds(666, 265, 110, 25);
			contentPanel.add(btnRemoveTool);
		}
		{
			JLabel lblRender = new JLabel("Render");
			lblRender.setBounds(24, 421, 51, 15);
			contentPanel.add(lblRender);
		}
		{

			cbRender = new JComboBox<>(RenderCrud.toArray(RenderCrud.getAllRenderi()));
//			cbRender = new JComboBox<>();
			cbRender.setBounds(191, 417, 456, 24);
			contentPanel.add(cbRender);
		}
		{
			lblError = new JLabel("Popunite sva polja!");
			lblError.setBounds(191, 448, 475, 15);
			lblError.setVisible(false);
			lblError.setForeground(Color.RED);
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
							SoftverCrud.createSoftver(tfNaziv.getText(), listCetkice.getSelectedValuesList(),
									tfFajlFormat.getText(), Collections.list(modelTools.elements()),
									(Render) cbRender.getSelectedItem());
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
				|| (notUnique = SoftverCrud.getSoftverByID(tfNaziv.getText()) != null)
				|| listCetkice.getSelectedValuesList().isEmpty() || tfFajlFormat.getText() == null
				|| tfFajlFormat.getText().isBlank() || modelTools.isEmpty() || cbRender.getSelectedIndex() == -1;
	}

}