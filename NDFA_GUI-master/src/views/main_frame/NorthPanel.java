package views.main_frame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Commands;

public class NorthPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton restartAutomaton, validateWord, formalism, autors;

	public NorthPanel(ActionListener listener) {
		restartAutomaton = new MenuButton("", Color.GRAY, Color.ORANGE);
		validateWord = new MenuButton("", Color.GRAY, Color.ORANGE);
		formalism = new MenuButton("", Color.GRAY, Color.ORANGE);
		autors = new MenuButton("", Color.GRAY, Color.ORANGE);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Color.LIGHT_GRAY);
		this.initComponents(listener);
		this.setVisible(true);
	}

	private void initComponents(ActionListener actionListener) {
		this.setLayout(new GridLayout(4,1));
		restartAutomaton.addActionListener(actionListener);
		restartAutomaton.setText("Reiniciar");
		restartAutomaton.setActionCommand(Commands.C_RESTART.toString());
		validateWord.addActionListener(actionListener);
		validateWord.setActionCommand(Commands.C_VALIDATE_WORD.toString());
		validateWord.setText("Ingresar palabra");
		formalism.setText("Formalismo");
		formalism.addActionListener(actionListener);
		formalism.setActionCommand(Commands.C_FORMALISM.toString());
		autors.addActionListener(actionListener);
		autors.setActionCommand(Commands.C_AUTORS.toString());
		autors.setText("Integrantes");
		this.add(validateWord);
		this.add(formalism);
		this.add(restartAutomaton);
		this.add(autors);
	}

}
