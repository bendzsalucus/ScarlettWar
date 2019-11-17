
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Line2D;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * Where the magic GUI stuff happens
 * 
 * @author Lucus Bendzsa.
 */
public class GuiComponenet extends JComponent {
	private NodeImporter importer;
	public ArrayList<BattleNode> battles;
	private BattleNode initalNode;
	private BattleNode targetNode;
	private BattleNode bat1;
	private BattleNode bat2;
	private int i;
	
	
	private ArrayList<Line2D.Double> lineToDraw=new ArrayList<Line2D.Double>();

	public GuiComponenet(int i) {
		this.i = i;
		handleimport(i);
	}

	@Override
	/*
	 * No Javadoc is needed, because we inherit one. (Hover on paintComponent.)
	 */
	protected void paintComponent(Graphics g) {
		// Asks the superclass to do its work
		super.paintComponent(g);
		// DO NOT TOUCH ABOVE
		handleBackground(g);
		handleBattles(g);
		
		if (bat1 != null) {
			printinfo(g);
		}
	}

	public void updateScreen() {
		importer = new NodeImporter(i); // currently only works on a line...
		battles = importer.getBattles();
		// we need to get rid of this and just update the lines, not nodes...
		lineToDraw.removeAll(lineToDraw);
//		if(initalNode!=null&&targetNode!=null) {
//			System.out.println(initalNode.battleName+" "+targetNode.battleName);
//			System.out.println(initalNode.shortestPath(targetNode.battleName));
//			g.drawString(initalNode.shortestPath(targetNode.battleName).toString(), 10,900);
//		}
		this.repaint();

	}

	private void handleBattles(Graphics g) {

		ArrayList<BattleNode> battles = importer.getBattles();

		battles.stream().forEach(e -> {
			g.setColor(e.color);
			e.drawOn(g);
			((Graphics2D) g).fill(e.getBoundingBox());
			g.setFont(new Font("Arial", Font.BOLD, 20));
			if (e.battleName != null) {
				if (this.i == 6) {
					g.setColor(Color.BLACK);
				} else {
					g.setColor(Color.WHITE);
				}
				if (i == 6) {
					String temp = e.battleName.substring(9);
					g.drawString(temp, (int) e.getX() - 20 + e.xOff, (int) e.getY() + 30 + e.yOff);
					g.setColor(e.color);
				} else {
					String temp = e.battleName.substring(4);
					g.drawString(temp, (int) e.getX() - 20 + e.xOff, (int) e.getY() + 30 + e.yOff);
					g.setColor(e.color);
				}
			}
		});
		if (i == 6) {
			g.drawString("Select Two Battles! ", 770, 780);
		}
		if (i == 7) {
			g.drawString("Select Two Avengers! ", 755, 780);
		}

        if (initalNode != null && targetNode != null) {
            ArrayList<BattleNode> shortestPath = initalNode.shortestPath(targetNode.battleName,
                                          new ArrayList<BattleNode>());
            if (shortestPath == null) {
                           System.out.println("There is no path to the specified location. Try a different route.");
            }
            if (shortestPath != null) {
                           String list = "";
                           for (int r = shortestPath.size() - 1; r >= 0; r--) {
                                          int concatNum = 0;
                                          if (this.i == 7) {
                                                         concatNum = 4;
                                          } else {
                                                         concatNum = 10;

                                          }
                                          String tempCat = shortestPath.get(r).battleName;
                                          tempCat = tempCat.substring(concatNum);
                                          list += tempCat + ", ";
                           }
                           g.setFont(new Font("Arial", Font.BOLD, 17));
                           g.drawString(list, 10, 900);
                           shortestPath.stream().forEach(e -> System.out.println(e.battleName));
                           System.out.println(initalNode.getCostOfPath(shortestPath));
            }
		return;


//		g.drawString("Battles traveled through: "+battles.get(0).shortestPath("BattleofGettysburg"), 10, 850);
		// this will be in a new method called by the fact the 2 text boxes are not null
		// otherwise, we get that clicker method going...
	}

	public void drawLines(ArrayList<BattleNode> path, Graphics g) {
		Graphics2D g2=(Graphics2D) g;
		for(int i=0;i<path.size()-1;i++) {
			BattleNode currentNode=path.get(i);
			BattleNode nextNode=path.get(i+1);
			lineToDraw.add(new Line2D.Double(currentNode.getX(), currentNode.getY(), nextNode.getX(), nextNode.getY()));
		//add thickness factor.
		}
		g2.setColor(Color.BLACK);
		lineToDraw.stream().forEach(e -> g2.draw(e));
	}
	

	public void insertInput(String input, int textBox) {
		if (textBox == 1) {
			initalNode = searchFor(input);
		}
		if (textBox == 2) {
			targetNode = searchFor(input);
		}
		if ((targetNode == null && textBox == 2) || (initalNode == null && textBox == 1)) {
			System.out.println("Battle(s) not found in system.");
		}
	}

	public BattleNode searchFor(String nodeName) {
		for (int i = 0; i < battles.size(); i++) {
			if (battles.get(i).battleName.equals(nodeName)) {
				return battles.get(i);
			}
		}
		return null;
	}

	private ArrayList handleimport(int i) {

		importer = new NodeImporter(i);
		battles = importer.getBattles();
		return importer.getBattles();
	}

	private void handleBackground(Graphics g) {
		String imgString = ("Resoruces//map" + i + ".jpg");

		try {
			Image background = ImageIO.read(new File(imgString));
			g.drawImage(background, 0, 0, null);

		} catch (Exception e) {
			System.out.println("MapFile not found");
			return;
		}
	}

	public void addInfo(String battle1, String battle2) {
		bat1 = searchFor(battle1);
		bat2 = searchFor(battle2);

	}

	private void printinfo(Graphics g) {
		g.drawString(bat1.battleName, 4, 744);
		g.drawString(bat2.battleName, 4, 830);

	}

}
