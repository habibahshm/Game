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

import dragonball.model.attack.Attack;

public class Battle_ended_levelup extends JFrame implements MouseListener {
	public JLabel xp;
	public JLabel targetXp;
	public JLabel level;
	public JLabel abi;
	public JLabel info;
	ArrayList<JLabel> att;

	public Battle_ended_levelup(int xp, int targetXp, int abilityPoints, int level, ArrayList<Attack> attacks) {
		super();
		setBounds(240, 150, 850, 401);
		setVisible(true);
		setLayout(new GridLayout(attacks.size()+5,1));
		att = new ArrayList<>();
	    info = new JLabel("you won and leveled up");
		add(info);
		this.xp = new JLabel("xp           " + xp);
		this.targetXp = new JLabel("targetXP       " + targetXp);
		this.level = new JLabel("Level               " + level);
		this.abi = new JLabel("    ability points          " + abilityPoints);
		add(this.xp);
		add(this.targetXp);
		add(this.level);
		add(this.abi);
		
	
		for (Attack attack : attacks) {
			JLabel tmp = new JLabel();
			add(tmp);
			att.add(tmp);
			tmp.setText("         Skills       " + attack.getName());
			tmp.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		}
		revalidate();
		Icon i = new ImageIcon(((new ImageIcon("upgrade.jpg").getImage().getScaledInstance(this.xp.getWidth(),
				this.xp.getHeight(), java.awt.Image.SCALE_SMOOTH))));
		this.xp.setIcon(i);
		this.xp.setForeground(Color.white);
		this.xp.setHorizontalTextPosition(JButton.CENTER);
		this.xp.setVerticalTextPosition(JButton.CENTER);
		this.xp.addMouseListener(this);
		i = new ImageIcon(((new ImageIcon("upgrade.jpg").getImage().getScaledInstance(info.getWidth(),
				info.getHeight(), java.awt.Image.SCALE_SMOOTH))));
		info.setIcon(i);
		info.setForeground(Color.white);
		info.setHorizontalTextPosition(JButton.CENTER);
		info.setVerticalTextPosition(JButton.CENTER);
		info.addMouseListener(this);
		i = new ImageIcon(((new ImageIcon("upgrade.jpg").getImage().getScaledInstance(this.targetXp.getWidth(),
				this.targetXp.getHeight(), java.awt.Image.SCALE_SMOOTH))));
		this.targetXp.setIcon(i);
		this.targetXp.setForeground(Color.white);
		this.targetXp.setHorizontalTextPosition(JButton.CENTER);
		this.targetXp.setVerticalTextPosition(JButton.CENTER);
		this.targetXp.addMouseListener(this);
		i = new ImageIcon(((new ImageIcon("upgrade.jpg").getImage().getScaledInstance(this.level.getWidth(),
				this.level.getHeight(), java.awt.Image.SCALE_SMOOTH))));
		this.level.setIcon(i);
		this.level.setForeground(Color.white);
		this.level.setHorizontalTextPosition(JButton.CENTER);
		this.level.setVerticalTextPosition(JButton.CENTER);
		this.level.addMouseListener(this);
		i = new ImageIcon(((new ImageIcon("upgrade.jpg").getImage().getScaledInstance(this.abi.getWidth(),
				this.abi.getHeight(), java.awt.Image.SCALE_SMOOTH))));
		this.abi.setIcon(i);
		this.abi.setForeground(Color.white);
		this.abi.setHorizontalTextPosition(JButton.CENTER);
		this.abi.setVerticalTextPosition(JButton.CENTER);
		this.abi.addMouseListener(this);
		addImage(att);

	}

	public void addImage(ArrayList<JLabel> x) {
		for (JLabel tmp : x) {
			Icon i = new ImageIcon(((new ImageIcon("upgrade.jpg").getImage().getScaledInstance(tmp.getWidth(),
					tmp.getHeight(), java.awt.Image.SCALE_SMOOTH))));
			tmp.setIcon(i);
			tmp.setForeground(Color.white);
			tmp.setHorizontalTextPosition(JButton.CENTER);
			tmp.setVerticalTextPosition(JButton.CENTER);
		}
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
		JLabel tmp = (JLabel) e.getSource();
		Icon i = new ImageIcon(((new ImageIcon("bselect.jpg").getImage().getScaledInstance(tmp.getWidth(),
				tmp.getHeight(), java.awt.Image.SCALE_SMOOTH))));
		tmp.setIcon(i);
		tmp.setForeground(Color.black);
		tmp.setHorizontalTextPosition(JButton.CENTER);
		tmp.setVerticalTextPosition(JButton.CENTER);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel tmp = (JLabel) e.getSource();
		Icon i = new ImageIcon(((new ImageIcon("upgrade.jpg").getImage().getScaledInstance(tmp.getWidth(),
				tmp.getHeight(), java.awt.Image.SCALE_SMOOTH))));
		tmp.setIcon(i);
		tmp.setForeground(Color.white);
		tmp.setHorizontalTextPosition(JButton.CENTER);
		tmp.setVerticalTextPosition(JButton.CENTER);

	}

}
