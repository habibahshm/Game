package dragonball.controller;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.Timer;

import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener;

import dragonball.model.attack.Attack;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.battle.BattleListener;
import dragonball.model.cell.Collectible;
import dragonball.model.cell.FoeCell;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;
import dragonball.view.AssignAttack;
import dragonball.view.BattleView;
import dragonball.view.Battle_ended;
import dragonball.view.Battle_ended_levelup;
import dragonball.view.DragonVeiw;
import dragonball.view.DuplicateAttack;
import dragonball.view.Exit;
import dragonball.view.Lost;
import dragonball.view.MaximumAttacks;
import dragonball.view.MissingFields;
import dragonball.view.NewFighter;
import dragonball.view.NewGame;
import dragonball.view.NotASaiyan;
import dragonball.view.NotEnoughAbility;
import dragonball.view.NotEnoughKi;
import dragonball.view.NotEnoughSenzuBeans;
import dragonball.view.SUattack;
import dragonball.view.Sattack;
import dragonball.view.Save;
import dragonball.view.SavedGames;
import dragonball.view.Start;
import dragonball.view.Switch;
import dragonball.view.Uattack;
import dragonball.view.UnknownAttack;
import dragonball.view.Upgrade;
import dragonball.view.WishGranted;
import dragonball.view.WorldVeiw;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class GameGUI implements ActionListener, KeyListener, GameListener, MouseListener {

	static ArrayList<String> savedGames = new ArrayList<>();
	final static String[] foes = { "Burter", "Captain Ginyu", "Jeice", "Zarbon", "Vegeta", "Guko", "Nappa", "Raditz",
			"Recoome" };
	String foeBattle = "";
	static Game game;
	Start startView;
	NewGame newGameView;
	WorldVeiw worldView;
	static String fighterName;
	Save saveView;
	Switch switchView;
	Upgrade upgradeView;
	NewFighter newFighterView;
	SavedGames savedGameView;
	BattleView battleView;
	Battle battle;
	AssignAttack assignAttackView;
	DragonVeiw dragonveiw;
	String oldAttack;
	String newAttack;
	Timer timer;
	Sattack sattack;
	Uattack uattack;
	SUattack su;
	int level;
	Battle_ended_levelup bu;
	Battle_ended be;
	int xp = 0;
	int yp = 0;
	boolean winner;
	boolean boss;
	Dragon dragon;
	WishGranted wishGranted;
	JFrame x;
	DuplicateAttack d;
	MaximumAttacks max;
	NotEnoughAbility re;
	NotASaiyan mi;
	MissingFields missing;
	UnknownAttack att;
	NotEnoughKi k;
	NotEnoughSenzuBeans b;

	public GameGUI() {
		savedGames = new ArrayList<>();
		load();
		startView = new Start();
		startView.addListener(this);
		// timer = new Timer(2000, this);
		// timer.setActionCommand("timer");
		music();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof Timer) {
			Timer v = (Timer) e.getSource();
			if (e.getActionCommand().equals("turn")) {
				if (battleView.isVisible())
					if (battleView.turn.isVisible())
						battleView.turn.setVisible(false);
				v.stop();
			} else if (e.getActionCommand().equals("fplay")) {
				try {
					battle.play();
				} catch (NotEnoughKiException y) {
					try {
						battle.attack((Attack) new PhysicalAttack());
					} catch (NotEnoughKiException r) {

					}
				}
				v.stop();
			} else if (e.getActionCommand().equals("fturn")) {
				String fighterPhotoWorld = "";
				if (game.getPlayer().getActiveFighter() instanceof Saiyan)
					fighterPhotoWorld = "Saiyan";
				else if (game.getPlayer().getActiveFighter() instanceof Namekian)
					fighterPhotoWorld = "Namekian";
				else if (game.getPlayer().getActiveFighter() instanceof Majin)
					fighterPhotoWorld = "Majin";
				else if (game.getPlayer().getActiveFighter() instanceof Frieza)
					fighterPhotoWorld = "Frieza";
				else if (game.getPlayer().getActiveFighter() instanceof Earthling)
					fighterPhotoWorld = "Earthling";
				battleView.fighter.setIcon(new ImageIcon(fighterPhotoWorld + "Battle.gif"));
				battleView.foblock.setVisible(false);
				battleView.revalidate();
				v.stop();
			} else if (e.getActionCommand().equals("pturn")) {
				battleView.foe.setIcon(new ImageIcon(foeBattle + ".gif"));
				battleView.revalidate();
				battleView.meblock.setVisible(false);
				v.stop();
			} else if (e.getActionCommand().equals("show")) {
				battleView.turn.setVisible(true);
				v.stop();
			} else if (e.getActionCommand().equals("closeskillsup")) {
				bu.dispose();
				v.stop();
			} else if (e.getActionCommand().equals("closeskills")) {
				be.dispose();
				v.stop();
			} else if (e.getActionCommand().equals("closewish")) {
				wishGranted.dispose();
				v.stop();
			} else if (e.getActionCommand().equals("closeCollectible")) {
				x.dispose();
				v.stop();
			} else if (e.getActionCommand().equals("closeDuplicateAttack")) {
				d.dispose();
				v.stop();
			} else if (e.getActionCommand().equals("closeMaximumAttacks")) {
				max.dispose();
				v.stop();
			} else if (e.getActionCommand().equals("closeNotASaiyan")) {
				mi.dispose();
				v.stop();
			} else if (e.getActionCommand().equals("closeMissingField")) {
				missing.dispose();
				v.stop();
			} else if (e.getActionCommand().equals("closeUnknownAttack")) {
				att.dispose();
				v.stop();
			} else if (e.getActionCommand().equals("closeNotEnoughAbility")) {
				re.dispose();
				v.stop();
			} else if (e.getActionCommand().equals("closeNotEnoughKi")) {
				k.dispose();
				v.stop();
			} else if (e.getActionCommand().equals("closeNotEnoughSenzuBeans")) {
				b.dispose();
				v.stop();
			}
		} else if (e.getSource() instanceof JButton) {
			JButton x = (JButton) e.getSource();
			if (e.getActionCommand().equals("start")) {
				newGameView = new NewGame();
				try {
					game = new Game();
					game.setListener(this);
				} catch (MissingFieldException e1) {
					missing = new MissingFields();
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeMissingField");
					tt.start();
				} catch (UnknownAttackTypeException e2) {
					att = new UnknownAttack();
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeUnknoenAttack");
					tt.start();
				}
				newGameView.addListener(this);
				startView.dispose();
			} else if (x.getActionCommand().equals("savedGame")) {
				try {
					game = new Game();
				} catch (MissingFieldException e1) {
					missing = new MissingFields();
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeMissingField");
					tt.start();
				} catch (UnknownAttackTypeException e2) {
					att = new UnknownAttack();
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeUnknoenAttack");
					tt.start();
				}
				game.load(x.getText());// fa kan by3mel null pointer exception
										// hena.

				String fighterPhotoWorld = "";
				if (game.getPlayer().getActiveFighter() instanceof Saiyan)
					fighterPhotoWorld = "Saiyan";
				else if (game.getPlayer().getActiveFighter() instanceof Namekian)
					fighterPhotoWorld = "Namekian";
				else if (game.getPlayer().getActiveFighter() instanceof Majin)
					fighterPhotoWorld = "Majin";
				else if (game.getPlayer().getActiveFighter() instanceof Frieza)
					fighterPhotoWorld = "Frieza";
				else if (game.getPlayer().getActiveFighter() instanceof Earthling)
					fighterPhotoWorld = "Earthling";
				worldView = new WorldVeiw();
				worldView.addListener(this);
				worldView.fighterLevel.setText(game.getPlayer().getActiveFighter().getLevel() + "");
				worldView.fighterName.setText(game.getPlayer().getActiveFighter().getName());
				worldView.playername.setText(game.getPlayer().getName());
				worldView.fighterPhoto.setIcon(new ImageIcon(fighterPhotoWorld + "1.png"));
				(worldView.labels.get(game.getWorld().getPlayerRow())[game.getWorld().getPlayerColumn()])
						.setIcon(new ImageIcon(fighterPhotoWorld + "Map.gif"));
				int foeidx = new Random().nextInt(9);
				worldView.labels.get(0)[0].setIcon(new ImageIcon(foes[foeidx] + "Map.gif"));
				worldView.sezuNo.setText("" + game.getPlayer().getSenzuBeans());
				worldView.dballsNo.setText("" + game.getPlayer().getDragonBalls());
				startView.dispose();
				savedGameView.dispose();
				worldView.requestFocus();

			} else if (x.getActionCommand().equals("load")) {
				savedGameView = new SavedGames(savedGames, this);
			}

			else if (x.getActionCommand().equals("saiyan")) {
				Saiyan tmp = new Saiyan(fighterName);
				game.getPlayer().setActiveFighter(tmp);
				game.getPlayer().getFighters().add(tmp);
				game.getPlayer().setDragonBalls(6);
				// game.getPlayer().getActiveFighter().setAbilityPoints(10);
				worldView = new WorldVeiw();
				worldView.addListener(this);
				worldView.fighterLevel.setText("" + tmp.getLevel());
				worldView.fighterName.setText(tmp.getName());
				worldView.playername.setText(game.getPlayer().getName());
				worldView.fighterPhoto.setIcon(new ImageIcon("saiyan1.png"));
				worldView.labels.get(game.getWorld().getPlayerRow())[game.getWorld().getPlayerColumn()]
						.setIcon(new ImageIcon("SaiyanMap.gif"));
				int foeidx = new Random().nextInt(9);
				worldView.labels.get(0)[0].setIcon(new ImageIcon(foes[foeidx] + "Map.gif"));
				worldView.sezuNo.setText("" + game.getPlayer().getSenzuBeans());
				worldView.dballsNo.setText("" + game.getPlayer().getDragonBalls());
				newGameView.dispose();
			} else if (x.getActionCommand().equals("namekian")) {
				newGameView.dispose();
				Namekian tmp = new Namekian(fighterName);
				game.getPlayer().setActiveFighter(tmp);
				game.getPlayer().getFighters().add(tmp);
				worldView = new WorldVeiw();
				worldView.addListener(this);
				worldView.fighterLevel.setText("" + tmp.getLevel());
				worldView.fighterName.setText(tmp.getName());
				worldView.playername.setText(game.getPlayer().getName());
				worldView.fighterPhoto.setIcon(new ImageIcon("Namekian1.png"));
				worldView.labels.get(game.getWorld().getPlayerRow())[game.getWorld().getPlayerColumn()]
						.setIcon(new ImageIcon("NamekianMap.gif"));
				int foeidx = new Random().nextInt(9);
				worldView.labels.get(0)[0].setIcon(new ImageIcon(foes[foeidx] + "Map.gif"));
				worldView.sezuNo.setText("" + game.getPlayer().getSenzuBeans());
				worldView.dballsNo.setText("" + game.getPlayer().getDragonBalls());
				newGameView.dispose();

			} else if (x.getActionCommand().equals("majin")) {
				FoeCell f = (FoeCell) game.getWorld().getMap()[0][0];
				Majin tmp = new Majin(fighterName);
				game.getPlayer().setActiveFighter(tmp);
				game.getPlayer().getFighters().add(tmp);
				// game.getPlayer().setDragonBalls(6);
				worldView = new WorldVeiw();
				worldView.addListener(this);
				worldView.fighterLevel.setText("" + tmp.getLevel());
				worldView.fighterName.setText(tmp.getName());
				worldView.playername.setText(game.getPlayer().getName());
				worldView.fighterPhoto.setIcon(new ImageIcon("Majin1.png"));
				worldView.labels.get(game.getWorld().getPlayerRow())[game.getWorld().getPlayerColumn()]
						.setIcon(new ImageIcon("MajinMap.gif"));
				worldView.labels.get(0)[0].setIcon(new ImageIcon(f.getFoe().getName() + ".gif"));
				worldView.sezuNo.setText("" + game.getPlayer().getSenzuBeans());
				worldView.dballsNo.setText("" + game.getPlayer().getDragonBalls());

				newGameView.dispose();
			} else if (x.getActionCommand().equals("frieza")) {
				Frieza tmp = new Frieza(fighterName);
				game.getPlayer().setActiveFighter(tmp);
				game.getPlayer().getFighters().add(tmp);
				worldView = new WorldVeiw();
				worldView.addListener(this);
				worldView.fighterLevel.setText("" + tmp.getLevel());
				worldView.fighterName.setText(tmp.getName());
				worldView.playername.setText(game.getPlayer().getName());
				worldView.fighterPhoto.setIcon(new ImageIcon("Frieza1.png"));
				worldView.labels.get(game.getWorld().getPlayerRow())[game.getWorld().getPlayerColumn()]
						.setIcon(new ImageIcon("FriezaMap.gif"));
				int foeidx = new Random().nextInt(9);
				worldView.labels.get(0)[0].setIcon(new ImageIcon(foes[foeidx] + "Map.gif"));
				worldView.sezuNo.setText("" + game.getPlayer().getSenzuBeans());
				worldView.dballsNo.setText("" + game.getPlayer().getDragonBalls());
				newGameView.dispose();
			} else if (x.getActionCommand().equals("earthling")) {
				Earthling tmp = new Earthling(fighterName);
				game.getPlayer().setActiveFighter(tmp);
				game.getPlayer().getFighters().add(tmp);
				worldView = new WorldVeiw();
				worldView.addListener(this);
				worldView.fighterLevel.setText("" + tmp.getLevel());
				worldView.fighterName.setText(tmp.getName());
				worldView.playername.setText(game.getPlayer().getName());
				worldView.fighterPhoto.setIcon(new ImageIcon("Earthling1.png"));
				worldView.labels.get(game.getWorld().getPlayerRow())[game.getWorld().getPlayerColumn()]
						.setIcon(new ImageIcon("EarthlingMap.gif"));
				int foeidx = new Random().nextInt(9);
				worldView.labels.get(0)[0].setIcon(new ImageIcon(foes[foeidx] + "Map.gif"));
				worldView.sezuNo.setText("" + game.getPlayer().getSenzuBeans());
				worldView.dballsNo.setText("" + game.getPlayer().getDragonBalls());
				newGameView.dispose();

			} else if (x.getActionCommand().equals("save")) {
				saveView = new Save();
				saveView.addListener(this);
				worldView.requestFocus();
			} else if (x.getActionCommand().equals("assignAttack")) {
				String fighterPhoto = "";
				if (game.getPlayer().getActiveFighter() instanceof Saiyan)
					fighterPhoto = "Saiyan";
				else if (game.getPlayer().getActiveFighter() instanceof Namekian)
					fighterPhoto = "Namekian";
				else if (game.getPlayer().getActiveFighter() instanceof Majin)
					fighterPhoto = "Majin";
				else if (game.getPlayer().getActiveFighter() instanceof Frieza)
					fighterPhoto = "Frieza";
				else if (game.getPlayer().getActiveFighter() instanceof Earthling)
					fighterPhoto = "Earthling";
				assignAttackView = new AssignAttack(game.getPlayer().getSuperAttacks(),
						game.getPlayer().getUltimateAttacks(), game.getPlayer().getActiveFighter().getUltimateAttacks(),
						game.getPlayer().getActiveFighter().getSuperAttacks(), this);
				assignAttackView.replaces.addActionListener(this);
				assignAttackView.replaceu.addActionListener(this);
				Icon rep = new ImageIcon(fighterPhoto + "A.gif");
				assignAttackView.fighter.setIcon(rep);
				assignAttackView.customize(assignAttackView.newsa, "white.png", Color.black);
				assignAttackView.customize(assignAttackView.newua, "white.png", Color.black);
				assignAttackView.customize(assignAttackView.oldsa, "white.png", Color.black);
				assignAttackView.customize(assignAttackView.oldua, "white.png", Color.black);
				worldView.requestFocus();
			} else if (x.getActionCommand().equals("newS")) {
				newAttack = x.getText();
			} else if (x.getActionCommand().equals("newU")) {
				newAttack = x.getText();
			} else if (x.getActionCommand().equals("oldS")) {
				oldAttack = x.getText();
			} else if (x.getActionCommand().equals("oldU")) {
				oldAttack = x.getText();
			} else if (x.getActionCommand().equals("replaceS")) {
				SuperAttack old = null;
				SuperAttack New = null;
				for (int i = 0; i < game.getPlayer().getActiveFighter().getSuperAttacks().size(); i++) {
					if (game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName().equals(oldAttack)) {
						old = game.getPlayer().getActiveFighter().getSuperAttacks().get(i);
						break;
					}
				}
				for (int i = 0; i < game.getPlayer().getSuperAttacks().size(); i++) {
					if (game.getPlayer().getSuperAttacks().get(i).getName().equals(newAttack)) {
						New = game.getPlayer().getSuperAttacks().get(i);
						break;
					}
				}
				try {
					game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(), New, old);
				} catch (DuplicateAttackException du) {
					d = new DuplicateAttack();
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeDuplicateAttack");
					tt.start();
				} catch (MaximumAttacksLearnedException ma) {
					max = new MaximumAttacks();
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeMaximumAttacks");
					tt.start();
				}
				assignAttackView.addNewSuper(game.getPlayer().getActiveFighter().getSuperAttacks(), this);
				assignAttackView.customize(assignAttackView.oldsa, "white.png", Color.black);
				assignAttackView.customize(assignAttackView.newsa, "white.png", Color.black);

				assignAttackView.revalidate();

			} else if (x.getActionCommand().equals("replaceu")) {
				UltimateAttack old = null;
				UltimateAttack New = null;
				for (int i = 0; i < game.getPlayer().getActiveFighter().getUltimateAttacks().size(); i++) {
					if (game.getPlayer().getActiveFighter().getUltimateAttacks().get(i).getName().equals(oldAttack)) {
						old = game.getPlayer().getActiveFighter().getUltimateAttacks().get(i);
						break;
					}
				}
				for (int i = 0; i < game.getPlayer().getUltimateAttacks().size(); i++) {
					if (game.getPlayer().getUltimateAttacks().get(i).getName().equals(newAttack)) {
						New = game.getPlayer().getUltimateAttacks().get(i);
						break;
					}
				}
				try {
					game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(), New, old);
				} catch (DuplicateAttackException du) {
					d = new DuplicateAttack();
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeDuplicateAttack");
					tt.addActionListener(this);
					tt.start();
				} catch (MaximumAttacksLearnedException ma) {
					max = new MaximumAttacks();
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeMaximumAttacks");
					tt.addActionListener(this);
					tt.start();
				} catch (NotASaiyanException m) {
					mi = new NotASaiyan();
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeNotASaiyan");
					tt.addActionListener(this);
					tt.start();
				}
				assignAttackView.addNewUltimate(game.getPlayer().getActiveFighter().getUltimateAttacks(), this);
				assignAttackView.customize(assignAttackView.newua, "white.png", Color.black);
				assignAttackView.customize(assignAttackView.oldua, "white.png", Color.black);
				assignAttackView.revalidate();

			} else if (x.getActionCommand().equals("switch")) {
				PlayableFighter tmp = game.getPlayer().getActiveFighter();
				String fighterPhoto = "";
				if (tmp instanceof Saiyan)
					fighterPhoto = "SaiyanS.png";
				else if (tmp instanceof Namekian)
					fighterPhoto = "NamekianS.png";
				else if (tmp instanceof Majin)
					fighterPhoto = "MajinS.png";
				else if (tmp instanceof Frieza)
					fighterPhoto = "FriezaS.png";
				else if (tmp instanceof Earthling)
					fighterPhoto = "EarthlingS.png";
				switchView = new Switch();
				switchView.addFighters(game.getPlayer().getFighters(), this);
				switchView.photo.setIcon(new ImageIcon(fighterPhoto));
				switchView.customize(switchView.buttons);
				switchView.revalidate();
				worldView.requestFocus();

			} else if (x.getActionCommand().equals("switchF")) {
				String name = x.getText();
				PlayableFighter tmp = null;
				for (int i = 0; i < game.getPlayer().getFighters().size(); i++) {
					if (game.getPlayer().getFighters().get(i).getName().equals(name)) {
						tmp = game.getPlayer().getFighters().get(i);
						break;
					}
				}
				game.getPlayer().setActiveFighter(tmp);
				worldView.fighterLevel.setText("" + tmp.getLevel());

				String fighterPhoto = "";
				if (tmp instanceof Saiyan)
					fighterPhoto = "Saiyan";
				else if (tmp instanceof Namekian)
					fighterPhoto = "Namekian";
				else if (tmp instanceof Majin)
					fighterPhoto = "Majin";
				else if (tmp instanceof Frieza)
					fighterPhoto = "Frieza";
				else if (tmp instanceof Earthling)
					fighterPhoto = "Earthling";
				worldView.fighterPhoto.setIcon(new ImageIcon(fighterPhoto+"1.png"));
				worldView.labels.get(game.getWorld().getPlayerRow())[game.getWorld().getPlayerColumn()]
						.setIcon(new ImageIcon(fighterPhoto + "Map.gif"));
				worldView.fighterName.setText(name);
				switchView.dispose();
				worldView.requestFocus();
				worldView.revalidate();

			} else if (x.getActionCommand().equals("createNewFighter")) {
				newFighterView = new NewFighter();
				newFighterView.addListener(this);
				worldView.requestFocus();
			} else if (x.getActionCommand().equals("saiyanf")) {
				PlayableFighter tmp = new Saiyan(fighterName);
				game.getPlayer().getFighters().add(tmp);
				game.getPlayer().setActiveFighter(tmp);
				worldView.fighterName.setText(fighterName);
				worldView.fighterPhoto.setIcon(new ImageIcon("Saiyan1.png"));
				worldView.labels.get(game.getWorld().getPlayerRow())[game.getWorld().getPlayerColumn()]
						.setIcon(new ImageIcon("SaiyanMap.gif"));
				worldView.fighterLevel.setText("1");
				newFighterView.dispose();
				worldView.requestFocus();

			} else if (x.getActionCommand().equals("namekianf")) {
				PlayableFighter tmp = new Namekian(fighterName);
				game.getPlayer().getFighters().add(tmp);
				game.getPlayer().setActiveFighter(tmp);
				worldView.fighterName.setText(fighterName);
				worldView.fighterPhoto.setIcon(new ImageIcon("Namekian1.png"));
				worldView.labels.get(game.getWorld().getPlayerRow())[game.getWorld().getPlayerColumn()]
						.setIcon(new ImageIcon("NamekianMap.gif"));
				worldView.fighterLevel.setText("1");
				newFighterView.dispose();
				worldView.requestFocus();

			} else if (x.getActionCommand().equals("friezaf")) {
				PlayableFighter tmp = new Frieza(fighterName);
				game.getPlayer().getFighters().add(tmp);
				game.getPlayer().setActiveFighter(tmp);
				worldView.fighterName.setText(fighterName);
				worldView.fighterPhoto.setIcon(new ImageIcon("Frieza1.png"));
				worldView.labels.get(game.getWorld().getPlayerRow())[game.getWorld().getPlayerColumn()]
						.setIcon(new ImageIcon("FriezaMap.gif"));
				worldView.fighterLevel.setText("1");
				newFighterView.dispose();
				worldView.requestFocus();
			} else if (x.getActionCommand().equals("majinf")) {
				PlayableFighter tmp = new Majin(fighterName);
				game.getPlayer().getFighters().add(tmp);
				game.getPlayer().setActiveFighter(tmp);
				worldView.fighterName.setText(fighterName);
				worldView.fighterPhoto.setIcon(new ImageIcon("Majin1.png"));
				worldView.fighterLevel.setText("1");
				worldView.labels.get(game.getWorld().getPlayerRow())[game.getWorld().getPlayerColumn()]
						.setIcon(new ImageIcon("MajinMap.gif"));
				newFighterView.dispose();
				worldView.requestFocus();
			} else if (x.getActionCommand().equals("earthlingf")) {
				PlayableFighter tmp = new Earthling(fighterName);
				game.getPlayer().getFighters().add(tmp);
				game.getPlayer().setActiveFighter(tmp);
				worldView.fighterName.setText(fighterName);
				worldView.fighterPhoto.setIcon(new ImageIcon("Earthling1.png"));
				worldView.fighterLevel.setText("1");
				worldView.labels.get(game.getWorld().getPlayerRow())[game.getWorld().getPlayerColumn()]
						.setIcon(new ImageIcon("EarthlingMap.gif"));
				newFighterView.dispose();
				worldView.requestFocus();
			} else if (x.getActionCommand().equals("upgrade")) {
				upgradeView = new Upgrade();
				upgradeView.addListener(this);
				upgradeView.abilityPointsNo.setText(game.getPlayer().getActiveFighter().getAbilityPoints() + "");
				upgradeView.physicalDamagaPoints.setText(game.getPlayer().getActiveFighter().getPhysicalDamage() + "");
				upgradeView.blastDamagaPoints.setText(game.getPlayer().getActiveFighter().getBlastDamage() + "");
				upgradeView.maxHealthpointsNo.setText(game.getPlayer().getActiveFighter().getMaxHealthPoints() + "");
				upgradeView.maxKiPoints.setText(game.getPlayer().getActiveFighter().getMaxKi() + "");
				upgradeView.staminaPoints.setText(game.getPlayer().getActiveFighter().getMaxStamina() + "");
				upgradeView.customize(upgradeView.buttons, "white.png", Color.black);
				upgradeView.customizel(upgradeView.labels);
				String fighterPhoto = "";
				if (game.getPlayer().getActiveFighter() instanceof Saiyan)
					fighterPhoto = "SaiyanG.png";
				else if (game.getPlayer().getActiveFighter() instanceof Namekian)
					fighterPhoto = "NamekianG.png";
				else if (game.getPlayer().getActiveFighter() instanceof Majin)
					fighterPhoto = "MajinG.png";
				else if (game.getPlayer().getActiveFighter() instanceof Frieza)
					fighterPhoto = "FriezaG.png";
				else if (game.getPlayer().getActiveFighter() instanceof Earthling)
					fighterPhoto = "EarthlingG.png";
				upgradeView.fighter.setContentAreaFilled(false);
				upgradeView.fighter.setIcon(new ImageIcon(fighterPhoto));
				worldView.requestFocus();
			} else if (x.getActionCommand().equals("upgrade health")) {
				try {
					game.getPlayer().upgradeFighter((PlayableFighter) game.getPlayer().getActiveFighter(), 'H');
				} catch (NotEnoughAbilityPointsException r) {
					re = new NotEnoughAbility();
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeNotEnoughAbility");
					tt.start();
				}
				upgradeView.maxHealthpointsNo.setText("" + game.getPlayer().getActiveFighter().getMaxHealthPoints());
				upgradeView.abilityPointsNo.setText(game.getPlayer().getActiveFighter().getAbilityPoints() + "");
			} else if (x.getActionCommand().equals("upgrade stamina")) {
				try {
					game.getPlayer().upgradeFighter((PlayableFighter) game.getPlayer().getActiveFighter(), 'S');
				} catch (NotEnoughAbilityPointsException r) {
					re = new NotEnoughAbility();
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeNotEnoughAbility");
					tt.start();
				}
				upgradeView.staminaPoints.setText("" + game.getPlayer().getActiveFighter().getMaxStamina());
				upgradeView.abilityPointsNo.setText(game.getPlayer().getActiveFighter().getAbilityPoints() + "");
			} else if (x.getActionCommand().equals("upgrade ki")) {
				try {
					game.getPlayer().upgradeFighter((PlayableFighter) game.getPlayer().getActiveFighter(), 'K');
				} catch (NotEnoughAbilityPointsException r) {
					re = new NotEnoughAbility();
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeNotEnoughAbility");
					tt.start();
				}
				upgradeView.maxKiPoints.setText("" + game.getPlayer().getActiveFighter().getMaxKi());
				upgradeView.abilityPointsNo.setText(game.getPlayer().getActiveFighter().getAbilityPoints() + "");
			} else if (x.getActionCommand().equals("upgrade physical")) {
				try {
					game.getPlayer().upgradeFighter((PlayableFighter) game.getPlayer().getActiveFighter(), 'P');
				} catch (NotEnoughAbilityPointsException r) {
					re = new NotEnoughAbility();
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeNotEnoughAbility");
					tt.start();
				}
				upgradeView.physicalDamagaPoints.setText("" + game.getPlayer().getActiveFighter().getPhysicalDamage());
				upgradeView.abilityPointsNo.setText(game.getPlayer().getActiveFighter().getAbilityPoints() + "");
			} else if (x.getActionCommand().equals("upgrade blast")) {
				try {
					game.getPlayer().upgradeFighter((PlayableFighter) game.getPlayer().getActiveFighter(), 'B');
				} catch (NotEnoughAbilityPointsException r) {
					re = new NotEnoughAbility();
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeNotEnoughAbility");
					tt.start();
				}
				upgradeView.blastDamagaPoints.setText("" + game.getPlayer().getActiveFighter().getBlastDamage());
				upgradeView.abilityPointsNo.setText(game.getPlayer().getActiveFighter().getAbilityPoints() + "");
			} else if (e.getActionCommand().equals("senzuWish")) {
				int v = Integer.parseInt(dragonveiw.senzuNumber.getText());
				game.getPlayer().setSenzuBeans(game.getPlayer().getSenzuBeans() + v);
				wishGranted = new WishGranted();
				wishGranted.wish.setText("You have been granted \n" + v + " senzu beans.");
				dragonveiw.dispose();
				Timer tt = new Timer(4000, this);
				tt.setActionCommand("closewish");
				tt.start();
				worldView.sezuNo.setText(game.getPlayer().getSenzuBeans() + "");
			} else if (e.getActionCommand().equals("abilityWish")) {
				int v = Integer.parseInt(dragonveiw.abilityNumber.getText());
				game.getPlayer().getActiveFighter()
						.setAbilityPoints((game.getPlayer().getActiveFighter().getAbilityPoints() + v));
				wishGranted = new WishGranted();
				wishGranted.wish.setText("You have been granted \n" + v + " ability points.");
				dragonveiw.dispose();
				Timer tt = new Timer(4000, this);
				tt.setActionCommand("closewish");
				tt.start();
			} else if (e.getActionCommand().equals("ultimateWish")) {
				DragonWish[] tmp = dragon.getWishes();
				game.getPlayer().chooseWish(tmp[3]);
				wishGranted = new WishGranted();
				wishGranted.wish
						.setText("You have been granted the \n" + tmp[3].getUltimateAttack().getName() + " attack");
				dragonveiw.dispose();
				Timer tt = new Timer(4000, this);
				tt.setActionCommand("closewish");
				tt.start();
			} else if (e.getActionCommand().equals("superWish")) {
				DragonWish[] tmp = dragon.getWishes();
				game.getPlayer().chooseWish(tmp[2]);
				wishGranted = new WishGranted();
				wishGranted.wish
						.setText("You have been granted the \n" + tmp[2].getSuperAttack().getName() + " attack");
				dragonveiw.dispose();
				Timer tt = new Timer(4000, this);
				tt.setActionCommand("closewish");
				tt.start();
			} else if (x.getActionCommand().equals("attack") && battle.getAttacker() instanceof PlayableFighter) {
				su = new SUattack();
				su.customize(su.buttons, "white.png", Color.black);
				su.addListener(this);
			} else if (x.getActionCommand().equals("SAttack")) {
				sattack = new Sattack(game.getPlayer().getActiveFighter().getSuperAttacks());
				sattack.customize(sattack.buttons, "white.png", Color.black);
				sattack.addListener(this);
				su.dispose();
			} else if (x.getActionCommand().equals("UAttack")) {
				uattack = new Uattack(game.getPlayer().getActiveFighter().getUltimateAttacks());
				uattack.customize(uattack.buttons, "white.png", Color.black);
				uattack.addListener(this);
				su.dispose();
			} else if (x.getActionCommand().equals("PAttack")) {
				try {
					battle.attack((Attack) new PhysicalAttack());
				} catch (NotEnoughKiException r) {
					k = new NotEnoughKi();
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeNotEnoughKi");
					tt.start();
				}
				su.dispose();
			} else if (x.getActionCommand().equals("fs")) {
				String name = x.getText();
				Attack tmp = null;
				for (int i = 0; i < game.getPlayer().getActiveFighter().getSuperAttacks().size(); i++) {
					if (game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName().equals(name)) {
						tmp = game.getPlayer().getActiveFighter().getSuperAttacks().get(i);
						break;
					}

				}
				try {
					battle.attack(tmp);
				} catch (NotEnoughKiException re) {
					k = new NotEnoughKi();
					k.message.setText(re.getMessage());
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeNotEnoughKi");
					tt.start();
				}
				sattack.dispose();
			} else if (x.getActionCommand().equals("fu")) {
				String name = x.getText();
				Attack tmp = null;
				for (int i = 0; i < game.getPlayer().getActiveFighter().getUltimateAttacks().size(); i++) {
					if (game.getPlayer().getActiveFighter().getUltimateAttacks().get(i).getName().equals(name)) {
						tmp = game.getPlayer().getActiveFighter().getUltimateAttacks().get(i);
						break;
					}

				}
				try {
					battle.attack(tmp);
				} catch (NotEnoughKiException re) {
					k = new NotEnoughKi();
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeNotEnoughKi");
					tt.start();
				}
				uattack.dispose();
			} else if (x.getActionCommand().equals("use") && battle.getAttacker() instanceof PlayableFighter) {

				try {
					battle.use(game.getPlayer(), Collectible.SENZU_BEAN);
				} catch (NotEnoughSenzuBeansException ex) {
					b = new NotEnoughSenzuBeans();
					Timer tt = new Timer(2000, this);
					tt.setActionCommand("closeNotEnoughSenzuBeans");
					tt.start();
				}
			} else if (x.getActionCommand().equals("block") && battle.getAttacker() instanceof PlayableFighter) {
				battle.block();
			}

		} else if (e.getSource() instanceof JTextField) {
			JTextField x = (JTextField) e.getSource();
			if (e.getActionCommand().equals("playerName"))
				game.getPlayer().setName(x.getText());
			else if (e.getActionCommand().equals("fighterName")) {
				fighterName = x.getText();
			} else if (e.getActionCommand().equals("saveName")) {
				savedGames.add(x.getText());
				game.save(x.getText());
				saveView.dispose();
				save();
				worldView.requestFocus();
			}
		}
	}

	public static void main(String[] args) {
		GameGUI n = new GameGUI();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		String fighterPhoto = "";
		if (game.getPlayer().getActiveFighter() instanceof Saiyan)
			fighterPhoto = "SaiyanMap";
		else if (game.getPlayer().getActiveFighter() instanceof Namekian)
			fighterPhoto = "NamekianMap";
		else if (game.getPlayer().getActiveFighter() instanceof Majin)
			fighterPhoto = "MajinMap";
		else if (game.getPlayer().getActiveFighter() instanceof Frieza)
			fighterPhoto = "FriezaMap";
		else if (game.getPlayer().getActiveFighter() instanceof Earthling)
			fighterPhoto = "EarthlingMap";

		try {
			if (worldView != null) {
				worldView.labels.get(game.getWorld().getPlayerRow())[game.getWorld().getPlayerColumn()].setIcon(null);
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					game.getWorld().moveUp();
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					game.getWorld().moveRight();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT)
					game.getWorld().moveLeft();
				else if (e.getKeyCode() == KeyEvent.VK_DOWN)
					game.getWorld().moveDown();
				worldView.labels.get(game.getWorld().getPlayerRow())[game.getWorld().getPlayerColumn()]
						.setIcon(new ImageIcon(fighterPhoto + ".gif"));
			}
		} catch (MapIndexOutOfBoundsException x) {
			worldView.labels.get(game.getWorld().getPlayerRow())[game.getWorld().getPlayerColumn()]
					.setIcon(new ImageIcon(fighterPhoto + ".gif"));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void save() {
		try {
			FileOutputStream out = new FileOutputStream("sg");
			ObjectOutputStream save = new ObjectOutputStream(out);
			save.writeObject(savedGames);
			out.close();
			save.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void load() {
		try {
			FileInputStream in = new FileInputStream("sg");
			ObjectInputStream load = new ObjectInputStream(in);
			ArrayList loaded = (ArrayList) load.readObject();
			this.savedGames = loaded;
			in.close();
			load.close();
		} catch (IOException e) {
			savedGames = new ArrayList<>();
		} catch (ClassNotFoundException e) {
			savedGames = new ArrayList<>();
		}
	}

	@Override
	public void onDragonCalled(Dragon dragon) {
		dragonveiw = new DragonVeiw();
		dragonveiw.addListener(this);
		this.dragon = dragon;
		dragonveiw.abilityNumber.setText(dragon.getAbilityPoints() + "");
		dragonveiw.senzuNumber.setText(dragon.getSenzuBeans() + "");
	}

	@Override
	public void onCollectibleFound(Collectible collectible) {
		if (collectible.equals(Collectible.SENZU_BEAN)) {
			x = new JFrame("collectible");
			x.setBounds(500, 200, 466, 365);
			x.setVisible(true);
			JLabel message = new JLabel();
			x.add(message);
			message.setIcon(new ImageIcon("senzufound.png"));
			x.revalidate();
			Timer tt = new Timer(2000, this);
			tt.setActionCommand("closeCollectible");
			tt.start();
			worldView.sezuNo.setText(game.getPlayer().getSenzuBeans() + "");

		} else if (collectible.equals(Collectible.DRAGON_BALL)) {
			x = new JFrame("collectible");
			x.setBounds(500, 200, 466, 365);
			x.setVisible(true);
			JLabel message = new JLabel();
			x.add(message);
			message.setIcon(new ImageIcon("dragonballfound.png"));
			x.revalidate();
			Timer tt = new Timer(2000, this);
			tt.setActionCommand("closeCollectible");
			tt.start();
			worldView.dballsNo.setText(game.getPlayer().getDragonBalls() + "");
		}
	}

	@Override
	public void onBattleEvent(BattleEvent e) {
		String fighterPhotoWorld = "";
		if (game.getPlayer().getActiveFighter() instanceof Saiyan)
			fighterPhotoWorld = "Saiyan";
		else if (game.getPlayer().getActiveFighter() instanceof Namekian)
			fighterPhotoWorld = "Namekian";
		else if (game.getPlayer().getActiveFighter() instanceof Majin)
			fighterPhotoWorld = "Majin";
		else if (game.getPlayer().getActiveFighter() instanceof Frieza)
			fighterPhotoWorld = "Frieza";
		else if (game.getPlayer().getActiveFighter() instanceof Earthling)
			fighterPhotoWorld = "Earthling";

		if (e.getType().equals(BattleEventType.STARTED)) {
			int foeidx = new Random().nextInt(9);
			foeBattle = foes[foeidx];
			xp = game.getWorld().getPlayerRow();
			yp = game.getWorld().getPlayerColumn();
			level = game.getPlayer().getActiveFighter().getLevel();
			battleView = new BattleView();
			battleView.addListener(this);
			battle = (Battle) e.getSource();
			NonPlayableFighter f = (NonPlayableFighter) battle.getFoe();
			PlayableFighter p = (PlayableFighter) battle.getMe();

			battleView.pname.setText("Name: " + p.getName());
			battleView.plevel.setText("Level: " + p.getLevel());
			battleView.PmaxHp.setText(p.getMaxHealthPoints() + ":max health");
			battleView.Pmaxstamina.setText(p.getMaxStamina() + "");
			battleView.PmaxKi.setText(p.getMaxKi() + "");

			battleView.fname.setText("Name: " + f.getName());
			battleView.flevel.setText("Level: " + f.getLevel());
			battleView.fmaxHp.setText("max health:" + f.getMaxHealthPoints());
			battleView.fmaxstamina.setText(f.getMaxStamina() + "");
			battleView.fmaxKi.setText(f.getMaxKi() + "");

			Icon j1 = new ImageIcon(((new ImageIcon(fighterPhotoWorld + "Battle.png").getImage().getScaledInstance(
					battleView.fighterphoto.getWidth(), battleView.fighterphoto.getHeight(),
					java.awt.Image.SCALE_SMOOTH))));
			battleView.fighterphoto.setIcon(j1);
			Icon j2 = new ImageIcon(((new ImageIcon(foeBattle + "Battle.png").getImage().getScaledInstance(
					battleView.foephoto.getWidth(), battleView.foephoto.getHeight(), java.awt.Image.SCALE_SMOOTH))));
			battleView.foephoto.setIcon(j2);
			battleView.foe.setIcon(new ImageIcon(foeBattle + ".gif"));
			battleView.fighter.setIcon(new ImageIcon(fighterPhotoWorld + "Battle.gif"));
			this.updatebattleinfo();
			battleView.revalidate();
			Timer tt = new Timer(2000, this);
			tt.setActionCommand("turn");
			tt.start();

		}
		if (e.getType().equals(BattleEventType.NEW_TURN)) {
			this.updatebattleinfo();
			if (battle.getAttacker() instanceof NonPlayableFighter) {
				battleView.turn.setText("foe turn");
				Timer tt = new Timer(4000, this);
				tt.setActionCommand("turn");
				Timer fplay = new Timer(2000, this);
				fplay.setActionCommand("fturn");
				Timer show = new Timer(2000, this);
				show.setActionCommand("show");

				fplay.start();
				show.start();
				tt.start();
			} else {
				battleView.turn.setText("your turn");
				Timer tt = new Timer(4000, this);
				Timer show = new Timer(2000, this);
				show.setActionCommand("show");
				tt.setActionCommand("turn");
				Timer pplay = new Timer(2000, this);
				pplay.setActionCommand("pturn");
				pplay.start();
				show.start();
				tt.start();
			}

			if (battle.getAttacker() instanceof NonPlayableFighter) {
				Timer t = new Timer(4000, this);
				t.setActionCommand("fplay");
				t.start();
			}
		} else if (e.getType().equals(BattleEventType.ATTACK)) {
			this.updatebattleinfo();
			if (e.getCurrentOpponent() instanceof NonPlayableFighter) {
				// battleView.fattacking.setVisible(true);

				if (e.getAttack() instanceof PhysicalAttack) {
					battleView.foe.setIcon(new ImageIcon(foeBattle + "Attack.gif"));
				} else if (e.getAttack() instanceof SuperAttack) {
					battleView.foe.setIcon(new ImageIcon(foeBattle + "Attack.gif"));
				} else if (e.getAttack() instanceof UltimateAttack) {
					battleView.foe.setIcon(new ImageIcon(foeBattle + "Attack.gif"));
				}

			} else {
				// battleView.pattacking.setVisible(true);
				if (e.getAttack() instanceof PhysicalAttack) {
					battleView.fighter.setIcon(new ImageIcon(fighterPhotoWorld + "Physical.gif"));
				} else if (e.getAttack() instanceof SuperAttack) {
					battleView.fighter.setIcon(new ImageIcon(fighterPhotoWorld + "Super.gif"));
				} else if (e.getAttack() instanceof UltimateAttack) {
					battleView.fighter.setIcon(new ImageIcon(fighterPhotoWorld + "Ultimate.gif"));
				} else if (e.getAttack() instanceof SuperSaiyan) {
					battleView.fighter.setIcon(new ImageIcon(fighterPhotoWorld + "Transform.gif"));
				}
			}
		} else if (e.getType().equals(BattleEventType.BLOCK)) {
			this.updatebattleinfo();
			if (e.getCurrentOpponent() instanceof PlayableFighter){
				battleView.meblock.setIcon(null);
				battleView.meblock.setIcon(new ImageIcon("shield.png"));
				battleView.meblock.setVisible(true);
			}
			else
				battleView.foblock.setVisible(true);
			battleView.revalidate();

		} else if (e.getType().equals(BattleEventType.USE)) {
			this.updatebattleinfo();
			battleView.meblock.setIcon(null);
			battleView.meblock.setIcon(new ImageIcon("senzu.png"));
			battleView.meblock.setVisible(true);

		} else if (e.getType().equals(BattleEventType.ENDED)) {
			this.updatebattleinfo();

			if (e.getWinner() instanceof PlayableFighter) {
				ArrayList<Attack> attacks = new ArrayList<Attack>();
				attacks.addAll(((Fighter) battle.getFoe()).getSuperAttacks());
				attacks.addAll(((Fighter) battle.getFoe()).getUltimateAttacks());
				if (game.getPlayer().getActiveFighter().getLevel() > level) {
					game.getPlayer().getActiveFighter().getXp();
					bu = new Battle_ended_levelup(game.getPlayer().getActiveFighter().getXp(),
							game.getPlayer().getActiveFighter().getTargetXp(),
							game.getPlayer().getActiveFighter().getAbilityPoints(),
							game.getPlayer().getActiveFighter().getLevel(), attacks);
					Timer tt = new Timer(7000, this);
					tt.setActionCommand("closeskillsup");
					tt.start();
				} else {
					be = new Battle_ended(game.getPlayer().getActiveFighter().getXp(), attacks);
					Timer tt1 = new Timer(7000, this);
					tt1.setActionCommand("closeskills");
					tt1.start();
				}
				if (((NonPlayableFighter) battle.getFoe()).isStrong()) {
					if (bu != null) {
						bu.info.setText(
								"                                    you won,leveled up,defeated boss and unlocked new map");
					} else if (be != null) {
						be.info.setText("you won and unlocked new map");
					}
					int foeidx = new Random().nextInt(9);
					PlayableFighter tmp = game.getPlayer().getActiveFighter();
					worldView.addListener(this);
					worldView.fighterLevel.setText("" + tmp.getLevel()); /// change
					worldView.l.setIcon(new ImageIcon("worldNewMap.png")); /// photo
					/// new
					/// map/////////
					worldView.fighterName.setText(tmp.getName());
					worldView.playername.setText(game.getPlayer().getName());
					worldView.fighterPhoto.setIcon(new ImageIcon(fighterPhotoWorld + "1.png"));
					worldView.labels.get(9)[9].setIcon(new ImageIcon(fighterPhotoWorld + "Map.gif"));
					worldView.labels.get(0)[0].setIcon(new ImageIcon(foes[foeidx] + ".gif"));
					worldView.sezuNo.setText("" + game.getPlayer().getSenzuBeans());
					worldView.dballsNo.setText("" + game.getPlayer().getDragonBalls());
					// worldView.revalidate();
					// worldView.setFocusable(true);

				} else {
					worldView.sezuNo.setText("" + game.getPlayer().getSenzuBeans());
					worldView.dballsNo.setText("" + game.getPlayer().getDragonBalls());
					worldView.fighterLevel.setText(game.getPlayer().getActiveFighter().getLevel() + "");
					worldView.fighterName.setText(game.getPlayer().getActiveFighter().getName());
					worldView.playername.setText(game.getPlayer().getName());
					worldView.labels.get(game.getWorld().getPlayerRow())[game.getWorld().getPlayerColumn()]
							.setIcon(new ImageIcon(fighterPhotoWorld + "Map.gif"));

				}
			} else {
				if (xp == 0 && yp == 0) {
					FoeCell f = (FoeCell) game.getWorld().getMap()[0][0];
					worldView.labels.get(xp)[yp].setIcon(new ImageIcon(foeBattle + ".gif"));
				} else
					worldView.labels.get(xp)[yp].setIcon(null);
				worldView.labels.get(game.getWorld().getPlayerRow())[game.getWorld().getPlayerColumn()]
						.setIcon(new ImageIcon(fighterPhotoWorld + "Map.gif"));
				Lost lx = new Lost();

			}
			battleView.dispose();
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
		if (e.getSource() instanceof JButton) {
			JButton x = (JButton) e.getSource();
			if (x.getActionCommand().equals("switchF")) {
				String name = x.getText();
				PlayableFighter tmp = null;
				for (int i = 0; i < game.getPlayer().getFighters().size(); i++) {
					if (game.getPlayer().getFighters().get(i).getName().equals(name)) {
						tmp = game.getPlayer().getFighters().get(i);
						break;
					}
				}
				String fighterPhoto = "";
				if (tmp instanceof Saiyan)
					fighterPhoto = "SaiyanS.png";
				else if (tmp instanceof Namekian)
					fighterPhoto = "NamekianS.png";
				else if (tmp instanceof Majin)
					fighterPhoto = "MajinS.png";
				else if (tmp instanceof Frieza)
					fighterPhoto = "FriezaS.png";
				else if (tmp instanceof Earthling)
					fighterPhoto = "EarthlingS.png";
				switchView.photo.setIcon(new ImageIcon(fighterPhoto));
				JButton tmp1 = (JButton) e.getSource();
				Icon i = new ImageIcon(((new ImageIcon("bselect.jpg").getImage().getScaledInstance(tmp1.getWidth(),
						tmp1.getHeight(), java.awt.Image.SCALE_SMOOTH))));
				tmp1.setIcon(i);
				tmp1.setForeground(Color.black);
				for (int j = 0; j < switchView.buttons.size(); j++) {
					if (switchView.buttons.get(j) != x) {
						JButton tmp2 = switchView.buttons.get(j);
						Icon il = new ImageIcon(((new ImageIcon("upgrade.jpg").getImage()
								.getScaledInstance(tmp2.getWidth(), tmp2.getHeight(), java.awt.Image.SCALE_SMOOTH))));
						tmp2.setIcon(il);
						tmp2.setForeground(Color.white);
					}
				}

			}
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public void updatebattleinfo() {
		battleView.fighterKi.removeAll();
		battleView.foeKi.removeAll();
		battleView.fighterStamina.removeAll();
		battleView.foeStamina.removeAll();
		battleView.fighterKi.setLayout(new GridLayout(1, ((Fighter) battle.getMe()).getMaxKi()));
		battleView.foeKi.setLayout(new GridLayout(1, ((Fighter) battle.getFoe()).getMaxKi()));
		battleView.fighterStamina.setLayout(new GridLayout(1, ((Fighter) battle.getMe()).getMaxStamina()));
		battleView.foeStamina.setLayout(new GridLayout(1, ((Fighter) battle.getFoe()).getMaxStamina()));
		battleView.fcurrenthealth.setText(((Fighter) battle.getFoe()).getHealthPoints() + "");
		battleView.pcurrenthealth.setText(((Fighter) battle.getMe()).getHealthPoints() + "");

		int foemax = ((Fighter) battle.getFoe()).getMaxHealthPoints();
		int foecurrent = ((Fighter) battle.getFoe()).getHealthPoints();

		int playermax = game.getPlayer().getActiveFighter().getMaxHealthPoints();
		int playercurrent = game.getPlayer().getActiveFighter().getHealthPoints();

		double foeratio = ((double) foecurrent) / foemax;
		double playerratio = ((double) playercurrent) / playermax;

		// changing fighter health
		battleView.playerhealthbar.setBounds(784 - (int) (200 * playerratio), battleView.playerhealthbar.getBounds().y,
				(int) (200 * playerratio), battleView.playerhealthbar.getHeight());
		if (playerratio * 100 < 50 && playerratio * 100 > 20)
			battleView.playerhealthbar.setBackground(Color.yellow);
		else if (playerratio * 100 <= 20)
			battleView.playerhealthbar.setBackground(Color.red);
		// changing foe health
		battleView.foehealthbar.setBounds(battleView.foehealthbar.getBounds().x, battleView.foehealthbar.getBounds().y,
				(int) (200 * foeratio), battleView.foehealthbar.getHeight());
		if (foeratio * 100 < 50 && foeratio * 100 > 20)
			battleView.foehealthbar.setBackground(Color.yellow);
		else if (foeratio * 100 <= 20)
			battleView.foehealthbar.setBackground(Color.red);

		for (int i = 0; i < ((Fighter) battle.getMe()).getKi(); i++) {
			JButton tmp = new JButton();
			tmp.setBorderPainted(true);
			tmp.setBackground(Color.yellow);
			battleView.fighterKi.add(tmp);
		}
		for (int i = 0; i < ((Fighter) battle.getFoe()).getKi(); i++) {
			JButton tmp = new JButton();
			// tmp.setBorderPainted(tr);
			tmp.setBackground(Color.yellow);
			battleView.foeKi.add(tmp);
		}
		battleView.revalidate();
		for (int i = 0; i < ((Fighter) battle.getFoe()).getStamina(); i++) {
			JButton tmp = new JButton();
			// tmp.setBorderPainted(false);
			tmp.setBackground(Color.blue);
			battleView.foeStamina.add(tmp);
		}
		battleView.revalidate();
		for (int i = 0; i < ((Fighter) battle.getMe()).getStamina(); i++) {
			JButton tmp = new JButton();
			// tmp.setBorderPainted(false);
			tmp.setBackground(Color.blue);
			battleView.fighterStamina.add(tmp);
		}
		battleView.revalidate();
	}

	public static void music() {
		AudioPlayer MGP = AudioPlayer.player;
		AudioStream BGM;
		AudioData MD;
		ContinuousAudioDataStream loop = null;
		try {
			InputStream test = new FileInputStream("pepa1.wav");
			BGM = new AudioStream(test);
			AudioPlayer.player.start(BGM);
		} catch (FileNotFoundException e) {
			System.out.print(e.toString());
		} catch (IOException error) {
			System.out.print(error.toString());
		}
		MGP.start(loop);
	}

}
