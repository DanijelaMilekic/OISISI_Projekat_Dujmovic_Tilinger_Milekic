package gui.zaposleni;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class JDeleteZaposleni extends JDialog {

	private static final long serialVersionUID = 5692044581367356584L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JDeleteZaposleni dialog = new JDeleteZaposleni();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JDeleteZaposleni() {
		setTitle("Brisanje zaposlenog");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dimension.width * 3/8, dimension.height * 3/8, dimension.width * 1/4, dimension.height * 1/4);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblUpozorenje = new JLabel("Da li zaista zelite da izbrisete zaposlenog?");
			lblUpozorenje.setFont(new Font("Dialog", Font.BOLD, 16));
			lblUpozorenje.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblUpozorenje);
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
