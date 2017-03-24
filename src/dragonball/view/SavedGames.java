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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.xml.soap.Text;

import dragonball.controller.GameGUI;

public class SavedGames extends JDialog implements MouseListener {
	static ArrayList<JButton> buttons;

	public SavedGames(ArrayList<String> x, GameGUI y) {
		super();
		setBounds(400, 30, 600, 700);
		setVisible(true);
		setLayout(new GridLayout(x.size(), 1));
		buttons = new ArrayList<>();
		if (x.size() > 0) {
			for (String game : x) {
				JButton tmp = new JButton(game);
				add(tmp);
				tmp.addActionListener(y);
				tmp.setActionCommand("savedGame");
				tmp.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
				tmp.setContentAreaFilled(false);
				tmp.addMouseListener(this);
				buttons.add(tmp);
			}
			
		} else {
			buttons = new ArrayList<>();
			JButton tmp = new JButton("No saved Games");
			add(tmp);
			tmp.addActionListener(y);
			tmp.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
			tmp.addMouseListener(this);
			Icon i = new ImageIcon(((new ImageIcon("white.png").getImage().getScaledInstance(tmp.getWidth(),
					tmp.getHeight(), java.awt.Image.SCALE_SMOOTH))));
			tmp.setIcon(i);
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

	@Override
	public void mouseClicked(MouseEvent e) {

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
		// customize(buttons,"black.png",Color.white);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		customize(buttons, "white.png", Color.black);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		ArrayList<String> x = new ArrayList();
		SavedGames s = new SavedGames(x, null);
	}
}
