package gui;

public class AgeCat {
	private String _text;
	private int _id;

	public AgeCat(int id,String text) {
		_text=text;
		_id=id;
	}

	public String toString() {
		return _text;
	}

	public int getId() {
		return _id;
	}
}
