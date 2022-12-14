package view;

import model.PdaAutomatum;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelForm extends JPanel {

	private JLabel labelTitle;
	private JTextField initial,stackInitial, states, r, alphabet,finals;
	private JTextArea transactions;

	private JButton btn;

	public PanelForm(ActionListener l) {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		initComponents();
		setCommands(l);
	}

	private void initComponents(){
		
		this.labelTitle=new JLabel("AUTOMATA DE PILA",SwingConstants.CENTER);
		labelTitle.setFont(new Font("ARIAL", Font.BOLD,30));
		this.labelTitle.setHorizontalTextPosition(JLabel.CENTER);
		
		JPanel panelForum = new JPanel();
		panelForum.setBackground(Color.WHITE);
		panelForum.setBorder(BorderFactory.createEmptyBorder(10,40,10,40));
		panelForum.setLayout(new GridLayout(7,1));
		
		add(labelTitle,BorderLayout.PAGE_START);

		this.initial=new JTextField();
		initial.setBorder(BorderFactory.createTitledBorder("Estado inicial"));
		panelForum.add(initial);


		this.stackInitial=new JTextField();
		stackInitial.setBorder(BorderFactory.createTitledBorder("Simbolo inicial de la pila"));
		panelForum.add(stackInitial);

		this.states=new JTextField();
		states.setBorder(BorderFactory.createTitledBorder("Estados (Separados por espacios: [q1 q2 q3])"));
		panelForum.add(states);

		this.r=new JTextField();
		r.setBorder(BorderFactory.createTitledBorder("Simbolos de la pila (Separado por espacios: [X @ ?])"));
		panelForum.add(r);

		this.alphabet=new JTextField();
		alphabet.setBorder(BorderFactory.createTitledBorder("Alfabeto (Separado por espacios: [a b c])"));
		panelForum.add(alphabet);

		this.finals=new JTextField();
		finals.setBorder(BorderFactory.createTitledBorder("Estados finales (Separado por espacios: [qf qa])"));
		panelForum.add(finals);

		this.transactions=new JTextArea();
		JScrollPane scroll = new JScrollPane(transactions);
		scroll.setBackground(Color.WHITE);
		scroll.setBorder(BorderFactory.createTitledBorder("Transiciones (Separado por espacios y saltos de linea: [q1 a X ? qf])"));
		panelForum.add(scroll);
		
		add(panelForum,BorderLayout.CENTER);

		this.btn=new JButton("CREAR");
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(Color.WHITE);
		panelButtons.setBorder(BorderFactory.createEmptyBorder(0,40,20,40));
		panelButtons.setLayout(new BorderLayout());
		
		panelButtons.add(btn,BorderLayout.CENTER);
		add(panelButtons,BorderLayout.PAGE_END);
	}

	private void setCommands(ActionListener l) {
		btn.addActionListener(l);
		btn.setActionCommand("CREAR");
	}

	public PdaAutomatum createPda(){
		String i=this.initial.getText();
		String si=this.stackInitial.getText();
		String [] rtemp=this.r.getText().split(" ");
		ArrayList<String> rt=new ArrayList<>();
		for (int j = 0; j < rtemp.length; j++) {
			rt.add(rtemp[j]);
		}
		String [] stats=this.states.getText().split(" ");
		ArrayList<String> sts=new ArrayList<>();
		for (int j = 0; j < stats.length; j++) {
			sts.add(stats[j]);
		}
		String [] alp=this.alphabet.getText().split(" ");
		ArrayList<String> alpha=new ArrayList<>();
		for (int j = 0; j < alp.length; j++) {
			alpha.add(alp[j]);
		}
		String [] fs=this.finals.getText().split(" ");
		ArrayList<String> sfns=new ArrayList<>();
		for (int j = 0; j < fs.length; j++) {
			sfns.add(fs[j]);
		}
		String [] aux=this.transactions.getText().split("\n");
		ArrayList<String[]> tra=new ArrayList<>();
		for (int j = 0; j < aux.length; j++) {
			tra.add(aux[j].split(" "));
		}
		clean();
		return  new PdaAutomatum(sts, alpha, rt, sfns, tra, i, si);
	}

	public void clean(){
		initial.setText("");
		stackInitial.setText("");
		r.setText("");
		states.setText("");
		alphabet.setText("");
		finals.setText("");
		transactions.setText("");

	}

}
