package db_connect;

import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class TestDBConnection {
	public static void main(String[] args) throws Exception {
		Database db= new Database();
		db.connect();
		db.addPerson(new Person("Galya", "Developer", AgeCategory.adult,
				EmploymentCategory.employed, "W123", true, Gender.female));
		db.load();
		db.save();
		db.disconnect();
	}
}
