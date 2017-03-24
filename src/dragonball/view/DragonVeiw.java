package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import dragonball.controller.GameGUI;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.dragon.Dragon;

public class DragonVeiw extends JFrame {
	JButton senzubean;
	JButton ability;
	JButton ultimate;
	JButton superAttack;
	public JLabel senzuNumber;
	public JLabel abilityNumber;
	

	public DragonVeiw() {
		super();
		setVisible(true);
		setBounds(110, 0, 1165, 730);
		JLabel p = new JLabel();
		p.setLayout(null);
		add(p, BorderLayout.CENTER);
		Icon i = new ImageIcon("dragon1.png");
		p.setIcon(i);
		senzubean = new JButton();
		p.add(senzubean);
		senzubean.setBounds(1000, 45, 100, 150);
		senzubean.setBorderPainted(false);
		senzubean.setContentAreaFilled(false);
		senzubean.setActionCommand("senzuWish");

		senzuNumber = new JLabel("10");
		senzuNumber.setForeground(Color.black);
		senzuNumber.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
		p.add(senzuNumber);
		senzuNumber.setBounds(1055, 90, 100, 100);

		ability = new JButton();
		p.add(ability);
		ability.setBounds(70, 35, 150, 155);
		ability.setBorderPainted(false);
		ability.setContentAreaFilled(false);
		ability.setActionCommand("abilityWish");

		abilityNumber = new JLabel("10");
		abilityNumber.setForeground(Color.white);
		abilityNumber.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
		p.add(abilityNumber);
		abilityNumber.setBounds(120, 100, 100, 100);

		ultimate = new JButton();
		p.add(ultimate);
		ultimate.setBounds(695, 55, 150, 150);
		ultimate.setBorderPainted(false);
		ultimate.setContentAreaFilled(false);
		ultimate.setActionCommand("ultimateWish");

		superAttack = new JButton();
		p.add(superAttack);
		superAttack.setBounds(360, 55, 150, 155);
		superAttack.setBorderPainted(false);
		superAttack.setContentAreaFilled(false);
		superAttack.setActionCommand("superWish");

		revalidate();
	}

	public void addListener(GameGUI x) {
		senzubean.addActionListener(x);
		ability.addActionListener(x);
		ultimate.addActionListener(x);
		superAttack.addActionListener(x);
	}

	public static void main(String[] args) {
		DragonVeiw d = new DragonVeiw();
	}
}
