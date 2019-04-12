package gui;

import java.awt.*;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class PrefsDialog extends JDialog {
	private JButton okbtn;
	private JButton cancelbtn;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	private JTextField userField;
	private JPasswordField password;
	private PrefsListener _prefsListener;

	public PrefsDialog(JFrame frame_parent) {
		super(frame_parent, "Preferences", false);
		okbtn = new JButton("OK");
		cancelbtn = new JButton("Cancel");

		spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
		portSpinner = new JSpinner(spinnerModel);
		userField = new JTextField(10);
		password = new JPasswordField(10);
		// configure what symbols to show instead of dots for password field
		password.setEchoChar('*');

		layoutControls();

		okbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer port = (Integer) portSpinner.getValue();
				String user = userField.getText();
				char[] password_arr;
				password_arr = password.getPassword(); // returns arr of chars
				System.out.println(user + " " + new String(password_arr)); // wrap in string

				if (_prefsListener != null) {
					_prefsListener.prefsSet(user, new String(password_arr), port);
				}
				setVisible(false);
			}
		});

		cancelbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		setSize(330, 245);
		setLocationRelativeTo(frame_parent);

	}

	private void layoutControls() {
		
		JPanel controlsPanel= new JPanel();
		JPanel buttonsPanel=new JPanel();
		
		int space=15;
		Border space_border= BorderFactory.createEmptyBorder(space,space,space,space);
		Border title_border=BorderFactory.createTitledBorder("Database Preferences");
		
		
		controlsPanel.setBorder(BorderFactory.createCompoundBorder(space_border,title_border));
		
		buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		controlsPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridy = 0;
		Insets right_padding=new Insets(0, 0, 0, 15);
		Insets no_padding= new Insets(0,0,0,0);

		// first row user field
		//gc.gridwidth or height for span over multiple cells
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		gc.gridx = 0;
		gc.anchor=GridBagConstraints.EAST;
		gc.insets= right_padding;		//picels top lefr bottom right
		controlsPanel.add(new JLabel("User: "), gc);
		
		gc.gridx++;
		gc.anchor=GridBagConstraints.WEST;
		gc.insets=no_padding;
		controlsPanel.add(userField, gc);

		// next row passsword field
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		gc.gridx = 0;
		gc.anchor=GridBagConstraints.EAST;
		gc.insets=right_padding;
		controlsPanel.add(new JLabel("Password: "), gc);
		
		gc.gridx++;
		gc.insets=no_padding;
		gc.anchor=GridBagConstraints.WEST;
		controlsPanel.add(password, gc);

		// next row

		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		gc.gridx = 0;
		gc.anchor=GridBagConstraints.EAST;
		gc.insets=right_padding;
		controlsPanel.add(new JLabel("Port: "), gc);
		
		gc.gridx++;
		gc.insets=no_padding;
		gc.anchor=GridBagConstraints.WEST;
		controlsPanel.add(portSpinner, gc);

		// Buttons panel
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.add(okbtn);
		buttonsPanel.add(cancelbtn);
		
		Dimension btnSz= cancelbtn.getPreferredSize();
		okbtn.setPreferredSize(btnSz);
		
		//Add sub panels to dialog
		setLayout(new BorderLayout());
		add(controlsPanel,BorderLayout.CENTER);
		add(buttonsPanel,BorderLayout.SOUTH);
	}

	public void setPrefsListener(PrefsListener prefsListener) {
		_prefsListener = prefsListener;
	}

	public void setDefaults(String user, String pass, int port) {
		userField.setText(user);
		password.setText(pass);
		portSpinner.setValue(port);
	}
}
