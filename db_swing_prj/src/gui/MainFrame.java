package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.*;

import controller.Controller;

public class MainFrame extends JFrame {
	private TextPanel _txtPanel;
	private JButton _btn;
	private Toolbar _toolbar;
	private FormPanel _formPanel;
	private JFileChooser _filePicker;
	private Controller _controller;
	private TablePanel _tablePanel;
	private PrefsDialog _prefsDialog;
	private Preferences _prefs;

	public MainFrame() {
		super("Hello World");

		setMinimumSize(new Dimension(500, 400));
		setSize(650, 650);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null); // centers the window
		_txtPanel = new TextPanel();
		_toolbar = new Toolbar();
		_formPanel = new FormPanel();
		_filePicker = new JFileChooser();
		_controller = new Controller();
		_tablePanel = new TablePanel();
		_prefsDialog = new PrefsDialog(this);
		_prefs = Preferences.userRoot().node("db");

		_tablePanel.set_data(_controller.get_people());

		_tablePanel.setPersonTableListener(new PersonTableListener() {
			public void rowDeleted(int row) {
				_controller.removePerson(row);
			}
		});

		_prefsDialog.setPrefsListener(new PrefsListener() {
			@Override
			public void prefsSet(String user, String pass, int port) {
				_prefs.put("user", user);
				_prefs.put("password", pass);
				_prefs.putInt("port", port);
			}
		});
		String user = _prefs.get("user", "");
		String pass = _prefs.get("password", "");
		Integer port = _prefs.getInt("port", 3306);
		_prefsDialog.setDefaults(user, pass, port);

		setJMenuBar(createMenuBar());
		_filePicker.addChoosableFileFilter(new PersonFileFilter());

		_toolbar.setToolbarListener(new ToolbarListener() {

			public void saveEventOccured() throws Exception {
				connect();
				try {
					_controller.save();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Cannot save to database",
							"Database save problem", JOptionPane.ERROR_MESSAGE);
				}
			}

			public void refreshEvent() {
				try {
					connect();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					_controller.load();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Cannot load  database",
							"Load  problem", JOptionPane.ERROR_MESSAGE);
				}
				// _controller.load();
				_tablePanel.refresh();
			}

		});

		_formPanel.setFormListener(new FormListener() {
			public void formEventOccured(FormEvent e) {
				_controller.addPerson(e); // connecting to data model
				_tablePanel.refresh();
			}
		});

		add(_toolbar, BorderLayout.NORTH);
		add(_formPanel, BorderLayout.WEST);
		add(_tablePanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public void connect() throws Exception {
		try {
			_controller.connect();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(MainFrame.this, "Cannot connect to database!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenuItem prefs = new JMenuItem("Preferences...");

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem exportData = new JMenuItem("Export Data..");
		JMenuItem importData = new JMenuItem("Import Data..");
		JMenuItem exit = new JMenuItem("Exit");

		fileMenu.add(exportData);
		fileMenu.add(importData);
		fileMenu.addSeparator();
		fileMenu.add(exit);

		JMenu showMenu = new JMenu("Show");
		JCheckBoxMenuItem showForm = new JCheckBoxMenuItem("Show menu");
		showForm.setSelected(true);

		showMenu.add(showForm);

		JMenu windowMenu = new JMenu("Window");
		menuBar.add(windowMenu);
		windowMenu.add(showMenu);
		windowMenu.add(prefs);

		prefs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_prefsDialog.setVisible(true);
			}

		});

		showForm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();

				_formPanel.setVisible(menuItem.isSelected());
			}
		});

		fileMenu.setMnemonic(KeyEvent.VK_F);
		exit.setMnemonic(KeyEvent.VK_X);
		prefs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		importData.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));

		exportData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (_filePicker.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {

					try {
						_controller.saveToFile(_filePicker.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this,
								"Could not save data to file!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		importData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (_filePicker.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {

					try {
						_controller.loadFromFile(_filePicker.getSelectedFile());
						_tablePanel.refresh();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Loading data failed!",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int action;
				action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Do you realy want to exit the app?", "Confirm Exit",
						JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}

			}
		});
		return menuBar;
	}
}
