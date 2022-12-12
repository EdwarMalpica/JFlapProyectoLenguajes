package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.NDFA;
import utils.StateType;

import javax.swing.JOptionPane;

import views.main_frame.MainFrame;

public class ControlAutomata implements ActionListener {

	private static final String VALIDATION_FAILED = "La palabra no pertenece a lenguaje generado por el automata";
	private static final String VALIDATION_SUCESS = "La palabra pertenece a lenguaje generado por el automata";
	private MainFrame frame; // instancia de la vista, frame principal
	private NDFA finiteAutomata = new NDFA();

	public ControlAutomata() {
		frame = new MainFrame(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Commands.valueOf(e.getActionCommand())) {
		case C_RESTART:
			frame.restartAutomaton();
			finiteAutomata.reinit();
			break;
		case C_VALIDATE_WORD:
			this.validateWord();
			break;
		case C_FORMALISM:
			this.generateFormalism();
			break;
		case C_AUTORS:
			this.getAutors();
			break;
		default:
			break;

		}

	}

	public void validateWord() {
		String name = JOptionPane.showInputDialog("Ingresa la palabra a validar");
		if (JOptionPane.showConfirmDialog(frame, "¿Seguro que desea validar esta palabra: " 
				+ name + "?", "Pregunta", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			// llamar al metodo de validacion del modelo y enviar la palabra guardada en
			// name
			boolean validation = finiteAutomata.validateWord(name);
			if (validation) {
				this.frame.showMessage(VALIDATION_SUCESS);
			} else {
				this.frame.showMessage(VALIDATION_FAILED);
			}

		}
	}
	
	public void generateFormalism() {
		this.frame.showMessage("El formalismo generado es :\n" + finiteAutomata.generateFormalism());
	}
	
	public void getAutors() {
		this.frame.showMessage("Integrantes de grupo :\n Andres Amaya\n Harrison Diaz\n Martin Chiquillo\n David Fajardo\n Luis Castillo" );
	}

	public void addState(String stateTag) {
		finiteAutomata.addState(stateTag, StateType.DEFAULT);
	}

	public void updateState(String stateTag, StateType type) {
		finiteAutomata.getStates().get(finiteAutomata.getStateIndex(stateTag)).setType(type);
	}

	public void addTransition(String startTag, String endTag, char condition) {
		finiteAutomata.addTransition(condition, finiteAutomata.getStates().get(finiteAutomata.getStateIndex(startTag)),
				finiteAutomata.getStates().get(finiteAutomata.getStateIndex(endTag)));
	}
}
