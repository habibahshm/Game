package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dragonball.controller.GameGUI;

public class NewFighter extends JFrame implements MouseListener {
	JTextField fighter = new JTextField(20);
	JButton sayian = new JButton();
	JButton majin = new JButton();
	JButton earthling = new JButton();
	JButton frieza = new JButton();
	JButton namekian = new JButton();
	JLabel main;

	public NewFighter() {
		super();
		setSize(1366, 736);
		setVisible(true);
		main = new JLabel();
		main.setLayout(new BorderLayout());
		main.setIcon(new ImageIcon("createf.png"));
		JLabel fighters = new JLabel();
		fighters.setLayout(new GridLayout(1, 5));
		fighters.setPreferredSize(new Dimension(1366, 500));
		main.add(fighters, BorderLayout.SOUTH);

		sayian.setActionCommand("saiyanf");
		sayian.setContentAreaFilled(false);
		sayian.setBorderPainted(false);
		majin.setActionCommand("majinf");
		majin.setContentAreaFilled(false);
		majin.setBorderPainted(false);
		earthling.setContentAreaFilled(false);
		earthling.setBorderPainted(false);
		earthling.setActionCommand("earthlingf");
		frieza.setActionCommand("friezaf");
		frieza.setContentAreaFilled(false);
		frieza.setBorderPainted(false);
		namekian.setActionCommand("namekianf");
		namekian.setContentAreaFilled(false);
		namekian.setBorderPainted(false);

		sayian.addMouseListener(this);
		majin.addMouseListener(this);
		namekian.addMouseListener(this);
		frieza.addMouseListener(this);
		earthling.addMouseListener(this);

		fighters.add(sayian);
		fighters.add(majin);
		fighters.add(namekian);
		fighters.add(frieza);
		fighters.add(earthling);
		fighter.setActionCommand("fighterName");

		JLabel texts = new JLabel();
		texts.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		texts.add(fighter);
		fighters.setBounds(10, 5, 200, 150);
		main.add(texts);
		add(main);
		revalidate();

	}

	public static void main(String[] args) {
		NewFighter j = new NewFighter();

	}

	public void addListener(GameGUI x) {
		namekian.addActionListener(x);
		frieza.addActionListener(x);
		earthling.addActionListener(x);
		sayian.addActionListener(x);
		majin.addActionListener(x);
		fighter.addActionListener(x);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JButton tmp = (JButton) e.getSource();
		if (tmp.getActionCommand().equals("saiyanf")) {
			Icon x = new ImageIcon((new ImageIcon("saiyanheighlight.png")).getImage()
					.getScaledInstance(sayian.getWidth(), sayian.getHeight(), Image.SCALE_SMOOTH));
			tmp.setIcon(x);
		} else if (tmp.getActionCommand().equals("majinf")) {
			// Icon x = new ImageIcon((new
			// ImageIcon("majinheighlight.png")).getImage().getScaledInstance(sayian.getWidth(),
			// sayian.getHeight(), Image.SCALE_SMOOTH));
			tmp.setIcon((new ImageIcon("majinheighlight.png")));
		} else if (tmp.getActionCommand().equals("namekianf")) {
			Icon x = new ImageIcon((new ImageIcon("namekianheighlight.png")).getImage()
					.getScaledInstance(sayian.getWidth(), sayian.getHeight(), Image.SCALE_SMOOTH));
			tmp.setIcon(x);
		} else if (tmp.getActionCommand().equals("friezaf")) {
			Icon x = new ImageIcon((new ImageIcon("friezaheighlight.png")).getImage()
					.getScaledInstance(sayian.getWidth(), sayian.getHeight(), Image.SCALE_SMOOTH));
			tmp.setIcon(x);
		} else if (tmp.getActionCommand().equals("earthlingf")) {
			// Icon x = new ImageIcon((new
			// ImageIcon("earthlingheighlight.png")).getImage().getScaledInstance(sayian.getWidth(),
			// sayian.getHeight(), Image.SCALE_SMOOTH));
			main.setIcon(new ImageIcon("earthlingheighlightf.png"));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton tmp = (JButton) e.getSource();
		if (tmp.getActionCommand().equals("earthlingf")) {
			main.setIcon(new ImageIcon("createf.png"));
		} else
			tmp.setIcon(null);

	}

}
