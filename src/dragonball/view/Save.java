package dragonball.view;


import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dragonball.controller.GameGUI;

public class Save extends JFrame {
	JTextField savedGameName;
      public Save (){
    	  super();
    	//  setLayout(null);
    	  setBounds(500, 200, 600, 300);
    	  setVisible(true);
    	  setTitle("Save Game");
    	//  setDefaultCloseOperation(EXIT_ON_CLOSE);
    	  JLabel main = new JLabel(new ImageIcon("save.png"));
       	  main.setLayout(null);
    	  savedGameName = new JTextField(20);
    	  savedGameName.setActionCommand("saveName");
    	  main.add(savedGameName);
    	  savedGameName.setBounds(360,123, 200, 22);
    	  add(main);
    	  revalidate();
      }
      public static void main(String[] args) {
		Save g = new Save();
	}
      public void addListener(GameGUI x){
  		savedGameName.addActionListener(x);
  	}
}
