

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * Where the magic GUI stuff happens
 * 
 * @author Lucus Bendzsa.
 */
public class GuiComponenet extends JComponent {
	private boolean ran;
	private NodeImporter importer;
	private ArrayList<BattleNode> battles;
	private BattleNode initalNode;
	private BattleNode targetNode;


	public GuiComponenet(MyBoolean booly) {
		handleimport(booly);
	}
	
	
	@Override
	/*
	 * No Javadoc is needed, because we inherit one. (Hover on paintComponent.)
	*/
	protected void paintComponent(Graphics g) {
		// Asks the superclass to do its work
		super.paintComponent(g);
		// DO NOT TOUCH ABOVE
		this.ran = false;
		handleBackground(g);
		handleBattles(g);
	}
	
	public void updateScreen() {
		importer = new NodeImporter(6);  //currently only works on a line...
		battles = importer.getBattles();
		//we need to get rid of this and just update the lines, not nodes...
		
		
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
			g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			if (e.battleName != null) {
				g.setColor(Color.BLUE);
				g.drawString(e.battleName, (int) e.getX() - 100, (int) e.getY() + 30);
			}
		});
		g.drawString("    Battle -----to---- Battle", 750, 835);
		if(initalNode!=null&&targetNode!=null) {
			System.out.println(initalNode.battleName+" to "+targetNode.battleName);
			ArrayList<BattleNode> shortestPath=initalNode.shortestPath(targetNode.battleName, new ArrayList<BattleNode>());
			if(shortestPath==null) {
				System.out.println("There is no path to the specified location. Try a different route.");
			}
			if(shortestPath!=null) {
				String list="";
for (int i=shortestPath.size()-1;i>=0;i--) {
	list +=shortestPath.get(i).battleName+ ", ";
}
				g.drawString(list, 10,900);
//				shortestPath.stream().forEach(e -> System.out.println(e.battleName));
				System.out.println(initalNode.getCostOfPath(shortestPath));
			}
//			System.out.println(initalNode.shortestPath(targetNode.battleName).toString());
//			g.drawString(initalNode.shortestPath(targetNode.battleName).toString(), 10,900);
		}
		
//		g.drawString("Battles traveled through: "+battles.get(0).shortestPath("BattleofGettysburg"), 10, 850);
		//this will be in a new method called by the fact the 2 text boxes are not null
		//otherwise, we get that clicker method going...
	}
	
	public void reset() {
		initalNode=null;
		return;
	}
	
	public void insertInput(String input, int textBox) {
		if(textBox==1) {
			initalNode=searchFor(input);
		}
		if(textBox==2) {
			targetNode=searchFor(input);
		}
		if((targetNode==null&&textBox==2)||(initalNode==null&&textBox==1)){
			System.out.println("Battle(s) not found in system.");
		}
	}
	
	public BattleNode searchFor(String nodeName) {
		for(int i=0; i<battles.size(); i++) {
			if(battles.get(i).battleName.equals(nodeName)) {
				return battles.get(i);
			}
		}
		return null;
	}

	private ArrayList<BattleNode> handleimport(MyBoolean booly) {
		if (booly.getValue() == true) {
			booly.setFalse();
			importer = new NodeImporter(6);
			battles = importer.getBattles();
			return importer.getBattles();
		}
		return battles;
	}

	private void handleBackground(Graphics g) {
		String imgString = ("Resoruces//map1.jpg");

		try {
			Image background = ImageIO.read(new File(imgString));
			g.drawImage(background, 0, 0, null);

		} catch (Exception e) {
			System.out.println("MapFile not found");
			return;
		}
	}

}
