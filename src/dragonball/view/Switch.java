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
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;

public class Switch extends JFrame implements MouseListener{

	public JLabel photo;
	public JLabel fighters;
    public ArrayList<JButton> buttons = new ArrayList<>();
	public Switch() {
		super();
		setBounds(0, 0, 1366, 744);
		setVisible(true);
		setLayout(null);
		fighters = new JLabel();
		photo = new JLabel();
		add(photo);
		add(fighters);
		fighters.setBounds(0, 0, (getWidth() / 2) + 200, 697);
		photo.setBounds((getWidth() / 2) + 200,0, getWidth()-((getWidth() / 2) + 200), 697);
		revalidate();
	}

	public static void main(String[] args) {
		Switch f = new Switch();
	}

	public void addFighters(ArrayList<PlayableFighter> x, GameGUI y) {
		if (x.size() > 0) {
			fighters.setLayout(new GridLayout(x.size(), 1));
			for (PlayableFighter fighter : x) {
				JButton tmp = new JButton(fighter.getName());
				tmp.setActionCommand("switchF");
				fighters.add(tmp);
				tmp.addActionListener(y);
				tmp.setContentAreaFilled(false);
				tmp.setBorderPainted(false);
				tmp.addMouseListener(y);
			//	tmp.addMouseListener(this);
				buttons.add(tmp);
				revalidate();
			}

		}
	}

	public void customize(ArrayList<JButton> x) {
		for (JButton tmp : x) {
			Icon i = new ImageIcon(((new ImageIcon("upgrade.jpg").getImage().getScaledInstance(tmp.getWidth(), tmp.getHeight(),
					java.awt.Image.SCALE_SMOOTH))));
			tmp.setIcon(i);
			tmp.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
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
		JButton tmp = (JButton) e.getSource();
		Icon i = new ImageIcon(((new ImageIcon("bselect.jpg").getImage().getScaledInstance(tmp.getWidth(),
				tmp.getHeight(), java.awt.Image.SCALE_SMOOTH))));
		tmp.setIcon(i);
		tmp.setForeground(Color.black);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton tmp = (JButton) e.getSource();
		Icon i = new ImageIcon(((new ImageIcon("upgrade.jpg").getImage().getScaledInstance(tmp.getWidth(),
				tmp.getHeight(), java.awt.Image.SCALE_SMOOTH))));
		tmp.setIcon(i);
		tmp.setForeground(Color.white);
		
	}
}
