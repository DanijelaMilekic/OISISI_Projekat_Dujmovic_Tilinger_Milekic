package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class JAbout extends JDialog {

	private static final long serialVersionUID = 5168468801078029238L;

	private JDialog thisDialog = this;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { try { JAbout dialog = new JAbout();
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * Create the dialog.
	 */
	public JAbout() {
		setTitle("About us");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dimension.width * 3 / 8, dimension.height * 3 / 8, dimension.width * 1 / 4, dimension.height * 1 / 4);
		getContentPane().setLayout(new BorderLayout());

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnClose = new JButton("Close");
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						thisDialog.dispose();
					}
				});
				btnClose.setActionCommand("Cancel");
				buttonPane.add(btnClose);
			}
		}
		{
			String text = "<html>Studenti Animacije u inzenjerstvu <br> Milekic Danijela <br> Dujmovic Lana <br> Tilinger Antonia<br>Hvala.";
			JLabel lblAbout = new JLabel(text);
			lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(lblAbout, BorderLayout.CENTER);
		}
	}

}
