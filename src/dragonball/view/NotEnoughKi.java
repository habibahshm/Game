package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NotEnoughKi extends JFrame {
	public JLabel message;

	public NotEnoughKi() {
		super();
		setBounds(500, 200, 466, 365);
		setVisible(true);
		setTitle("Not enough Ki");
		message = new JLabel();
		message.setIcon(new ImageIcon("noki.png"));
		add(message);
		revalidate();
	}

	public static void main(String[] args) {
		NotEnoughKi m = new NotEnoughKi();

	}
}
