package gui;
import java.io.File;

import javax.swing.filechooser.FileFilter;

public class PersonFileFilter extends FileFilter{

	@Override
	public boolean accept(File file) {
		String name=file.getName();
		
		if(file.isDirectory()) {
			return true;
		}
		//check extension of the file
		String ext=Utils.getFileExtension(name);
		
		if(ext==null) {
			return false;
		}
		if(ext.equals("per")) {
			return true;
		}
		return false;
	}


	@Override
	public String getDescription() {
		return "Person database files (*.per)";
	}

}
