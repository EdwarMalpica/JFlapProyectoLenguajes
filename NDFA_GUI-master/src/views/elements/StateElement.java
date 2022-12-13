package views.elements;

import java.awt.Color;
import java.awt.Graphics;

import utils.StateType;
import views.main_frame.GraphicsFA;

public class StateElement {
	public int x = 50, y = 50;
	public String tag;
	private int size = GraphicsFA.CIRCLE_RADIUS;
	private StateType type = StateType.DEFAULT;

	public StateElement() {

	}

	public StateElement(int x, int y, String tag) {
		this.x = x;
		this.y = y;
		this.tag = tag;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return this.y;
	}

	public void setType(StateType type) {
		this.type = type;
	}

	public StateType getType() {
		return this.type;
	}

	public void paintState(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillOval(this.x, this.y, size, size);
		g.setColor(Color.BLACK);
		g.drawOval(this.x, this.y, size, size);
		if (type == StateType.FINAL) {
			g.drawOval(this.x + 3, this.y + 3, size - 6, size - 6);
		} else if (type == StateType.INITIAL) {
			drawTriangle(g);
		}
		g.setColor(Color.WHITE);
		g.drawString(tag, x + size / 2 - 7, y + size / 2 + 5);
	}

	private void drawTriangle(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(this.x - 25, this.y, this.x - 25, this.y + this.size);
		g.drawLine(this.x - 25, this.y, this.x, this.y + this.size / 2);
		g.drawLine(this.x - 25, this.y + this.size, this.x, this.y + this.size / 2);
	}
}
