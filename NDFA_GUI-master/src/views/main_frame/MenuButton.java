package views.main_frame;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class MenuButton extends JButton {
	
	public MenuButton(String text, Color colorBackground, Color colorForeground) {
		super(text);
		setBackground(colorBackground);
		setForeground(colorForeground);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Font font = new Font("Plain", Font.PLAIN, 15);
		setFont(font);
	}

}
