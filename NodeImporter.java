

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Interprets level based on text file. Areguements for each item can be seen
 * below.
 * 
 * @author Lucus Bendzsa. Created May 2019.
 */
public class NodeImporter {

	ArrayList BattleNodes;
	private String nameofBattle;
	private String battleDescription;
	private int soldierInTown;
	private String imgPath;
	private int mapNum;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean isUnion;
	private Color color;
	private ArrayList<BattleNode> battles;
	private ArrayList<Edge> edges;
	private int xOff;
	private int yOff;

	public NodeImporter(int mapNummy) {
//		System.out.println(mapNummy);
		battles = new ArrayList<BattleNode>();
		mapNum = mapNummy;
		readBattle();
		readEdge();

	}

	/**
	 * Checks for each areguement and if the arguement matches the keyword the
	 * arguement is made.
	 */

	private void readBattle() {
		Scanner scanner;
		String scanString = ("Resoruces//Map" + this.mapNum + ".txt");
		try {
			scanner = new Scanner(new File(scanString));
		} catch (FileNotFoundException e) {
			System.out.println("Level not found");
			return;
		}
		while (scanner.hasNextLine()) {
			String currentLine = scanner.next();
			if (currentLine.equals("stop")) {
				makeNewBattle();
			}
			if (currentLine.equals("nameofBattle")) {
				nameofBattle = scanner.next()+" "+scanner.next()+" "+scanner.next();
			}
			if (currentLine.equals("battleDescription")) {
				battleDescription = scanner.nextLine();
			}
			if (currentLine.equals("soldierCount")) {
				soldierInTown = scanner.nextInt();
			}
			if (currentLine.equals("position")) {
				x = scanner.nextInt();
				y = scanner.nextInt();
				width = scanner.nextInt();
				height = scanner.nextInt();
			}
			if (currentLine.equals("textOffset")) {
				xOff = scanner.nextInt();
				yOff = scanner.nextInt();
			}
			if (currentLine.equals("side")) {
				int temp = scanner.nextInt();
				if (temp == 1) {
					isUnion = false;
					color = Color.RED;
				} else {
					isUnion = true;
					color = Color.BLUE;
				}
			}
			if (currentLine.equals("imgpath")) {
				imgPath = scanner.next();
			}

		}
		scanner.close();

	}

	private void readEdge() {
		Scanner scanner;
		String scanString = ("Resoruces//Edge" + this.mapNum + ".txt");
		try {
			scanner = new Scanner(new File(scanString));
		} catch (FileNotFoundException e) {
			System.out.println("EdgeFile not found");
			return;
		}
		while (scanner.hasNextLine()) {
			BattleNode tempBattle = battles.get(scanner.nextInt());
			BattleNode tempBattleTwo = battles.get(scanner.nextInt());
			int cost = scanner.nextInt();
			Edge tempEdge = new Edge(tempBattle, tempBattleTwo, cost);
			if (tempBattle.edges == null) {
				tempBattle.edges = new ArrayList<Edge>();
			}
			tempBattle.edges.add(tempEdge);

		}
		scanner.close();

	}

	private void makeNewBattle() {
		BattleNode temp = new BattleNode(x, y, width, height, nameofBattle, battleDescription, soldierInTown, imgPath,
				isUnion, color, edges, xOff, yOff);
		battles.add(temp);

	}

	public int getSoldierCount() {
		return this.soldierInTown;
	}

	public String getBattleName() {
		return this.nameofBattle;
	}

	public String getBattleDescription() {
		return this.battleDescription;
	}

	public String getBackGroundPath() {
		return this.imgPath;
	}

	public int getLevel() {
		return this.mapNum;
	}

	public ArrayList<BattleNode> getBattles() {
		return battles;
	}
}
