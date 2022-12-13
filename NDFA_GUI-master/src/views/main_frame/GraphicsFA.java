package views.main_frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import controller.ControlAutomata;
import utils.StateType;
import views.elements.StateElement;
import views.elements.Transition;

public class GraphicsFA extends JPanel {
	public int width, height;
	public static int CIRCLE_RADIUS = 50;
	private StateElement se = new StateElement();
	private ArrayList<StateElement> states = new ArrayList<StateElement>();
	private ArrayList<Transition> transitions = new ArrayList<Transition>();
	private boolean creatingTransition = false;

	public GraphicsFA(int width, int height, ControlAutomata control) {

		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.WHITE);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				boolean isOverlapping = false;
				StateElement selected = null;

				int counter = 0;

				while (!isOverlapping && counter < states.size()) {
					if (dist(states.get(counter).x + CIRCLE_RADIUS / 2, states.get(counter).y + CIRCLE_RADIUS / 2,
							e.getX(), e.getY()) < CIRCLE_RADIUS) {
						selected = states.get(counter);
						isOverlapping = true;
					}
					counter++;
				}
				if (!isOverlapping) {

					control.addState("q" + states.size());
					states.add(new StateElement(e.getX() - CIRCLE_RADIUS / 2, e.getY() - CIRCLE_RADIUS / 2,
							"q" + states.size()));
					for (StateElement state : states) {
						drawState(state.x, state.y);
					}

				} else if (!creatingTransition) {
					if (e.getButton() == 1) {
						Transition t = new Transition();
						t.start = selected;
						creatingTransition = true;
						transitions.add(t);
					}
					if (e.getButton() == 3) {
						MyJOption o = new MyJOption();
						int option = o.myMenu();
						if (option == 0) {
							boolean initialSetted = false;
							for (StateElement s : states) {
								if (s.getType() == StateType.INITIAL) {
									initialSetted = true;
								}
							}
							if (!initialSetted)
								selected.setType(StateType.INITIAL);
							control.updateState(selected.tag, StateType.INITIAL);
						} else if (option == 1) {
							selected.setType(StateType.FINAL);
							control.updateState(selected.tag, StateType.FINAL);
						}
					}

				} else if (creatingTransition) {
					Transition aux = transitions.get(transitions.size() - 1);
					transitions.get(transitions.size() - 1).end = selected;
					MyJOption o = new MyJOption();
					transitions.get(transitions.size() - 1).condicion = o.myWord("Ingresa una condicion");
					creatingTransition = false;
					int index = 0;
					for (Transition t : transitions) {
						if (aux.start == t.start && aux.end == t.end && aux.condicion != t.condicion) {
							index++;
						}
					}
					transitions.get(transitions.size() - 1).setIndex(index);
					control.addTransition(aux.start.tag, aux.end.tag, aux.condicion.charAt(0));
				}
				drawState();
			}
		});

	}

	private void drawState(int x, int y) {
		final int CURR_X = se.getX();
		final int CURR_Y = se.getY();
		final int OFFSET = 1;

		if ((CURR_X != x) || (CURR_Y != y)) {

			repaint(CURR_X, CURR_Y, CIRCLE_RADIUS, CIRCLE_RADIUS);

			se.setX(x);
			se.setY(y);

			repaint(se.getX(), se.getY(), CIRCLE_RADIUS + OFFSET, CIRCLE_RADIUS + OFFSET);
		}
	}

	private void drawState() {
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints hints = new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		hints.put(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(hints);
		for (Transition t : transitions) {
			t.drawTransition(g2);
			if (t.condicion != null) {
				t.drawCondition(g2);
			}
		}
		for (StateElement state : states) {
			state.paintState(g2);
		}
	}

	public void restartAutomaton() {
		states.removeAll(states);
		transitions.removeAll(transitions);
		this.drawState();
	}

	private double dist(float x1, float y1, float x2, float y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
}
