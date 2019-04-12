package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.*;

public class FormPanel extends JPanel {
	private JLabel _nameLbl;
	private JLabel _occupationLbl;
	private JTextField _nameFld;
	private JTextField _occupationFld;
	private JButton _okBtn;
	private FormListener _formListener;
	private JList _ageList;
	private JComboBox _employmentCbox;
	private JCheckBox _citizenCheckBox;
	private JTextField _taxFld;
	private JLabel _taxLbl;

	private JRadioButton _mRadioBtn;
	private JRadioButton _fRadioBtn;
	private ButtonGroup _genderGroup;

	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);

		_nameLbl = new JLabel("Name: ");
		_occupationLbl = new JLabel("Occupation: ");
		_taxLbl = new JLabel("Tax ID: ");
		_nameFld = new JTextField(10);
		_occupationFld = new JTextField(10);
		_taxFld = new JTextField(10);
		_ageList = new JList();
		_employmentCbox = new JComboBox();
		_citizenCheckBox = new JCheckBox();
		_okBtn = new JButton("OK");
		_mRadioBtn = new JRadioButton("male");
		_fRadioBtn = new JRadioButton("female");
		_genderGroup = new ButtonGroup();

		_fRadioBtn.setSelected(true);
		_mRadioBtn.setActionCommand("male");
		_fRadioBtn.setActionCommand("female");
		_genderGroup.add(_mRadioBtn);
		_genderGroup.add(_fRadioBtn);

		_taxLbl.setEnabled(false);
		_taxFld.setEnabled(false);

		_citizenCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// check if field is selected
				boolean isChecked = _citizenCheckBox.isSelected();
				_taxLbl.setEnabled(isChecked);
				_taxFld.setEnabled(isChecked);
			}
		});

		// setup list box
		DefaultListModel age_model = new DefaultListModel();
		age_model.addElement(new AgeCat(0, "Under 18"));
		age_model.addElement(new AgeCat(1, "18 to 65"));
		age_model.addElement(new AgeCat(2, "65 or over"));
		_ageList.setModel(age_model);

		_ageList.setPreferredSize(new Dimension(110, 68));
		_ageList.setBorder(BorderFactory.createEtchedBorder());

		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("employed");
		empModel.addElement("self-employed");
		empModel.addElement("unemployed");
		_employmentCbox.setModel(empModel);
		_employmentCbox.setEditable(true); // make combo box editable

		_okBtn.setMnemonic(KeyEvent.VK_O);
		_nameLbl.setDisplayedMnemonic(KeyEvent.VK_N);
		_nameLbl.setLabelFor(_nameFld);
		_ageList.setSelectedIndex(1);
		_okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = _nameFld.getText();
				String occupation = _occupationFld.getText();
				AgeCat age_cat = (AgeCat) _ageList.getSelectedValue();
				String empCat = (String) _employmentCbox.getSelectedItem();
				String taxFld = _taxFld.getText();
				boolean usCitizen = _citizenCheckBox.isSelected();
				String genderCmd = _genderGroup.getSelection().getActionCommand();

				FormEvent ev = new FormEvent(this, name, occupation, age_cat.getId(), empCat,
						taxFld, usCitizen, genderCmd);
				if (_formListener != null) {
					_formListener.formEventOccured(ev);
				} else {
				}
				;
			}
		});
		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		layoutComponents();
	}

	public void layoutComponents() {

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		/* First Row---->>> Name Field + Label */
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.gridy = 0;
		gc.gridx = 0; // top left corner
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(_nameLbl, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(_nameFld, gc);

		/* Next Row -->>>Occupation */
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(_occupationLbl, gc);

		gc.gridx = 1;
		gc.weightx = 1;
		gc.weighty = 0.1;
		// gc.gridy=1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 5);
		add(_occupationFld, gc);

		/* Next Row--->>>Age */
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Age: "), gc);

		gc.gridx = 1;
		// gc.gridy=2;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(_ageList, gc);

		/* Next Row-->>>Employment Combo Box */

		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.2;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Emplpoyment: "), gc);
		// gc.gridy=3;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(_employmentCbox, gc);

		/* Next Row -->>Check box */
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.3;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("US Citizen: "), gc);
		// gc.gridy=3;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(_citizenCheckBox, gc);

		/* Next Row -->> Tax Field */
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.4;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(_taxLbl, gc);
		// gc.gridy=3;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(_taxFld, gc);

		/* Next Row---->>> Radio Buttons */
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.05;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Gender:  "), gc);
		// gc.gridy=3;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(_mRadioBtn, gc);

		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.6;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);

		add(_fRadioBtn, gc);

		/* Last Row --->>>OK button */
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 2.0;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(_okBtn, gc);
	}

	public void setFormListener(FormListener listener) {
		this._formListener = listener;
	}

}
