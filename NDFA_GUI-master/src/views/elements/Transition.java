package views.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import views.main_frame.GraphicsFA;

public class Transition {
	public String condicion;

	public StateElement start;
	public StateElement end;
	private int index = 0;

	public void drawTransition(Graphics g) {
		g.setColor(Color.BLACK);
		if (end != null && start != null)
			g.drawLine(start.x + GraphicsFA.CIRCLE_RADIUS / 2, start.y + GraphicsFA.CIRCLE_RADIUS / 2,
					end.x + GraphicsFA.CIRCLE_RADIUS / 2, end.y + GraphicsFA.CIRCLE_RADIUS / 2);
	}

	public void drawCondition(Graphics g) {
		Graphics2D gr = (Graphics2D) g;
		if (!condicion.isEmpty()) {
			gr.drawString(condicion, (start.x + end.x) / 2 + GraphicsFA.CIRCLE_RADIUS / 2,
					((start.y + end.y) / 2 + GraphicsFA.CIRCLE_RADIUS / 2) - (index * 15));
		} else {
			gr.drawString("λ", (start.x + end.x) / 2 + GraphicsFA.CIRCLE_RADIUS / 2,
					((start.y + end.y) / 2 + GraphicsFA.CIRCLE_RADIUS / 2) - (index * 15));
		}
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
