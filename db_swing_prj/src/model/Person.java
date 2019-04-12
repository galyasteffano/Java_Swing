package model;

import java.io.Serializable;

// stores people while the app is running
public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int count = 1;
	private int _id;
	private String _name;
	private String _occupation;
	private AgeCategory _ageCategory;
	private EmploymentCategory _empCategory;
	private String _taxID;
	private boolean _usCitizen;
	private Gender _gender;

	public Person(String name, String occupation, AgeCategory ageCat, EmploymentCategory empCat,
			String taxId, boolean usCitizen, Gender gender) {
		_name = name;
		_occupation = occupation;
		_ageCategory = ageCat;
		_empCategory = empCat;
		_taxID = taxId;
		_usCitizen = usCitizen;
		_gender = gender;
		_id = count;
		count++;
	}

	public Person(int id, String name, String occupation, AgeCategory ageCat,
			EmploymentCategory empCat, String taxId, boolean usCitizen, Gender gender) {
		this(name, occupation, ageCat, empCat, taxId, usCitizen, gender);
		_id = id;
	}

	public int getID() {
		return _id;
	}

	public void setID(int id) {
		_id = id;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getOccupation() {
		return _occupation;
	}

	public void setOccupation(String occupation) {
		_occupation = occupation;
	}

	public AgeCategory get_ageCategory() {
		return _ageCategory;
	}

	public void setAgeCat(AgeCategory ageCategory) {
		_ageCategory = ageCategory;
	}

	public EmploymentCategory get_empCategory() {
		return _empCategory;
	}

	public void set_empCategory(EmploymentCategory empCategory) {
		this._empCategory = empCategory;
	}

	public String get_taxID() {
		return _taxID;
	}

	public void set_taxID(String taxID) {
		this._taxID = taxID;
	}

	public boolean is_usCitizen() {
		return _usCitizen;
	}

	public void set_usCitizen(boolean usCitizen) {
		_usCitizen = usCitizen;
	}

	public Gender get_gender() {
		return _gender;
	}

	public void set_gender(Gender gender) {
		_gender = gender;
	}

}
