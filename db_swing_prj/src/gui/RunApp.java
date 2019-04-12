package gui;
import javax.swing.SwingUtilities;

public class RunApp {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//only for multi threading will prevent crashing??
		SwingUtilities.invokeLater(new Runnable() {
			int bug2=1;
			public void run() {
				new MainFrame();
			}
			int bug=1;
		});
	
	}
}
