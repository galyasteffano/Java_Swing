package gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

public class Toolbar extends JPanel implements ActionListener{
	private JButton _saveBtn;
	private JButton _refreshBtn;
	private ToolbarListener _txtListener;
	public Toolbar() {
		_saveBtn=new JButton("Save");
		_refreshBtn=new JButton("Refresh");
		_saveBtn.addActionListener(this);
		_refreshBtn.addActionListener(this);
		
		setBorder(BorderFactory.createEtchedBorder());
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(_saveBtn);
		add(_refreshBtn);
	}
	public void setToolbarListener(ToolbarListener listener) {
		_txtListener=listener;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clicked=(JButton)e.getSource();
		if(clicked==_saveBtn) {
			if(_txtListener!=null) {
				try {
					_txtListener.saveEventOccured();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if(clicked==_refreshBtn) {
			if(_txtListener!=null) {
				
					_txtListener.refreshEvent();
			
			}
		}
	}
}
