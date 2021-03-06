package gui.render;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import crud.RenderCrud;
import gui.Refreshable;
import model.Render;

public class JDeleteRender extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JDialog thisDialog = this;

	
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			JDeleteRender dialog = new JDeleteRender();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public JDeleteRender(Render render, Refreshable main) {
		setTitle("Brisanje rendera");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dimension.width * 3/8, dimension.height * 3/8, dimension.width * 1/4, dimension.height * 1/4);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblDaLiZaista = new JLabel("Da li zaista zelite da izbrisete render?");
			lblDaLiZaista.setFont(new Font("Dialog", Font.BOLD, 16));
			lblDaLiZaista.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblDaLiZaista, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RenderCrud.deleteRender(render);
						main.refresh(thisDialog);
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

}
