package dragonball.view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class NotEnoughSenzuBeans extends JFrame {

	public NotEnoughSenzuBeans() {
		super();
		setBounds(500, 200, 466, 365);
		setVisible(true);
		setTitle("Not Enough Senzu Error");
		JLabel message = new JLabel();
		message.setIcon(new ImageIcon("nosenzu.png"));
		add(message);
		revalidate();
	}

	public static void main(String[] args) {
		NotEnoughSenzuBeans m = new NotEnoughSenzuBeans();

	}
}
