package dragonball.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Lost extends JFrame implements MouseListener {
	
	public Lost(){
		super();
		setBounds(240, 150, 850, 401);
		setVisible(true);
		setLayout(new GridLayout(1, 1));
		JLabel info = new JLabel("you lost !");
		add(info);
		revalidate();
		Icon i = new ImageIcon(((new ImageIcon("upgrade.jpg").getImage().getScaledInstance(info.getWidth(), info.getHeight(),
				java.awt.Image.SCALE_SMOOTH))));
		info.setIcon(i);
		info.setForeground(Color.white);
		info.setHorizontalTextPosition(JButton.CENTER);
		info.setVerticalTextPosition(JButton.CENTER);
		info.addMouseListener(this);
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
