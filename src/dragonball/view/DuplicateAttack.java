package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DuplicateAttack extends JFrame {
	JLabel message;

	public DuplicateAttack() {
		super();
		setBounds(500, 200, 466, 365);
		setVisible(true);
		setTitle("Duplicate Attack Error");
		message = new JLabel();
		message.setIcon(new ImageIcon("dup.png"));
		add(message);
		revalidate();
	}

	public static void main(String[] args) {
		DuplicateAttack m = new DuplicateAttack();

	}
}
