package gui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Person;

public class PersonTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<Person> _db;
	private String[] col_names = { "ID", "NAME", "OCCUPATION", "AGE CATEGORY",
			"EMPLOYMENT CATEGORY", "US CITIZEN", "TAX ID" };

	public PersonTableModel() {
	}

	@Override
	public String getColumnName(int column) {
		return col_names[column];
	}

	@Override
	public int getColumnCount() {

		return 7;
	}

	@Override
	public int getRowCount() {
		return _db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Person person = _db.get(row);
		switch (col) {
		case 0:
			return person.getID();
		case 1:
			return person.getName();
		case 2:
			return person.getOccupation();
		case 3:
			return person.get_ageCategory();
		case 4:
			return person.get_empCategory();
		case 5:
			return person.is_usCitizen();
		case 6:
			return person.get_taxID();
		}
		return null;
	}

	public void set_data(List<Person> db) {
		_db = db;
	}

}
