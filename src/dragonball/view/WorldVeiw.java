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

import dragonball.controller.GameGUI;

public class WorldVeiw extends JFrame {
	public ArrayList<JLabel[]> labels;
	JButton save;
	JButton createNewFighter;
	JButton Switch;
	JButton assignAttack;
	JButton upgrade;
	public WindowDestroyer myListener;
	static ArrayList<JButton> buttons;

	public JLabel world;
	public JLabel l;
	public JLabel fighterPhoto;
	public JLabel fighterName;
	public JLabel playername;
	public JLabel fighterLevel;
	public JLabel sezuNo;
	public JLabel dballsNo;
	public JLabel senzuImage;
	public JLabel dballsImage;

	public WorldVeiw() {
		super();
		setBounds(0, 0, 1366, 744);
		setVisible(true);
		myListener = new WindowDestroyer();
		addWindowListener(myListener);
		labels = new ArrayList<>();
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		for (int i = 0; i < 10; i++) {
			labels.add(new JLabel[10]);
		}

		 l = new JLabel();
		l.setLayout(null);
		Icon k = new ImageIcon("worldmap.png");
		l.setIcon(k);
		world = new JLabel();
		world.setLayout(new GridLayout(10, 10));
		l.add(world);
		world.setBounds(0, 0, 744 + 350, 695);

		// adding labels to map
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				JLabel x = new JLabel();
				world.add(x);
				labels.get(i)[j] = x;

			}
		}

		revalidate();
		// adding options; save,switch, create new fighter,
		// assign attack and upgrade
		buttons = new ArrayList<>();

		save = new JButton("Save");
		save.setActionCommand("save");
		buttons.add(save);
		l.add(save);
		save.setBounds(world.getWidth() - 2, 490, getWidth() - (world.getWidth() - 2), 40);
		save.setContentAreaFilled(false);
		save.setBorderPainted(false);

		assignAttack = new JButton("AssignAttack");
		assignAttack.setActionCommand("assignAttack");
		buttons.add(assignAttack);
		l.add(assignAttack);
		assignAttack.setBounds(world.getWidth() - 2, 490 + 40, getWidth() - (world.getWidth() - 2), 40);
		assignAttack.setContentAreaFilled(false);
		assignAttack.setBorderPainted(false);

		Switch = new JButton("Switch");
		Switch.setActionCommand("switch");
		buttons.add(Switch);
		l.add(Switch);
		Switch.setBounds(world.getWidth() - 2, 490 + 80, getWidth() - (world.getWidth() - 2), 40);
		Switch.setContentAreaFilled(false);
		Switch.setBorderPainted(false);

		upgrade = new JButton("Upgrade");
		upgrade.setActionCommand("upgrade");
		buttons.add(upgrade);
		l.add(upgrade);
		upgrade.setBounds(world.getWidth() - 2, 490 + 120, getWidth() - (world.getWidth() - 2), 40 + 1);
		upgrade.setContentAreaFilled(false);
		upgrade.setBorderPainted(false);

		createNewFighter = new JButton("New Fighter");
		createNewFighter.setActionCommand("createNewFighter");
		buttons.add(createNewFighter);
		l.add(createNewFighter);
		createNewFighter.setBounds(world.getWidth() - 2, 490 + 161, getWidth() - (world.getWidth() - 2), 40 + 6);
		createNewFighter.setContentAreaFilled(false);
		createNewFighter.setBorderPainted(false);

		customize(buttons, "black.png", Color.white);

		// adding informations
		fighterPhoto = new JLabel();
		l.add(fighterPhoto);
		fighterPhoto.setBounds(world.getWidth() - 2, 0, getWidth() - (world.getWidth() - 2), 142);
		fighterName = new JLabel();
		fighterName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		fighterName.setForeground(Color.white);

		fighterLevel = new JLabel();
		fighterLevel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		fighterLevel.setForeground(Color.white);

		playername = new JLabel();
		playername.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		playername.setForeground(Color.white);
		l.add(playername);
		l.add(fighterLevel);
		l.add(fighterName);
		playername.setBounds(world.getWidth() + 10, 202, getWidth() - (world.getWidth() - 2), 40);
		fighterName.setBounds(world.getWidth() + 10, (208 - 142) + 225, getWidth() - (world.getWidth() - 2), 40);
		fighterLevel.setBounds((world.getWidth() + 2) + (getWidth() - (world.getWidth() - 2)) / 2,
				(208 - 142) + 225 + 40, (getWidth() - (world.getWidth() - 2)) / 2, 44);
		senzuImage = new JLabel();
		sezuNo = new JLabel();
		dballsImage = new JLabel();
		dballsNo = new JLabel();
		l.add(senzuImage);
		l.add(sezuNo);
		l.add(dballsImage);
		l.add(dballsNo);

		senzuImage.setBounds(world.getWidth() - 2, 375, (getWidth() - (world.getWidth() - 2)) / 2, 42 + 15);
		dballsImage.setBounds((world.getWidth() - 2) + (getWidth() - (world.getWidth() - 2)) / 2, 375,
				(getWidth() - (world.getWidth() - 2)) / 2, 42 + 15);

		sezuNo.setBounds((world.getWidth() + 25), 432, (getWidth() - (world.getWidth() - 2)) / 2, 58);
		sezuNo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		sezuNo.setForeground(Color.white);

		dballsNo.setBounds((world.getWidth() + 25) + (getWidth() - (world.getWidth() - 2)) / 2, 432,
				(getWidth() - (world.getWidth() - 2)) / 2, 58);
		dballsNo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		dballsNo.setForeground(Color.white);

		add(l);
		revalidate();

	}

	public void addListener(GameGUI x) {
		save.addActionListener(x);
		assignAttack.addActionListener(x);
		Switch.addActionListener(x);
		upgrade.addActionListener(x);
		createNewFighter.addActionListener(x);
		addKeyListener(x);
		setFocusable(true);
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

	public static void main(String[] args) {
		WorldVeiw s = new WorldVeiw();
		s.labels.get(0)[0].setIcon(new ImageIcon("Nappa.gif"));
	}

}
