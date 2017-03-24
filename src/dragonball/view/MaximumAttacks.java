package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MaximumAttacks extends JFrame {
	JLabel message;

	public MaximumAttacks() {
		super();
		setBounds(500, 200, 466, 365);
		setVisible(true);
		setTitle("Maximum Assigned Attack Error");
		message = new JLabel();
		message.setIcon(new ImageIcon("maxattacks.png"));
		add(message);
		revalidate();
	}

	public static void main(String[] args) {
		MaximumAttacks m = new MaximumAttacks();

	}
}
