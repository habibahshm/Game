package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.scene.image.Image;

public class NotASaiyan extends JFrame {

	JLabel message;

	public NotASaiyan() {
		super();
		setBounds(500, 200, 466, 365);
		setVisible(true);
		setTitle("Not A Saiyan Error");
		message = new JLabel();
		message.setIcon(new ImageIcon("notsaiyan.png"));
		add(message,BorderLayout.CENTER);
		revalidate();
	}

	public static void main(String[] args) {
		NotASaiyan m = new NotASaiyan();

	}
}
