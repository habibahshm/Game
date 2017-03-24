package dragonball.view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MissingFields extends JFrame {

	JLabel message;

	public MissingFields() {
		super();
		setBounds(500, 200, 466, 365);
		setVisible(true);
		setTitle("Missing Fields Error");
		message = new JLabel();
		message.setIcon(new ImageIcon("missing.png"));
		add(message);
		revalidate();
	}

	public static void main(String[] args) {
		MissingFields m = new MissingFields();

	}
}
