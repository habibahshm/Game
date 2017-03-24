package dragonball.view;

import java.awt.Button;
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

public class Battle_ended extends JFrame implements MouseListener {
	public JLabel xp;
	ArrayList<JLabel> att;
    public JLabel info;
	public Battle_ended(int xp, ArrayList<Attack> attacks) {
		super();
		setBounds(240, 150, 850, 401);
		setVisible(true);
		setLayout(new GridLayout(attacks.size() + 2, 1));
		att = new ArrayList<>();
	    info = new JLabel("you won");
		add(info);
		info.addMouseListener(this);
		this.xp = new JLabel("xp           " + xp);
		add(this.xp);
        this.xp.addMouseListener(this);
		
		for (Attack attack : attacks) {
			JLabel tmp = new JLabel();
			add(tmp);
			att.add(tmp);
			tmp.addMouseListener(this);
			tmp.setText("        Skills         " + attack.getName());
			tmp.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		}

		revalidate();
		Icon i = new ImageIcon(((new ImageIcon("upgrade.jpg").getImage().getScaledInstance(this.xp.getWidth(),
				this.xp.getHeight(), java.awt.Image.SCALE_SMOOTH))));
		this.xp.setIcon(i);
		this.xp.setForeground(Color.white);
		this.xp.setHorizontalTextPosition(JButton.CENTER);
		this.xp.setVerticalTextPosition(JButton.CENTER);
		i = new ImageIcon(((new ImageIcon("upgrade.jpg").getImage().getScaledInstance(info.getWidth(), info.getHeight(),
				java.awt.Image.SCALE_SMOOTH))));
		info.setIcon(i);
		info.setForeground(Color.white);
		info.setHorizontalTextPosition(JButton.CENTER);
		info.setVerticalTextPosition(JButton.CENTER);
		addImage(att);
		revalidate();
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
