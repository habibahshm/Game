package dragonball.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import dragonball.controller.GameGUI;
import dragonball.model.attack.Attack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class AssignAttack extends JFrame implements MouseListener {

	public JLabel fighter;
	static JLabel newSuper;
	public JLabel newUltimate;
	JLabel ultimateAttacks;
	JLabel superAttacks;
	public JButton replaces;
	public JButton replaceu;

	public ArrayList<JButton> newsa;
	public ArrayList<JButton> newua;
	public ArrayList<JButton> oldua;
	public ArrayList<JButton> oldsa;

	public AssignAttack(ArrayList<SuperAttack> news, ArrayList<UltimateAttack> newu, ArrayList<UltimateAttack> oldu,
			ArrayList<SuperAttack> olds, GameGUI y) {
		super();
		setLayout(null);
		setBounds(0, 0, 1366, 736);
		setVisible(true);
		JLabel x1 = new JLabel("     fighter super attacks");
		JLabel x2 = new JLabel("         fighter ultimate attacks");
		JLabel x3 = new JLabel("           available super");
		JLabel x4 = new JLabel("         available ultimate");

		add(x3);
		add(x4);
		fighter = new JLabel();
		add(fighter);
		fighter.setBounds(1077, 0, getWidth() / 5, getHeight());
		// System.out.println(fighter.getSize());
		// Icon a = new ImageIcon("bars.jpg");
		newSuper = new JLabel();
		add(newSuper);
		// newSuper.setIcon(a);
		newSuper.setBounds(0, 40, ((getWidth() - fighter.getWidth()) / 4) - 50, 689);
		x3.setBounds(0, 0, ((getWidth() - fighter.getWidth()) / 4) - 50, 40);
		x4.setBounds(x3.getWidth(), 0, ((getWidth() - fighter.getWidth()) / 4) - 50, 40);

		newsa = new ArrayList<>();
		newua = new ArrayList<>();
		oldua = new ArrayList<>();
		oldsa = new ArrayList<>();

		// Icon b = new ImageIcon("saiyan.png");
		newUltimate = new JLabel();
		add(newUltimate);
		// newUltimate.setIcon(b);
		newUltimate.setBounds(newSuper.getWidth(), 40, ((getWidth() - fighter.getWidth()) / 4) - 50, 689);

		// Icon i = new ImageIcon("Frieza.jpg");
		superAttacks = new JLabel();
		add(superAttacks);
		superAttacks.setBounds(newSuper.getWidth() * 2, 80,
				getWidth() - ((newSuper.getWidth() * 2) + fighter.getWidth())-16, 200);
		// superAttacks.setIcon(i);
		add(x1);
		x1.setBounds(newSuper.getWidth() * 2, 0, getWidth() - ((newSuper.getWidth() * 2) + fighter.getWidth())-16, 80);
		ultimateAttacks = new JLabel();
		// ultimateAttacks.setLayout(new GridLayout());
		add(ultimateAttacks);
		ultimateAttacks.setBounds(newSuper.getWidth() * 2, 400,
				getWidth() - ((newSuper.getWidth() * 2) + fighter.getWidth())-16, 200);
		// ultimateAttacks.setIcon(i);
		add(x2);
		x2.setBounds(newSuper.getWidth() * 2, 280, getWidth() - ((newSuper.getWidth() * 2) + fighter.getWidth())-16, 120);
		replaces = new JButton("replace / add Super");
		replaces.setBorderPainted(false);
		replaces.setContentAreaFilled(false);
		replaces.setActionCommand("replaceS");
		add(replaces);
		replaces.setBounds(newSuper.getWidth() * 2, 400 + ultimateAttacks.getHeight(),
				(getWidth() - ((newSuper.getWidth() * 2) + fighter.getWidth())) / 2, 89);
		replaceu = new JButton("replace / add Ultimate");
		replaceu.setContentAreaFilled(false);
		replaceu.setBorderPainted(false);
		replaceu.setActionCommand("replaceu");
		add(replaceu);
		replaceu.setBounds((newSuper.getWidth() * 2) + replaces.getWidth(), 400 + ultimateAttacks.getHeight(),
				(getWidth() - ((newSuper.getWidth() * 2) + fighter.getWidth())) / 2, 89);
		superAttacks.setLayout(new GridLayout(1, 4));
		ultimateAttacks.setLayout(new GridLayout(1, 2));
		if (news.size() > 0) {
			newSuper.setLayout(new GridLayout(news.size(), 1));
			for (SuperAttack attack : news) {
				JButton tmp = new JButton(attack.getName());
				tmp.setActionCommand("newS");
				tmp.addActionListener(y);
				newSuper.add(tmp);
				tmp.setContentAreaFilled(false);
				tmp.setBorderPainted(false);
				tmp.addMouseListener(this);
				newsa.add(tmp);
			}
		} else {
			newSuper.setLayout(new GridLayout());
			JButton tmp = new JButton("No Super Attacks");
			newSuper.add(tmp);
			tmp.setContentAreaFilled(false);
			tmp.setBorderPainted(false);
			// tmp.addActionListener(y);
			tmp.addMouseListener(this);
			newsa.add(tmp);
		}
		if (newu.size() > 0) {
			newUltimate.setLayout(new GridLayout(newu.size(), 1));
			for (UltimateAttack attack : newu) {
				JButton tmp = new JButton(attack.getName());
				tmp.setActionCommand("newU");
				tmp.addActionListener(y);
				tmp.setContentAreaFilled(false);
				tmp.setBorderPainted(false);
				newUltimate.add(tmp);
				tmp.addMouseListener(this);
				newua.add(tmp);
			}
		} else {
			newUltimate.setLayout(new GridLayout());
			JButton tmp = new JButton("No Ultimate Attacks");
			tmp.setContentAreaFilled(false);
			tmp.setBorderPainted(false);
			newUltimate.add(tmp);
			// tmp.addActionListener(y);
			tmp.addMouseListener(this);
			newua.add(tmp);
		}
		if (olds.size() > 0) {
			// superAttacks.setLayout(new GridLayout(olds.size(), 1));
			for (SuperAttack attack : olds) {
				JButton tmp = new JButton(attack.getName());
				tmp.setActionCommand("oldS");
				tmp.addActionListener(y);
				superAttacks.add(tmp);
				tmp.setContentAreaFilled(false);
				tmp.setBorderPainted(false);
				tmp.addMouseListener(this);
				oldsa.add(tmp);
			}
		} else {
			// superAttacks.setLayout(new GridLayout());
			JButton tmp = new JButton("No Super Attacks");
			superAttacks.add(tmp);
			tmp.addMouseListener(this);
			tmp.setContentAreaFilled(false);
			tmp.setBorderPainted(false);
			oldsa.add(tmp);
		}
		if (oldu.size() > 0) {
			// ultimateAttacks.setLayout(new GridLayout(oldu.size(), 1));
			for (UltimateAttack attack : oldu) {
				JButton tmp = new JButton(attack.getName());
				tmp.setActionCommand("oldU");
				tmp.addActionListener(y);
				tmp.setContentAreaFilled(false);
				tmp.setBorderPainted(false);
				ultimateAttacks.add(tmp);
				tmp.addMouseListener(this);
				oldua.add(tmp);
			}
		} else {
			// ultimateAttacks.setLayout(new GridLayout());
			JButton tmp = new JButton("No Ultimate Attacks");
			tmp.setContentAreaFilled(false);
			tmp.setBorderPainted(false);
			ultimateAttacks.add(tmp);
			tmp.addMouseListener(this);
			oldua.add(tmp);
		}

		Icon rep = new ImageIcon(new ImageIcon("replace.jpg").getImage().getScaledInstance(replaces.getWidth(),
				replaces.getHeight(), Image.SCALE_SMOOTH));
		replaces.setIcon(rep);
		replaces.setHorizontalTextPosition(JButton.CENTER);
		replaces.setVerticalTextPosition(JButton.CENTER);
		replaces.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));

		Icon rep1 = new ImageIcon(new ImageIcon("replace.jpg").getImage().getScaledInstance(replaceu.getWidth(),
				replaceu.getHeight(), Image.SCALE_SMOOTH));
		replaceu.setIcon(rep1);
		replaceu.setHorizontalTextPosition(JButton.CENTER);
		replaceu.setVerticalTextPosition(JButton.CENTER);
		replaceu.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));

		Icon rep2 = new ImageIcon(new ImageIcon("upgrade.jpg").getImage().getScaledInstance(x1.getWidth(),
				x1.getHeight(), Image.SCALE_SMOOTH));
		x1.setIcon(rep2);
		x1.setHorizontalTextPosition(JButton.CENTER);
		x1.setVerticalTextPosition(JButton.CENTER);
		x1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));

		rep2 = new ImageIcon(new ImageIcon("upgrade.jpg").getImage().getScaledInstance(x2.getWidth(), x2.getHeight(),
				Image.SCALE_SMOOTH));
		x2.setIcon(rep2);
		x2.setHorizontalTextPosition(JButton.CENTER);
		x2.setVerticalTextPosition(JButton.CENTER);
		x2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		x2.setForeground(Color.white);

		rep2 = new ImageIcon(new ImageIcon("upgrade.jpg").getImage().getScaledInstance(x3.getWidth(), x3.getHeight(),
				Image.SCALE_SMOOTH));
		x3.setIcon(rep2);
		x3.setHorizontalTextPosition(JButton.CENTER);
		x3.setVerticalTextPosition(JButton.CENTER);
		x3.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
		x3.setForeground(Color.white);
		rep2 = new ImageIcon(new ImageIcon("upgrade.jpg").getImage().getScaledInstance(x4.getWidth(), x4.getHeight(),
				Image.SCALE_SMOOTH));
		x4.setIcon(rep2);
		x4.setHorizontalTextPosition(JButton.CENTER);
		x4.setVerticalTextPosition(JButton.CENTER);
		x4.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
		x4.setForeground(Color.white);
		x1.setForeground(Color.white);
		revalidate();
	}

//	 public static void main(String[] args) {
//	 AssignAttack n = new AssignAttack(null,null,null,null,null);
//	// System.out.println(n.newSuper.getSize());
//	 }

	public void customize(ArrayList<JButton> x, String photo, Color textcolor) {
		// System.out.println(x.toString());
		// System.out.println("Ffffff");
		// .out.println(x.size());
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
		JButton tmp = (JButton) e.getSource();
		Icon i = new ImageIcon(((new ImageIcon("black.png").getImage().getScaledInstance(tmp.getWidth(),
				tmp.getHeight(), java.awt.Image.SCALE_SMOOTH))));
		tmp.setIcon(i);
		tmp.setForeground(Color.white);
		tmp.setHorizontalTextPosition(JButton.CENTER);
		tmp.setVerticalTextPosition(JButton.CENTER);
	}

	public void addNewSuper(ArrayList<SuperAttack> x, GameGUI y) {

		// System.out.println();
		superAttacks.removeAll();
		superAttacks.setLayout(new GridLayout(x.size(), 1));
		oldsa = new ArrayList<>();
		for (SuperAttack superAttack : x) {
			JButton tmp = new JButton(superAttack.getName());
			superAttacks.add(tmp);
			tmp.setActionCommand("oldS");
			tmp.addActionListener(y);
			tmp.setContentAreaFilled(false);
			tmp.setBorderPainted(false);
			tmp.addMouseListener(this);
			oldsa.add(tmp);
		}
		revalidate();
		// for (int i = 0; i < superAttacks.getComponentCount(); i++) {
		// oldsa.add((JButton) superAttacks.getComponent(i));
		// }
		// System.out.println(superAttacks.getComponentCount() + "after");
	}

	public void addNewUltimate(ArrayList<UltimateAttack> x, GameGUI y) {
		ultimateAttacks.removeAll();
		ultimateAttacks.setLayout(new GridLayout(x.size(), 1));
		oldua = new ArrayList<>();
		for (UltimateAttack attack : x) {
			JButton tmp = new JButton(attack.getName());
			tmp.setActionCommand("oldU");
			tmp.addActionListener(y);
			tmp.setContentAreaFilled(false);
			tmp.setBorderPainted(false);
			ultimateAttacks.add(tmp);
			tmp.addMouseListener(this);
			oldua.add(tmp);
		}
		revalidate();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}