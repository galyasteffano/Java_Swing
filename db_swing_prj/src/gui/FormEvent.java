package gui;
import java.util.EventObject;

public class FormEvent extends EventObject {

	private String _name;
	private String _occupation;
	private int _ageCategory;
	private String _empCat;
	private String _taxID;
	private boolean _usCitizen;
	private String _gender;

	public FormEvent(Object source) {
		super(source);
	}

	public FormEvent(Object source, String name, String occupation, int ageCat, String empCat,String taxID,
			boolean usCitizen, String gender) {
		super(source);
		_name = name;
		_occupation = occupation;
		_ageCategory = ageCat;
		_empCat = empCat;
		_taxID=taxID;
		_usCitizen=usCitizen;
		_gender=gender;
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
		occupation = _occupation;
	}

	public int getAgeCategory() {
		return _ageCategory;
	}

	public String getEmploymentCategory() {
		return _empCat;
	}
	public String getTaxId() {
		return _taxID;
	}
	public boolean isUSCitizen() {
		return _usCitizen;
	}
	public String getGender() {
		return _gender;
	}
}
