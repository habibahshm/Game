package dragonball.view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class NotEnoughAbility extends JFrame {

	public NotEnoughAbility() {
		super();
		setBounds(500, 200, 466, 365);
		setVisible(true);
		setTitle("Not Enough Ability Points Error");
		JLabel message = new JLabel();
		message.setIcon(new ImageIcon("noability.png"));
		add(message);
		revalidate();
	}

	public static void main(String[] args) {
		NotEnoughAbility m = new NotEnoughAbility();

	}
}
