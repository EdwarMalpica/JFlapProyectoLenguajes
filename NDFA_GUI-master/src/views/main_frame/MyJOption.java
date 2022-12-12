package views.main_frame;

import javax.swing.JOptionPane;

public class MyJOption {
	
	public MyJOption() {
		// TODO Auto-generated constructor stub
	}
	
	public int myMenu() {
		String[] opciones = {"Inicial","Final"};
		int data = JOptionPane.showOptionDialog(null, "Seleciona una opción","Opcion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
	return data;
	}
	
	public String myWord(String text) {
		String dataWord = JOptionPane.showInputDialog(null,text);
	return String.valueOf(dataWord.charAt(0));
	}

}
