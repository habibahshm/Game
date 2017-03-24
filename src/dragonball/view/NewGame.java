package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dragonball.controller.GameGUI;
import dragonball.model.character.fighter.Fighter;

public class NewGame extends JFrame implements MouseListener {
	static JTextField name = new JTextField(20);
	static JTextField fighter = new JTextField(20);
	JButton sayian = new JButton();
	JButton majin = new JButton();
	JButton earthling = new JButton();
	JButton frieza = new JButton();
	JButton namekian = new JButton();
	JLabel main;

	public NewGame() {
		super();
		setSize(1366, 736);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLayout(new BorderLayout());
           main = new JLabel();
          main.setLayout(new BorderLayout());
          main.setIcon(new ImageIcon("newgame.png"));
		JLabel fighters = new JLabel();
		fighters.setLayout(new GridLayout(1, 5));

		fighters.setPreferredSize(new Dimension(1366, 500));
		main.add(fighters, BorderLayout.SOUTH);

		sayian.setActionCommand("saiyan");
		sayian.setContentAreaFilled(false);
		sayian.setBorderPainted(false);
		majin.setActionCommand("majin");
		majin.setContentAreaFilled(false);
		majin.setBorderPainted(false);
		earthling.setContentAreaFilled(false);
		earthling.setBorderPainted(false);
		earthling.setActionCommand("earthling");
		frieza.setActionCommand("frieza");
		frieza.setContentAreaFilled(false);
		frieza.setBorderPainted(false);
		namekian.setActionCommand("namekian");
		namekian.setContentAreaFilled(false);
		namekian.setBorderPainted(false);

		fighters.add(sayian);
		fighters.add(majin);
		fighters.add(namekian);
		fighters.add(frieza);
		fighters.add(earthling);

		 sayian.addMouseListener(this);
		 majin.addMouseListener(this);
		 namekian.addMouseListener(this);
		 frieza.addMouseListener(this);
		 earthling.addMouseListener(this);
		
		 name.setActionCommand("playerName");
		fighter.setActionCommand("fighterName");
		JLabel texts = new JLabel();
		texts.setPreferredSize(new Dimension(1366, 736-500));
		texts.setLayout(null);
		main.add(texts, BorderLayout.NORTH);
		texts.add(name);
		name.setBounds(255, 95, 300, 30);
		texts.add(fighter);
		fighter.setBounds(890, 95, 300, 30);
		
		add(main);
		revalidate();

	}

	public static void main(String[] args) {
		NewGame j = new NewGame();
		//System.out.println(j.majin.getSize());

	}

	public void addListener(GameGUI x) {
		namekian.addActionListener(x);
		frieza.addActionListener(x);
		earthling.addActionListener(x);
		sayian.addActionListener(x);
		majin.addActionListener(x);
		fighter.addActionListener(x);
		name.addActionListener(x);
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
		JButton tmp = (JButton)e.getSource();
		if(tmp.getActionCommand().equals("saiyan")){
	Icon x = new ImageIcon((new ImageIcon("saiyanheighlight.png")).getImage().getScaledInstance(sayian.getWidth(), sayian.getHeight(), Image.SCALE_SMOOTH));
		tmp.setIcon(x);
		}
		else if(tmp.getActionCommand().equals("majin")){
			//Icon x = new ImageIcon((new ImageIcon("majinheighlight.png")).getImage().getScaledInstance(sayian.getWidth(), sayian.getHeight(), Image.SCALE_SMOOTH));
			tmp.setIcon((new ImageIcon("majinheighlight.png")));
		}
		else if(tmp.getActionCommand().equals("namekian")){
			Icon x = new ImageIcon((new ImageIcon("namekianheighlight.png")).getImage().getScaledInstance(sayian.getWidth(), sayian.getHeight(), Image.SCALE_SMOOTH));
			tmp.setIcon(x);
		}
		else if(tmp.getActionCommand().equals("frieza")){
			Icon x = new ImageIcon((new ImageIcon("friezaheighlight.png")).getImage().getScaledInstance(sayian.getWidth(), sayian.getHeight(), Image.SCALE_SMOOTH));
			tmp.setIcon(x);
		}else if(tmp.getActionCommand().equals("earthling")){
			//Icon x = new ImageIcon((new ImageIcon("earthlingheighlight.png")).getImage().getScaledInstance(sayian.getWidth(), sayian.getHeight(), Image.SCALE_SMOOTH));
			main.setIcon(new ImageIcon("earthlingheighlight.png"));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton tmp = (JButton)e.getSource();
		if(tmp.getActionCommand().equals("earthling")){
			main.setIcon(new ImageIcon("newgame.png"));
		}
		else
		tmp.setIcon(null);
	}

}
