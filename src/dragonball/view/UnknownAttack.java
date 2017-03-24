package dragonball.view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UnknownAttack extends JFrame {

	JLabel message;

	public UnknownAttack() {
		super();
		setBounds(500, 200, 466, 365);
		setVisible(true);
		setTitle("Unknown Attack Error");
		message = new JLabel();
		message.setIcon(new ImageIcon("unknownattack.png"));
		add(message);
		revalidate();
	}

	public static void main(String[] args) {
		UnknownAttack m = new UnknownAttack();

	}
}
