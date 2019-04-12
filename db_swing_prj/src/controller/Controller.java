package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import gui.FormEvent; 
import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class Controller {
	Database db =new Database();
	
	public List<Person> get_people(){
		return db.getPeople();				
	}
	public void removePerson(int index) {
		db.removePerson(index);
	}
	
	public void addPerson(FormEvent ev) {
		String name=ev.getName();
		String occupation=ev.getOccupation();
		int ageCatID=ev.getAgeCategory();
		String empCat=ev.getEmploymentCategory();
		boolean isCitizen=ev.isUSCitizen();
		String taxID=ev.getTaxId();
		String gender=ev.getGender();  
		AgeCategory ageCategory=null;
		EmploymentCategory employmentCat;
		Gender genderEnum; 
		
		switch(ageCatID) {
		case 0:
			ageCategory=AgeCategory.child;
			break;
		case 1:
			ageCategory=AgeCategory.adult;
			break;
		case 2:
			ageCategory=AgeCategory.senior;
			break;
		}
		
		if(empCat.equals("employed")) {
			employmentCat=EmploymentCategory.employed;
		}
		else if(empCat.equals("unemployed")) {
			employmentCat=EmploymentCategory.unemployed;
		}
		else if(empCat.equals("self-employed")) {
			employmentCat=EmploymentCategory.selfEmployed;
		}
		else {
			employmentCat=EmploymentCategory.other;
			System.err.print(empCat);
		}
		
		if(gender.equals("female")) {
			genderEnum=Gender.female;
		}else {
			genderEnum=Gender.female;
		}
		
		Person person= new Person(name,occupation,ageCategory,employmentCat,taxID,isCitizen,genderEnum);
		db.addPerson(person);
		
	}
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}
	public void save() throws SQLException {
		db.save();
	}
	public void disconnect() {
		db.disconnect();
	}
	public void connect() throws Exception {
		db.connect();
	}
	public void load() throws SQLException {
		db.load();
	}
}
