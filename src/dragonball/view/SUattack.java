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

public class SUattack extends JFrame implements MouseListener{
         JButton Super;
         JButton Ultimate;
         JButton physical;
         public static ArrayList<JButton> buttons;
         
         public SUattack(){
        	 super();
             setVisible(true);
             setLayout(new GridLayout(3, 1));
             setBounds(540, 550,200 , 150);
             
             buttons = new ArrayList<>();
             
             Super = new JButton("Super Attack");
             add(Super);
             Super.setActionCommand("SAttack");
			 Super.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
			 Super.setContentAreaFilled(false);
			 Super.addMouseListener(this);
			 
			 physical = new JButton("Physical Attack");
             add(physical);
             physical.setActionCommand("PAttack");
			 physical.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
			 physical.setContentAreaFilled(false);
			 physical.addMouseListener(this);
			 
             Ultimate = new JButton("Ultimate Attack");
             add(Ultimate);
             Ultimate.setActionCommand("UAttack");
			 Ultimate.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
			 Ultimate.setContentAreaFilled(false);
			 Ultimate.addMouseListener(this);
			 
			 buttons.add(Super);
			 buttons.add(physical);
			 buttons.add(Ultimate);
			 
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
        	 physical.addActionListener(x);
        	 Ultimate.addActionListener(x);
        	 Super.addActionListener(x);
         }
         
         public static void main(String[] args) {
			SUattack f = new SUattack();
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
