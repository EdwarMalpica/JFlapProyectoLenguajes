package views.main_frame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.ControlAutomata;

import java.awt.*;

import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private GraphicsFA graphicsPanel;
	private NorthPanel menuBarr;

	public MainFrame(ActionListener actionListener) {
		this.graphicsPanel = new GraphicsFA(300, 300, (ControlAutomata) actionListener);
		this.menuBarr = new NorthPanel(actionListener);
		this.setLayout(new BorderLayout());
		this.setSize(800, 800);
		this.initComponents();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void initComponents() {
		this.add(graphicsPanel, BorderLayout.CENTER);
		this.add(menuBarr, BorderLayout.WEST);
	}

	public void restartAutomaton() {
		graphicsPanel.restartAutomaton();
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
}