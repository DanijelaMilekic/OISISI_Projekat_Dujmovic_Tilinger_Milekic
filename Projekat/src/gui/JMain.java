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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.TableModel;

import crud.BootCrud;
import crud.CetkicaCrud;
import crud.RenderCrud;
import crud.SoftverCrud;
import crud.ZaposleniCrud;
import gui.cetkica.CetkicaTable;
import gui.cetkica.JCreateCetkica;
import gui.render.JCreateRender;
import gui.render.RenderiTable;
import gui.softver.JCreateSoftver;
import gui.softver.SoftveriTable;
import gui.zaposleni.JCreateZaposleni;
import gui.zaposleni.ZaposleniTable;
import util.Formating;

public class JMain implements Refreshable {

	private JFrame frame;
	private JTable tableZaposleni;
	private JTable tableSoftveri;
	private JTable tableRenderi;
	private JTable tableCetkice;
	private Refreshable thisRefreshable = this;

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
		frame.setSize(dimension.width * 3/4, dimension.height * 3/4);
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
		
		tableZaposleni = new JTable();
		tableZaposleni.setModel(new ZaposleniTable(ZaposleniCrud.getAllZaposlenis()));
		tableZaposleni.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabbedPane.addTab("Zaposleni", null, tableZaposleni, null);
		
		tableSoftveri = new JTable();
		tableSoftveri.setModel(new SoftveriTable(SoftverCrud.getAllSoftveri()));
		tableSoftveri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabbedPane.addTab("Softveri", null, tableSoftveri, null);
		
		tableRenderi = new JTable();
		tableRenderi.setModel(new RenderiTable(RenderCrud.getAllRenderi()));
		tableSoftveri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabbedPane.addTab("Renderi", null, tableRenderi, null);
		
		tableCetkice = new JTable();
		tableCetkice.setModel(new CetkicaTable(CetkicaCrud.getAllCetkice()));
		tableCetkice.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabbedPane.addTab("Cetkice", null, tableCetkice, null);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
//		fileMenu.setIcon(new ImageIcon("/Paint/352004_add_box_icon.png"));
		menuBar.add(fileMenu);
		
		JMenuItem newFileMenuItem = new JMenuItem("New");
		newFileMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog;
				TableModel table = ((JTable) tabbedPane.getSelectedComponent()).getModel();
				if (table instanceof ZaposleniTable) {
					dialog = new JCreateZaposleni(thisRefreshable);
				} else if (table instanceof SoftveriTable) {
					dialog = new JCreateSoftver(thisRefreshable);
				} else if (table instanceof CetkicaTable) {
					dialog = new JCreateCetkica(thisRefreshable);
				} else {
					dialog = new JCreateRender(thisRefreshable);
				}
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		newFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		fileMenu.add(newFileMenuItem);
		
		JMenu openFileSubmenu = new JMenu("Open");
		fileMenu.add(openFileSubmenu);
		
		JMenuItem openZaposleniMenuItem = new JMenuItem("Zaposleni");
		openZaposleniMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedComponent(tableZaposleni);
			}
		});
		openFileSubmenu.add(openZaposleniMenuItem);
		
		JMenuItem openSoftverMenuItem = new JMenuItem("Softver");
		openSoftverMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedComponent(tableSoftveri);
			} 
		});
		openFileSubmenu.add(openSoftverMenuItem); 
		
		JMenuItem openRenderMenuItem = new JMenuItem("Render"); 
		openRenderMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedComponent(tableRenderi);
			}
		});
		openFileSubmenu.add(openRenderMenuItem); 
		
		JMenuItem openCetkicaMenuItem = new JMenuItem("Cetkica");
		openCetkicaMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedComponent(tableCetkice);
			}
		});
		openFileSubmenu.add(openCetkicaMenuItem);
		
		
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
	
	@Override
	public void refresh(JDialog dialog) {
		tableZaposleni.setModel(new ZaposleniTable(ZaposleniCrud.getAllZaposlenis()));	
		tableSoftveri.setModel(new SoftveriTable(SoftverCrud.getAllSoftveri()));
		tableRenderi.setModel(new RenderiTable(RenderCrud.getAllRenderi()));
		tableCetkice.setModel(new CetkicaTable(CetkicaCrud.getAllCetkice()));
		dialog.setVisible(false);
		dialog.dispose();
	}
}
