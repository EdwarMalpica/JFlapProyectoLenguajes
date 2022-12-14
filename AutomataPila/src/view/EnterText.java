package view;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class EnterText  extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField text;
	private JLabel mensaje;
	private boolean hayTexto;
	public EnterText() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setOpaque(false);
		hayTexto = false;
		text = new JTextField("");
		text.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if (text.getText().equals("")) {
					hayTexto=false;
				}else {
					hayTexto=true;
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		text.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				switch (text.getText()) {
				case "":
					mensaje.setText("el texto debe CONTENER caracteres");
					mensaje.setForeground(Color.RED);
					break;
				case "[a-zA-Z0-9]+":
					mensaje.setText("el texto debe CONTENER Uno o más caracteres alfanuméricos");
					break;
				default:
					mensaje.setText("");
					hayTexto=true;
					break;
				}
			}

			@Override
			public void focusGained(FocusEvent e) {

			}
		});

		mensaje = new JLabel();
		mensaje.setHorizontalTextPosition(JLabel.CENTER);
		mensaje.setVerticalTextPosition(JLabel.BOTTOM);

		add(text);
		add(mensaje);
	}

	public String getText(){
		return text.getText();
	}
	
	public void setText(String textIn) {
		text.setText(textIn);
	}
	
	
	public boolean isHayTexto() {
		return hayTexto;
	}
}
