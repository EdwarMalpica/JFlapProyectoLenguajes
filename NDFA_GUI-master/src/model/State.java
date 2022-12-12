package model;

import utils.StateType;

public class State {
	private String name;
	private StateType type;
	
	public State() {
		this.name = "";
		this.type = StateType.DEFAULT;
	}
	
	public State(String name, StateType type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public StateType getType() {
		return type;
	}
	
	public void setType(StateType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "NOMBRE " + name + "TIPO" + type;
	}
	
	
}
