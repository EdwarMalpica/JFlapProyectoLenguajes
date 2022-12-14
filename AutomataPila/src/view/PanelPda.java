package view;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class PanelPda extends JPanel {
	private JTextField word;
	private JButton btnBack, btnValidate;
	private JTextArea pdaInfo;

	public PanelPda(ActionListener l) {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		initComponents();
		setCommands(l);
	}

	private void setCommands(ActionListener l) {
		this.btnValidate.addActionListener(l);
		this.btnValidate.setActionCommand("VALIDAR");

		this.btnBack.addActionListener(l);
		this.btnBack.setActionCommand("VOLVER");
	}

	public void changeInfo(String info){
		pdaInfo.setText(info);
	}

	private void initComponents( ) {



		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(Color.WHITE);
		panelCenter.setLayout(new BorderLayout());
		panelCenter.setBorder(BorderFactory.createEmptyBorder(10,40,10,40));

		JLabel labelTitle=new JLabel("AUTOMATA DE PILA",SwingConstants.CENTER);
		labelTitle.setFont(new Font("ARIAL", Font.BOLD,30));
		labelTitle.setHorizontalTextPosition(JLabel.CENTER);
		panelCenter.add(labelTitle,BorderLayout.PAGE_START);

		this.pdaInfo = new JTextArea("");
		pdaInfo.setEditable(false);
		JScrollPane scroll = new JScrollPane(pdaInfo);
		scroll.setBackground(Color.WHITE);
		scroll.setBorder(BorderFactory.createTitledBorder("Informacion del automata de pila"));
		panelCenter.add(scroll,BorderLayout.CENTER);


		this.word=new JTextField();
		word.setBorder(BorderFactory.createTitledBorder("Ingrese una plabra para validar"));
		panelCenter.add(word,BorderLayout.PAGE_END);

		add(panelCenter,BorderLayout.CENTER);

		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(Color.WHITE);
		panelButtons.setBorder(BorderFactory.createEmptyBorder(0,40,10,40));
		panelButtons.setLayout(new GridLayout(1,2));
		this.btnBack=new JButton("VOLVER");
		this.btnValidate=new JButton("VALIDAR");
		panelButtons.add(btnValidate);
		panelButtons.add(btnBack);
		add(panelButtons,BorderLayout.PAGE_END);
	}

	public String getWord(){
		return word.getText();
	}
	public void clean(){
		pdaInfo.setText("");
		word.setText("");
	}
}


