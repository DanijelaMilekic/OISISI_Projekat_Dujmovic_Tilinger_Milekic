package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;


import crud.BootCrud;
import util.Formating;

public class JMain {
	
	private JFrame frame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BootCrud.bootCrud();
					JMain window = new JMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Naslov");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(dimension.width * 2/4, dimension.height * 2/4);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		
		JButton createEntityButton = new JButton("");
		toolBar.add(createEntityButton);
		
		JButton editEntityButton = new JButton("");
		toolBar.add(editEntityButton);
		
		JButton deleteEntityButton = new JButton("");
		toolBar.add(deleteEntityButton);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.SOUTH);
		layeredPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblToday = new JLabel(Formating.formatDate(new Date())); 
		lblToday.setHorizontalAlignment(SwingConstants.RIGHT);
		layeredPane.add(lblToday, BorderLayout.EAST);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		tabbedPane.addTab(null, tabbedPane)
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
//		fileMenu.setIcon(new ImageIcon("/Paint/352004_add_box_icon.png"));
		menuBar.add(fileMenu);
		
		JMenuItem newFileMenuItem = new JMenuItem("New");
		fileMenu.add(newFileMenuItem);
		
		JMenu openFileSubmenu = new JMenu("Open");
		fileMenu.add(openFileSubmenu);
		
		
		
		
		JMenuItem exitFileMenuItem = new JMenuItem("Exit");
		exitFileMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		exitFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		fileMenu.add(exitFileMenuItem);
		
		
		
		
		JMenu editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		
		JMenuItem editEditMenuItem = new JMenuItem("Edit");
		editMenu.add(editEditMenuItem);
		
		JMenuItem deleteEditMenuItem = new JMenuItem("Delete");
		editMenu.add(deleteEditMenuItem);
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem aboutHelpMenuItem = new JMenuItem("About");
		helpMenu.add(aboutHelpMenuItem);
		
		
		
	}

}
