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

import dragonball.controller.GameGUI;
import dragonball.model.attack.SuperAttack;

public class Sattack extends JFrame implements MouseListener{
	public static ArrayList<JButton> buttons;
	
	public Sattack(ArrayList<SuperAttack> x) {
		super();
		setVisible(true);
		setBounds(800, 250, 400, 150);
		buttons = new ArrayList<>();
		if ( x != null && x.size() > 0) {
			setLayout(new GridLayout(x.size(), 1));
			for (SuperAttack attack : x) {
				JButton tmp = new JButton(attack.getName());
				add(tmp);
				tmp.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
				tmp.setContentAreaFilled(false);
				tmp.addMouseListener(this);
				tmp.setActionCommand("fs");
				buttons.add(tmp);
			}
			
		} else {
			buttons = new ArrayList<>();
			JButton tmp = new JButton("No super Attack");
			add(tmp);
			tmp.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
			tmp.addMouseListener(this);
			tmp.setForeground(Color.black);
		
			tmp.setHorizontalTextPosition(JButton.CENTER);
			tmp.setVerticalTextPosition(JButton.CENTER);
			buttons.add(tmp);
		}
		revalidate();
		customize(buttons, "white.png", Color.black);
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
	
	 public void addListener(GameGUI x){
    	 for(JButton b: buttons)
    		 b.addActionListener(x);
     }
	public static void main(String[] args) {
	 Sattack t = new Sattack(null);
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
		// TODO Auto-generated method stub
		customize(buttons, "white.png", Color.black);
	}
}
