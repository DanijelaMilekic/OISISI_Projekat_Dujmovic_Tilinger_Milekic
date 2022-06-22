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
		setBounds(dimension.width * 2 / 8, dimension.height * 2 / 8, dimension.width * 1 / 2, dimension.height * 1 / 2);
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
			String text = "<html> Studenti Animacije u inzenjerstvu<br> <br>  Ovaj projekat se ispostavio kao malo zahtevniji i izazovniji<br>  sto smo na nasu srecu, ili zalost,<br>  iskusili i na nasoj kozi.<br> Tokom rada smo nailazili na razne probleme, <br>koje smo koriscenjem nasih glavica i interneta resavale. <br>  Svaki problem koliki god bio problem, je i novo iskustvo, <br>  koje nas je sve vise priblizavalo veoma interesantnom svetu programiranja. <br>  Kada se sve sabere, nista nije moglo da nadmasi osecaj kad je sve proradilo kako treba. (I sad kad komitujemo) <br> Milekic Danijela AI15/2019<br> Dujmovic Lana AI16/2019<br> Tilinger Antonia AI43/2019<br>Hvala.";
			JLabel lblAbout = new JLabel(text);
			lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(lblAbout, BorderLayout.CENTER);
		}
	}

}
