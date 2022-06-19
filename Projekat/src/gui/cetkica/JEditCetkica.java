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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class JEditCetkica extends JDialog {

	private static final long serialVersionUID = -7224389510539177690L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfNamena;
	private JDialog thisDialog;
	private Color selectedColor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JEditCetkica dialog = new JEditCetkica();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JEditCetkica() {
		setTitle("Izmena podataka cetkice");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(dimension.width * 3/8, dimension.height * 3/8, dimension.width * 1/4, dimension.height * 1/4);
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
				selectedColor = JColorChooser.showDialog(thisDialog, "Izaberite boju", Color.ORANGE);
			}
		});
		contentPanel.add(btnBoja);
		{
			JLabel lblError = new JLabel("Popunite sva poolja");
			lblError.setBounds(122, 120, 140, 15);
			lblError.setVisible(false);
			{
				JButton btnNapraviBoju = new JButton("Napravi boju");
				btnNapraviBoju.setBounds(259, 88, 123, 25);
				btnNapraviBoju.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						final JColorChooser colorChooser = new JColorChooser();  
		                JDialog dialog = JColorChooser.createDialog(thisDialog, "Chane TextArea color", false, colorChooser,  
		                    new ActionListener() {  
		                        @Override  
		                        public void actionPerformed(ActionEvent event) {  
		                        	selectedColor = colorChooser.getColor();
		                        }  
		                    }, new ActionListener() {  
		                        @Override  
		                        public void actionPerformed(ActionEvent event) { 
		                        	setVisible(false);
		                        }  
		                    });  
		                dialog.setVisible(true);
					}
				});
				contentPanel.add(btnNapraviBoju);
			}
			lblError.setForeground(Color.RED);
			contentPanel.add(lblError);
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
