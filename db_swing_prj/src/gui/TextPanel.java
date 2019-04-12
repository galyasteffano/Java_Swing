package gui;
import javax.swing.JPanel;

import java.awt.*;

import javax.swing.*;

public class TextPanel extends JPanel {
	private JTextArea _textArea;
	public TextPanel() {
		_textArea=new JTextArea();
		setLayout(new BorderLayout());
		//add(_textArea,BorderLayout.CENTER);
		add(new JScrollPane(_textArea),BorderLayout.CENTER);
	}
	public void appendText(String text) {
		_textArea.append(text);
	}
}
