package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import dragonball.controller.GameGUI;

public class Start extends JFrame implements MouseListener {
	JButton load;
	JButton start;
	JLabel main;
	ArrayList<JMenuItem> menuItems;

	public Start() {
		super();
		setSize(1366, 736);
		setVisible(true);
		 main = new JLabel();
		main.setLayout(null);
		ImageIcon i = new ImageIcon("start.png");
		main.setIcon(i);
		add(main, BorderLayout.CENTER);

		start = new JButton();
		start.setActionCommand("start");
		start.setContentAreaFilled(false);
		start.setBorderPainted(false);
		start.addMouseListener(this);
		main.add(start);
		start.setBounds(630, 210, 100, 100);

		load = new JButton();
		load.setActionCommand("load");
		main.add(load);
		load.addMouseListener(this);
		load.setBounds(630, 400, 100, 100);
		load.setBorderPainted(false);
		load.setContentAreaFilled(false);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		revalidate();
	}

	public static void main(String[] args) {
		Start test = new Start();

	}

	public void addListener(GameGUI x) {
		start.addActionListener(x);
		load.addActionListener(x);
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
		if (tmp.getActionCommand().equals("start")) {
//			Icon x = new ImageIcon((new ImageIcon("startlight.png")).getImage().getScaledInstance(start.getWidth(),
//					start.getHeight(), Image.SCALE_SMOOTH));
			main.setIcon(new ImageIcon("startlight.png"));
		}else if(tmp.getActionCommand().equals("load")){
			main.setIcon(new ImageIcon("loadlight.png"));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		main.setIcon(new ImageIcon("start.png"));

	}
}
