package gui.softver;

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
import model.Softver;

public class JEditSoftver extends JDialog {

	private static final long serialVersionUID = 78604694571929511L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfFajlFormat;
	private JTextField tfAnimationTool;
	private JLabel lblError;
	private JDialog thisDialog = this;

	private JList<Cetkica> listCetkice;
	private DefaultListModel<Cetkica> modelCetkice;
	
	private JList<String> listTools;
	private DefaultListModel<String> modelTools;
	
	private JComboBox<Render> cbRender;
	
	private boolean somethingEmpty = false;
	
	/**
	 * Launch the application.
	 */
	/*	public static void main(String[] args) {
	try {
		JEditSoftver dialog = new JEditSoftver();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	} catch (Exception e) {
		e.
}*/

	/**
	 * Create the dialog.
	 */
	public JEditSoftver(Softver softver, Refreshable main) {
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
			listCetkice = new JList<>();
			modelCetkice = new DefaultListModel<>();
			modelCetkice.addAll(CetkicaCrud.getAllCetkice());
			listCetkice.setModel(modelCetkice);
			listCetkice.setSelectedIndices(CetkicaCrud.indices(softver.getCetkice()));
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
			tfFajlFormat.setText(softver.getFajlFormat());
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
			btnAddTool.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!modelTools.contains(tfAnimationTool.getText())) {
						modelTools.addElement(tfAnimationTool.getText());
					}
				}
			});
			btnAddTool.setBounds(826, 218, 107, 25);
			contentPanel.add(btnAddTool);
		}
		{
			listTools = new JList<String>();
			modelTools = new DefaultListModel<>();
			modelTools.addAll(softver.getAlatiZaAnimaciju());
			listTools.setModel(modelTools);
			listTools.setBounds(191, 250, 616, 160);
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
			btnRemoveTool.setBounds(826, 250, 110, 25);
			contentPanel.add(btnRemoveTool);
		}
		{
			JLabel lblRender = new JLabel("Render");
			lblRender.setBounds(24, 421, 51, 15);
			contentPanel.add(lblRender);
		}
		{
			cbRender = new JComboBox<>(RenderCrud.toArray(RenderCrud.getAllRenderi()));
			cbRender.setSelectedItem(softver.getRender());
//			cbRender = new JComboBox<>();
			cbRender.setBounds(191, 417, 616, 24);
			contentPanel.add(cbRender);
		}
		{
			lblError = new JLabel("Popunite sva polja!");
			lblError.setBounds(191, 448, 635, 15);
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
							softver.setAlatiZaAnimaciju(Collections.list(modelTools.elements()));
							softver.setCetkice(listCetkice.getSelectedValuesList());
							softver.setFajlFormat(tfFajlFormat.getText());
							softver.setRender((Render) cbRender.getSelectedItem());
							SoftverCrud.updateSoftver(softver);
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
		somethingEmpty = listCetkice.getSelectedValuesList().isEmpty() || tfFajlFormat.getText() == null
				|| tfFajlFormat.getText().isBlank() || modelTools.isEmpty() || cbRender.getSelectedIndex() == -1;
	}

}
