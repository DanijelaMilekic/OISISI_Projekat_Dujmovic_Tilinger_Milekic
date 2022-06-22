package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import crud.BootCrud;
import crud.CetkicaCrud;
import crud.RenderCrud;
import crud.SoftverCrud;
import crud.ZaposleniCrud;
import gui.cetkica.CetkicaTable;
import gui.cetkica.JCreateCetkica;
import gui.cetkica.JDeleteCetkica;
import gui.cetkica.JEditCetkica;
import gui.render.JCreateRender;
import gui.render.JDeleteRender;
import gui.render.JEditRender;
import gui.render.RenderiTable;
import gui.softver.JCreateSoftver;
import gui.softver.JDeleteSoftver;
import gui.softver.JEditSoftver;
import gui.softver.SoftveriTable;
import gui.zaposleni.JCreateZaposleni;
import gui.zaposleni.JDeleteZaposleni;
import gui.zaposleni.JEditZaposleni;
import gui.zaposleni.ZaposleniTable;
import util.Formating;

public class JMain implements Refreshable {

	private JFrame frame;
	
	private JTabbedPane tabbedPane;
	
	private JTable tableZaposleni;
	private ZaposleniTable modelZaposleni;
	private JTable tableSoftveri;
	private SoftveriTable modelSoftveri;
	private JTable tableRenderi;
	private RenderiTable modelRenderi;
	private JTable tableCetkice;
	private CetkicaTable modelCetkica;
	
	private ActionListener createActionListener;
	private ActionListener editActionListener;
	private ActionListener deleteActionListener;
	
	private Refreshable thisRefreshable = this;
	
	private DefaultTableCellRenderer dCellRenderer;

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
	@SuppressWarnings("serial")
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
		
		createActionListener = new ActionListener() {
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
		};
		
		editActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog;
				JTable jTable = (JTable) tabbedPane.getSelectedComponent();
				
				int index = jTable.getSelectedRow();
				if (index == -1) {
					return;
				}
				TableModel table = (jTable).getModel();
				if (table instanceof ZaposleniTable) {
					dialog = new JEditZaposleni(modelZaposleni.getRowValue(index), thisRefreshable);
				} else if (table instanceof SoftveriTable) {
					dialog = new JEditSoftver(modelSoftveri.getRowValue(index), thisRefreshable);
				} else if (table instanceof CetkicaTable) {
					dialog = new JEditCetkica(modelCetkica.getRowValue(index), thisRefreshable);
				} else {
					dialog = new JEditRender(modelRenderi.getRowValue(index), thisRefreshable);
				}
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		};
		
		deleteActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog;
				JTable jTable = (JTable) tabbedPane.getSelectedComponent();
				
				int index = jTable.getSelectedRow();
				if (index == -1) {
					return;
				}
				TableModel table = (jTable).getModel();
				if (table instanceof ZaposleniTable) {
					dialog = new JDeleteZaposleni(modelZaposleni.getRowValue(index), thisRefreshable);
				} else if (table instanceof SoftveriTable) {
					dialog = new JDeleteSoftver(modelSoftveri.getRowValue(index), thisRefreshable);
				} else if (table instanceof CetkicaTable) {
					dialog = new JDeleteCetkica(modelCetkica.getRowValue(index), thisRefreshable);
				} else {
					dialog = new JDeleteRender(modelRenderi.getRowValue(index), thisRefreshable);
				}
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		};
		
		JPanel panel = new JPanel();
		toolBar.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton createEntityButton = new JButton("");
		panel.add(createEntityButton);
		createEntityButton.setIcon(new ImageIcon("icons/add_box_icon.png"));
		createEntityButton.addActionListener(createActionListener); 
		createEntityButton.setBorder(null);
		
		JButton editEntityButton = new JButton("");
		panel.add(editEntityButton);
		editEntityButton.setIcon(new ImageIcon("icons/edit_pencil_write_icon.png"));
		editEntityButton.addActionListener(editActionListener);
		editEntityButton.setBorder(null);
		
		JButton deleteEntityButton = new JButton("");
		panel.add(deleteEntityButton);
		deleteEntityButton.setIcon(new ImageIcon("icons/bin_delete_garbage_rubbish_trash_icon.png"));
		deleteEntityButton.addActionListener(deleteActionListener);
		deleteEntityButton.setBorder(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.SOUTH);
		layeredPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblToday = new JLabel(Formating.formatDate(new Date())); 
		lblToday.setHorizontalAlignment(SwingConstants.RIGHT);
		layeredPane.add(lblToday, BorderLayout.EAST);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		tableZaposleni = new JTable();
		modelZaposleni = new ZaposleniTable(ZaposleniCrud.getAllZaposlenis());
		tableZaposleni.setModel(modelZaposleni);
		tableZaposleni.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabbedPane.addTab("Zaposleni", null, tableZaposleni, null);
		
		tableSoftveri = new JTable();
		modelSoftveri = new SoftveriTable(SoftverCrud.getAllSoftveri());
		tableSoftveri.setModel(modelSoftveri);
		tableSoftveri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabbedPane.addTab("Softveri", null, tableSoftveri, null);
		
		tableRenderi = new JTable();
		modelRenderi = new RenderiTable(RenderCrud.getAllRenderi());
		tableRenderi.setModel(modelRenderi);
		tableSoftveri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabbedPane.addTab("Renderi", null, tableRenderi, null);
		
		tableCetkice = new JTable();
		modelCetkica = new CetkicaTable(CetkicaCrud.getAllCetkice());
		tableCetkice.setModel(modelCetkica);
		dCellRenderer = new DefaultTableCellRenderer() {
			@Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(modelCetkica.getColor(row));
                return c;
            }
        };
		tableCetkice.getColumnModel().getColumn(2).setCellRenderer(dCellRenderer);
		tableCetkice.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabbedPane.addTab("Cetkice", null, tableCetkice, null);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
//		fileMenu.setIcon(new ImageIcon("/Paint/352004_add_box_icon.png"));
		menuBar.add(fileMenu);
		
		JMenuItem newFileMenuItem = new JMenuItem("New");
		newFileMenuItem.setIcon(new ImageIcon("icons/add_box_icon.png"));
		newFileMenuItem.addActionListener(createActionListener);
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
		exitFileMenuItem.setIcon(new ImageIcon("icons/bin_delete_garbage_rubbish_trash_icon.png"));
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
		editEditMenuItem.addActionListener(editActionListener);
		editMenu.add(editEditMenuItem);
		
		JMenuItem deleteEditMenuItem = new JMenuItem("Delete");
		deleteEditMenuItem.addActionListener(deleteActionListener);
		editMenu.add(deleteEditMenuItem);
		
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem aboutHelpMenuItem = new JMenuItem("About");
		aboutHelpMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JAbout();
				dialog.setVisible(true);
			}
		});
		helpMenu.add(aboutHelpMenuItem);
		
		
		
	}
	
	@Override
	public void refresh(JDialog dialog) {
		modelCetkica = new CetkicaTable(CetkicaCrud.getAllCetkice());
		tableCetkice.setModel(modelCetkica); 
		tableCetkice.getColumnModel().getColumn(2).setCellRenderer(dCellRenderer);
		modelRenderi = new RenderiTable(RenderCrud.getAllRenderi());
		tableRenderi.setModel(modelRenderi);
		modelSoftveri = new SoftveriTable(SoftverCrud.getAllSoftveri());
		tableSoftveri.setModel(modelSoftveri);
		modelZaposleni = new ZaposleniTable(ZaposleniCrud.getAllZaposlenis());
		tableZaposleni.setModel(modelZaposleni);

		dialog.setVisible(false);
		dialog.dispose();
	}
}
