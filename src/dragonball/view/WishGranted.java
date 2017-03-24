package dragonball.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WishGranted extends JFrame {
	public JLabel wish;
	
	public WishGranted() {
		super();
		setBounds(270, 170, 900, 350);
		setVisible(true);
		JLabel main = new JLabel();
		add(main);
        main.setIcon(new ImageIcon("wishgranted.png"));
        wish = new JLabel();
        main.add(wish);
        wish.setBounds(245, 50, 630, 100);
        wish.setText("you have been granted th ejnjjnjnjn ultimate attack");
        wish.setForeground(new Color(43, 243, 243));
		wish.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
	}

	public static void main(String[] args) {
		WishGranted d = new WishGranted();
	}
}
