package dragonball.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class test extends JFrame {
	public JPanel health;

	public test() {
		super();
		setVisible(true);
		setBounds(0, 0, 600, 600);
		setLayout(null);
		health = new JPanel();
		add(health);
		health.setBounds(0, 0, 500, 50);
		health.setBackground(Color.green);
		revalidate();
	}

	public static void main(String[] args) {
		test g = new test();
		int maxHealth = 2500;
		int currentHealth = 1100;
		double ratio = ((double) currentHealth) / maxHealth;
		// changing fighter health
//		g.health.setBounds(500 - (int) (g.health.getWidth() * ratio), g.health.getBounds().y,
//				(int) (g.health.getWidth() * ratio), g.health.getHeight());
        //changing foe health
		g.health.setBounds(g.health.getBounds().x, g.health.getBounds().y,
				(int) (g.health.getWidth() * ratio), g.health.getHeight());
		if(ratio*100 <50 && ratio*100 > 20)
			g.health.setBackground(Color.yellow);
		else if(ratio*100 <= 20 )
			g.health.setBackground(Color.red);
        

	}
}
