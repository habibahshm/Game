package dragonball.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dragonball.controller.GameGUI;

public class Upgrade extends JFrame implements MouseListener {
	public static ArrayList<JButton> buttons = new ArrayList<>();
	public static ArrayList<JLabel> labels = new ArrayList<>();
	public JButton fighter;
	JButton maxHealthPoints = new JButton("upgrade max.health points");
	public JLabel maxHealthpointsNo = new JLabel("health number");
	JButton maxStamina = new JButton("upgrade max.stamina points");
	public JLabel staminaPoints = new JLabel("stamina number");
	JButton maxKi = new JButton("upgrade max.ki points");
	public JLabel maxKiPoints = new JLabel("ki number");
	JButton physicalDamage = new JButton("upgrade physical damage");
	public JLabel physicalDamagaPoints = new JLabel("physical damage points");
	JButton blastlDamage = new JButton("upgrade blast damage");
	public JLabel blastDamagaPoints = new JLabel("blast damage points");
	public JLabel abilityPoints = new JLabel("      ability points");
	public JLabel abilityPointsNo = new JLabel("ability points no.");

	public Upgrade() {
		super();
		setLayout(null);
		setBounds(0, 0, 1366, 744);
		setVisible(true);

		fighter = new JButton();
		fighter.setBackground(Color.black);
		fighter.setBorderPainted(false);
		add(fighter);
		fighter.setBounds(650, 0, getWidth() - 650, 698);
        buttons.add(maxHealthPoints);
        buttons.add(blastlDamage);
        buttons.add(physicalDamage);
        buttons.add(maxKi);
        buttons.add(maxStamina);
		maxHealthPoints.setActionCommand("upgrade health");
		maxStamina.setActionCommand("upgrade stamina");
		maxKi.setActionCommand("upgrade ki");
		physicalDamage.setActionCommand("upgrade physical");
		blastlDamage.setActionCommand("upgrade blast");
		labels.add(maxHealthpointsNo);
		labels.add(abilityPoints);
		labels.add(abilityPointsNo);
		labels.add(physicalDamagaPoints);
		labels.add(maxKiPoints);
		labels.add(blastDamagaPoints);
		labels.add(staminaPoints);

		JPanel Main = new JPanel(new GridLayout(6, 2));
		Main.setBackground(Color.black);
		add(Main);
		Main.setBounds(0, 0, getWidth() - fighter.getWidth(), 698);
		Main.add(abilityPoints);
		Main.add(abilityPointsNo);
		Main.add(maxHealthPoints);
		maxHealthPoints.setBorderPainted(false);
		maxHealthPoints.setContentAreaFilled(false);
		Main.add(maxHealthpointsNo);
		Main.add(maxStamina);
		maxStamina.setBorderPainted(false);
		maxStamina.setContentAreaFilled(false);
		Main.add(staminaPoints);
		Main.add(physicalDamage);
		physicalDamage.setBorderPainted(false);
		physicalDamage.setContentAreaFilled(false);
		Main.add(physicalDamagaPoints);
		Main.add(blastlDamage);
		blastlDamage.setBorderPainted(false);
		blastlDamage.setContentAreaFilled(false);
		Main.add(blastDamagaPoints);
		Main.add(maxKi);
		maxKi.setBorderPainted(false);
		maxKi.setContentAreaFilled(false);
		Main.add(maxKiPoints);
		for (JButton x : buttons){
			x.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
			x.setContentAreaFilled(false);
			x.addMouseListener(this);
		}
		
		revalidate();

	}

	public void customize(ArrayList<JButton> x, String photo, Color textcolor) {
		for (JButton tmp : x) {
			Icon i = new ImageIcon(((new ImageIcon(photo).getImage().getScaledInstance(tmp.getWidth(), tmp.getHeight(),
					java.awt.Image.SCALE_SMOOTH))));
			tmp.setIcon(i);
			tmp.setForeground(textcolor);
			tmp.setHorizontalTextPosition(JButton.CENTER);
			tmp.setVerticalTextPosition(JButton.CENTER);
		}
	}	
	public void customizel(ArrayList<JLabel> x) {
		for (JLabel tmp : x) {
			Icon j = new ImageIcon(((new ImageIcon("upgrade.jpg").getImage().getScaledInstance(tmp.getWidth(),tmp.getHeight(),
					java.awt.Image.SCALE_SMOOTH))));
			tmp.setIcon(j);
			tmp.setForeground(Color.white);
			tmp.setHorizontalTextPosition(JLabel.CENTER);
			tmp.setVerticalTextPosition(JLabel.CENTER);
		    tmp.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		}
	}	
	// public static void main(String[] args) {
	// Upgrade f = new Upgrade();
	// System.out.println(fighter.getSize());
	// }
	public void addListener(GameGUI x) {
		maxHealthPoints.addActionListener(x);
		maxKi.addActionListener(x);
		maxStamina.addActionListener(x);
		physicalDamage.addActionListener(x);
		blastlDamage.addActionListener(x);

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
		Icon i = new ImageIcon(((new ImageIcon("black.png").getImage().getScaledInstance(tmp.getWidth(),
				tmp.getHeight(), java.awt.Image.SCALE_SMOOTH))));
		tmp.setIcon(i);
		tmp.setForeground(Color.white);
		tmp.setHorizontalTextPosition(JButton.CENTER);
		tmp.setVerticalTextPosition(JButton.CENTER);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		customize(buttons, "white.png", Color.black);
	}
}
