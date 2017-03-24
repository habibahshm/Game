package dragonball.model.world;

import dragonball.model.character.fighter.*;

import java.io.Serializable;
import java.util.*;

import dragonball.model.cell.*;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;

public class World implements CellListener,Serializable {
	private Cell[][] map;
	private int playerColumn;
	private int playerRow;
	private WorldListener worldListener;

	public WorldListener getListener() {
		return worldListener;
	}

	public void setListener(WorldListener worldListener) {
		this.worldListener = worldListener;
	}

	public World() {
		map = new Cell[10][10];
		playerColumn = 9;
		playerRow = 9;
	}

	private static int randomize(int size) {
		return (int) (Math.random() * size);
	}

	private boolean isValid(int x, int y) {
		return (x + y != 18) && (x + y != 0) && x >= 0 && x < 10 && y >= 0
				&& y < 10 && (map[x][y] == null);
	}

	private void spreadWeakFoes(ArrayList<NonPlayableFighter> weakFoes) {
		for (int i = 0; i < 15; i++) {
			int w = randomize(weakFoes.size());
			int wx, wy;
			do {
				wx = randomize(10);
				wy = randomize(10);
			} while (!isValid(wx, wy));
			map[wx][wy] = new FoeCell(weakFoes.get(w));
		}
	}

	private void spreadSenzubeans() {
		int num = randomize(3) + 3;
		for (int i = 0; i < num; i++) {
			int sx, sy;
			do {
				sx = randomize(10);
				sy = randomize(10);
			} while (!isValid(sx, sy));
			map[sx][sy] = new CollectibleCell(Collectible.SENZU_BEAN);
		}
	}

	private void layDragonball() {
		int dx, dy;
		do {
			dx = randomize(10);
			dy = randomize(10);
		} while (!isValid(dx, dy));
		map[dx][dy] = new CollectibleCell(Collectible.DRAGON_BALL);
	}

	private void fill() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (isValid(i, j))
					map[i][j] = new EmptyCell();
			}
		}
	}

	public void generateMap(ArrayList<NonPlayableFighter> weakFoes,
			ArrayList<NonPlayableFighter> strongFoes) {
		int s = randomize(strongFoes.size());
		map[0][0] = new FoeCell(strongFoes.get(s));
		map[9][9] = new EmptyCell();
		spreadWeakFoes(weakFoes);
		spreadSenzubeans();
		layDragonball();
		fill();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				(map[i][j]).setListener(this);
			}

		}
	}

	public String toString() {
		String output = "";
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == playerRow && j == playerColumn)
					output += "[x]";
				else
					output += map[i][j].toString();
				if (j != 10)
					output += " ";
			}
			output += "\n";
		}
		return output;
	}

	public void onFoeEncountered(NonPlayableFighter foe) {
		if (this.worldListener != null)
			(this.worldListener).onFoeEncountered(foe);

	}

	public void onCollectibleFound(Collectible collectible) {
		if (this.worldListener != null)
			(this.worldListener).onCollectibleFound(collectible);
		map[playerRow][playerColumn] = new EmptyCell();
	}


	public void resetPlayerPosition() {
		playerColumn = 9;
		playerRow = 9;
	}

	public void moveUp() throws MapIndexOutOfBoundsException {
		if (playerRow != 0) {
			playerRow--;
			map[playerRow][playerColumn].onStep();
		} else
			throw new MapIndexOutOfBoundsException(playerRow, playerColumn);
	}

	public void moveDown() throws MapIndexOutOfBoundsException {
		if (playerRow != 9) {
			playerRow++;
			map[playerRow][playerColumn].onStep();
		} else
			throw new MapIndexOutOfBoundsException(playerRow, playerColumn);
	}

	public void moveRight() throws MapIndexOutOfBoundsException {
		if (playerColumn != 9) {
			playerColumn++;
			map[playerRow][playerColumn].onStep();
		} else
			throw new MapIndexOutOfBoundsException(playerRow, playerColumn);

	}

	public void moveLeft() throws MapIndexOutOfBoundsException {
		if (playerColumn != 0) {
			playerColumn--;
			map[playerRow][playerColumn].onStep();
		}else
			throw new MapIndexOutOfBoundsException(playerRow, playerColumn);
	}

	public Cell[][] getMap() {
		return map;
	}

	public int getPlayerColumn() {
		return playerColumn;
	}

	public int getPlayerRow() {
		return playerRow;
	}

}
