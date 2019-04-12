package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Person;

public class TablePanel extends JPanel{
	private JTable _table;
	private PersonTableModel _tableModel;
	private JPopupMenu _popup;
	private PersonTableListener _personTableListener;
	
	public TablePanel() {
		_tableModel= new PersonTableModel();
		_table= new JTable(_tableModel);
		_popup=new JPopupMenu();
		
		JMenuItem remove_item=new JMenuItem("Delete row");
		_popup.add(remove_item);
		
		_table.addMouseListener(new MouseAdapter() {

			@Override
			
			public void mousePressed(MouseEvent ev) {
				int row=_table.rowAtPoint(ev.getPoint());
				_table.getSelectionModel().setSelectionInterval(row,row);
				System.out.print(row);
				if(ev.getButton()==MouseEvent.BUTTON3) {
					_popup.show(_table,ev.getX(),ev.getY());
				}
			}
			
		});
		remove_item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row=_table.getSelectedRow();
				if(_personTableListener!=null) {
					_personTableListener.rowDeleted(row);
					_tableModel.fireTableRowsDeleted(row, row);
				}
			//	System.out.println(row);
			}
			
		});
		
		setLayout(new BorderLayout());
		add((new JScrollPane(_table)),BorderLayout.CENTER);
	}
	public void set_data(List<Person> db) {
		_tableModel.set_data(db);
	}
	public void refresh() {
		_tableModel.fireTableDataChanged();
	}
	public void setPersonTableListener(PersonTableListener listener) {
		this._personTableListener=listener;
	}
}
