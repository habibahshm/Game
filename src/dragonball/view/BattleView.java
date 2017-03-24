package dragonball.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dragonball.controller.GameGUI;

public class BattleView extends JFrame implements MouseListener {
	JButton attack;
	JButton block;
	JButton use;
	ArrayList<JButton> buttons = new ArrayList<>();

	public JLabel fighterphoto;
	public JLabel foephoto;
	public JButton foe;
	public JButton fighter;

	public JLabel fighterStamina;
	public JLabel fighterKi;

	public JLabel foeStamina;
	public JLabel foeKi;

	public ArrayList<JButton> pHealth;
	public ArrayList<JLabel> pStamina;
	public ArrayList<JLabel> pKi;
	public ArrayList<JLabel> fHealth;
	public ArrayList<JLabel> fStamina;
	public ArrayList<JLabel> fKi;

	public JLabel fighterHealth;
	public JLabel foeHealth;

	public JLabel meblock = new JLabel(new ImageIcon("shield.png"));
	public JLabel foblock = new JLabel(new ImageIcon("shield.png"));

	public JLabel pcurrenthealth;
	public JLabel fcurrenthealth;

	//////

	public JLabel turn;
	public int ps;
	public int ph;
	public int pk;

	public int fs;
	public int fh;
	public int fk;

	// Info
	public JLabel pname = new JLabel();
	public JLabel plevel = new JLabel();
	public JLabel Pmaxstamina = new JLabel();
	public JLabel PmaxHp = new JLabel();
	public JLabel PmaxKi = new JLabel();

	public JLabel fname = new JLabel();
	public JLabel flevel = new JLabel();
	public JLabel fmaxstamina = new JLabel();
	public JLabel fmaxHp = new JLabel();
	public JLabel fmaxKi = new JLabel();

	public JLabel playerhealthlabel;
	public JLabel foehealthlabel;

	public JPanel playerhealthbar;
	public JPanel foehealthbar;

	public BattleView() {
		super();
		setBounds(240, 150, 850, 401);
		setVisible(true);
		JLabel main = new JLabel();
		main.setLayout(null);
		ImageIcon i = new ImageIcon("Battle1.gif");
		main.setIcon(i);
		add(main);

		playerhealthlabel = new JLabel();
		main.add(playerhealthlabel);
		playerhealthlabel.setBounds(584, 0, 200, 18);
		playerhealthlabel.setIcon(
				new ImageIcon(new ImageIcon("keda.png").getImage().getScaledInstance(200, 18, Image.SCALE_SMOOTH)));

		playerhealthbar = new JPanel();
		main.add(playerhealthbar);
		playerhealthbar.setBounds(584, 0, 200, 18);
		playerhealthbar.setBackground(Color.green);

		foehealthlabel = new JLabel();
		main.add(foehealthlabel);
		foehealthlabel.setBounds(50, 0, 200, 18);
		foehealthlabel.setIcon(
				new ImageIcon(new ImageIcon("keda.png").getImage().getScaledInstance(200, 18, Image.SCALE_SMOOTH)));

		foehealthbar = new JPanel();
		main.add(foehealthbar);
		foehealthbar.setBounds(50, 0, 200, 18);
		foehealthbar.setBackground(Color.green);

		attack = new JButton("ATTACK");
		attack.setActionCommand("attack");
		attack.setIcon(new ImageIcon("battlebutton2.png"));
		attack.setForeground(Color.white);
		attack.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		attack.setHorizontalTextPosition(JButton.CENTER);
		attack.setVerticalTextPosition(JButton.CENTER);
		main.add(attack);
		buttons.add(attack);
		attack.setBounds(350, 320, 100, 30);
		attack.setContentAreaFilled(false);
		attack.setBorderPainted(false);

		turn = new JLabel("Your Turn");
		main.add(turn);
		turn.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		turn.setForeground(Color.white);
		// turn.setContentAreaFilled(false);
		// turn.setBorderPainted(false);
		turn.setBounds(350, 100, 200, 50);
		turn.setHorizontalTextPosition(JLabel.CENTER);
		turn.setVerticalTextPosition(JLabel.CENTER);

		block = new JButton("BLOCK");
		block.setActionCommand("block");
		block.setIcon(new ImageIcon("battlebutton2.png"));
		block.setForeground(Color.white);
		block.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		block.setHorizontalTextPosition(JButton.CENTER);
		block.setVerticalTextPosition(JButton.CENTER);
		main.add(block);
		buttons.add(block);
		block.setBounds(246, 330, 100, 30);
		block.setContentAreaFilled(false);
		block.setBorderPainted(false);

		pcurrenthealth = new JLabel();
		fcurrenthealth = new JLabel();
		main.add(fcurrenthealth);
		main.add(pcurrenthealth);
		fcurrenthealth.setBounds(250, 0, 50, 18);
		fcurrenthealth.setForeground(Color.white);
		fcurrenthealth.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		fcurrenthealth.setVerticalTextPosition(JLabel.CENTER);

		pcurrenthealth.setBounds(534, 0, 50, 18);
		pcurrenthealth.setForeground(Color.white);
		pcurrenthealth.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		pcurrenthealth.setVerticalTextPosition(JLabel.CENTER);

		use = new JButton("USE");
		use.setActionCommand("use");
		use.setIcon(new ImageIcon("battlebutton2.png"));
		use.setForeground(Color.white);
		use.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		use.setHorizontalTextPosition(JButton.CENTER);
		use.setVerticalTextPosition(JButton.CENTER);
		main.add(use);
		buttons.add(use);
		use.setBounds(453, 330, 100, 30);
		use.setContentAreaFilled(false);
		use.setBorderPainted(false);

		attack.addMouseListener(this);
		block.addMouseListener(this);
		use.addMouseListener(this);

		foephoto = new JLabel();
		main.add(foephoto);
		foephoto.setBounds(0, 0, 50, 50);

		fighterphoto = new JLabel();
		main.add(fighterphoto);
		fighterphoto.setBounds(784, 0, 50, 50);

		foe = new JButton();
		main.add(foe);
		foe.setBounds(195, 185, 200, 130);
		foe.setContentAreaFilled(false);
		foe.setBorderPainted(false);
		foe.setIcon(new ImageIcon("JeiceAttack.gif"));

		fighter = new JButton();
		main.add(fighter);
		fighter.setBounds(395,185, 200, 130);
		fighter.setContentAreaFilled(false);
		fighter.setBorderPainted(false);
		fighter.setIcon(new ImageIcon("FriezaUltimate.gif"));
		foeStamina = new JLabel();
		main.add(foeStamina);
		foeStamina.setBounds(50, 18, 150, 16);

		foeKi = new JLabel();
		main.add(foeKi);
		foeKi.setBounds(50, 34, 100, 16);

		fighterStamina = new JLabel();
		main.add(fighterStamina);
		fighterStamina.setBounds(634, 18, 150, 16);

		fighterKi = new JLabel();
		main.add(fighterKi);
		fighterKi.setBounds(684, 34, 100, 16);

		// main.add(fmaxHp);
		// fmaxHp.setBounds(80, 0, 300, 18);
		// fmaxHp.setForeground(Color.white); /// max health points
		// fmaxHp.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));

		main.add(fmaxstamina);
		fmaxstamina.setBounds(200, 18, 100, 16);
		fmaxstamina.setForeground(Color.white);
		fmaxstamina.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		// setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		main.add(fmaxKi);
		fmaxKi.setBounds(150, 34, 100, 16);
		fmaxKi.setForeground(Color.white);
		fmaxKi.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));

		// main.add(PmaxHp);
		// PmaxHp.setBounds(500, 0, 300, 18);
		// PmaxHp.setForeground(Color.white); ////max health points
		// PmaxHp.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));

		main.add(Pmaxstamina);
		Pmaxstamina.setBounds(615, 18, 25, 16);
		Pmaxstamina.setForeground(Color.white);
		Pmaxstamina.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));

		main.add(PmaxKi);
		PmaxKi.setBounds(666, 34, 100, 16);
		PmaxKi.setForeground(Color.white);
		PmaxKi.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		////////////////
		main.add(foblock);
		foblock.setBounds(95, 210, 100, 100);
		foblock.setVisible(false);

		main.add(meblock);
		meblock.setBounds(545, 210, 100, 100);
		//meblock.setIcon(new ImageIcon("senzu.png"));
		meblock.setVisible(false);

		main.add(pname);
		pname.setBounds(650, 150, 150, 25);
		pname.setForeground(Color.white);
		pname.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		pname.setVerticalTextPosition(JLabel.CENTER);

		main.add(plevel);
		plevel.setBounds(670, 180, 150, 25);
		plevel.setForeground(Color.white);
		plevel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		plevel.setVerticalTextPosition(JLabel.CENTER);

		main.add(fname);
		fname.setBounds(30, 150, 150, 25);
		fname.setForeground(Color.white);
		fname.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		fname.setVerticalTextPosition(JLabel.CENTER);

		main.add(flevel);
		flevel.setBounds(10, 180, 150, 25);
		flevel.setForeground(Color.white);
		flevel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		flevel.setVerticalTextPosition(JLabel.CENTER);

		/////////////////////////////////////////////////

		pHealth = new ArrayList<>();
		pStamina = new ArrayList<>();
		pKi = new ArrayList<>();
		fHealth = new ArrayList<>();
		fStamina = new ArrayList<>();
		fKi = new ArrayList<>();

		revalidate();

	}

	public static void main(String[] args) {
		BattleView b = new BattleView();

	}

	public void addListener(GameGUI x) {
		attack.addActionListener(x);
		block.addActionListener(x);
		use.addActionListener(x);
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
		((JButton) e.getSource()).setIcon(new ImageIcon("battlebutton1.png"));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		((JButton) e.getSource()).setIcon(new ImageIcon("battlebutton2.png"));

	}

}
