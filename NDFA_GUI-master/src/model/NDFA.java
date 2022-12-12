package model;

import java.util.ArrayList;

import utils.StateType;

public class NDFA {
	
	private ArrayList<State> states;
	private ArrayList<Transition> transitions;
	
	public NDFA() {
		states=new ArrayList<State>();
		transitions=new ArrayList<Transition>();
	}
	
	public void addState(String name, StateType type) {
		states.add(new State(name,type));
	}
	
	public void deleteState(State state) {
		states.remove(state);
	}
	
	public void addTransition(char transitionValue, State initialState, State finalState) {
		transitions.add(new Transition(transitionValue,initialState,finalState));
	}
	
	public void deleteTransition(Transition transition) {
		transitions.remove(transition);
	}

	
	public boolean validateWord(String word) {
		State initialState = new State();
		for (int i = 0; i < states.size(); i++) {
			if (states.get(i).getType().equals(StateType.INITIAL)) {
				initialState = states.get(i);
			}
		}
		ArrayList<State>finalStates = new ArrayList<>();
		for (int i = 0; i < states.size(); i++) {
			if (states.get(i).getType().equals(StateType.FINAL)) {
				finalStates.add(states.get(i));
			}
		}
		
		ArrayList<State> parcialStates = new ArrayList<>();
		parcialStates.add(initialState);
		
		for (int i = 0; i < word.length(); i++) {
			ArrayList<State> st = new ArrayList<>();
			for (int j = 0; j < parcialStates.size(); j++) {
				for (int k = 0; k < transitions.size(); k++) {
					if (transitions.get(k).getInitialState().getName().equals(parcialStates.get(j).getName()) && 
						transitions.get(k).getTransitionValue() == (word.charAt(i))) {
						st.add(transitions.get(k).getFinalState());
					}
				}
			}
			parcialStates = st;
		}
		
		for (int i = 0; i < finalStates.size(); i++) {
			if (parcialStates.contains(finalStates.get(i))) {
				return true;
			}
		}
		
		return false;
	}


	public ArrayList<Transition> searchTransitions(State state){
		ArrayList<Transition> stateTransitions = new ArrayList<Transition>();
		for(int i = 0; i < this.transitions.size(); i++){
			if(transitions.get(i).getInitialState().equals(state) == true){
				stateTransitions.add(transitions.get(i));
			}
		}
		return stateTransitions;
	}

	public int getStateIndex(String stateName){
		int index = 0;
		for(int i = 0; i < states.size() ;i++){
			if(states.get(i).getName().compareTo(stateName) == 0){
				index = i;
			}
		}
		return index;
	}
	
	public ArrayList<State> getStates(){
		return this.states;
	}

	public ArrayList<Transition> gTransitions(){
		return this.transitions;
	}
	
	public String generateFormalism() {
		String formalism = "";
		formalism += "A1 ? { \n";
		formalism += "	Q = {";
		for (int i = 0; i < states.size(); i++) {
			formalism += i != states.size()-1 ?  states.get(i).getName() + "," : states.get(i).getName() + "} \n";
		}
		ArrayList<Character>symbols = new ArrayList<>();
		for (int i = 0; i < transitions.size(); i++) {
			if (!symbols.contains(transitions.get(i).getTransitionValue())) {
				symbols.add(transitions.get(i).getTransitionValue());
			}
		}
		formalism += "	E = {";
		for (int i = 0; i < symbols.size(); i++) {
			formalism += i != symbols.size()-1 ? symbols.get(i)+ "," : symbols.get(i)+ "} \n";
		}
		formalism += "	d = { \n";
		for (int i = 0; i < transitions.size(); i++) {
			formalism += "		" + transitions.get(i) + (i != transitions.size()-1 ? ", \n" : " \n");
		}
		formalism += "	}\n";
		for (int i = 0; i < states.size(); i++) {
			if (states.get(i).getType() == StateType.INITIAL) {
				formalism += "q0 =" + states.get(i).getName() + "\n";
			}
		}
		formalism += "	qF = {";
		for (int i = 0; i < states.size(); i++) {
			if (states.get(i).getType() == StateType.FINAL) {
				formalism += i != states.size()-1 ? states.get(i).getName() + "," : states.get(i).getName() + "}";
			}
		}
		formalism += "}";
		
		return formalism;
	}
	
	public void reinit() {
		states = new ArrayList<>();
		transitions = new ArrayList<>();
	}
}
