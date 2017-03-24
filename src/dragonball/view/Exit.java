package dragonball.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import dragonball.controller.GameGUI;

public class Exit extends JFrame implements ActionListener{
	JButton exit;
	JButton stay;

	public Exit() {
		super();
		setLayout(new BorderLayout());
		setBounds(500, 300, 219, 100);
		setVisible(true);
		JLabel question = new JLabel("Are you sure you want to exit ?");
		add(question, BorderLayout.NORTH);
		JPanel panel = new JPanel(new FlowLayout());
		exit = new JButton("YES");
		exit.setActionCommand("yes");
		stay = new JButton("NO");
		stay.setActionCommand("no");
		panel.add(exit);
		panel.add(stay);
		add(panel, BorderLayout.SOUTH);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		stay.addActionListener(this);
		exit.addActionListener(this);
	}

	public static void main(String[] args) {
		Exit f = new Exit();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("yes")){
			System.exit(0);
		}else 
			this.dispose();
	}

}
