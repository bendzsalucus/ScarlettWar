package blah;

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
	private NodeImporter importer;

	/*
	 * No Javadoc is needed, because we inherit one. (Hover on paintComponent.)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		// Asks the superclass to do its work
		super.paintComponent(g);
		Graphics2D graphics2 = (Graphics2D) g;
		this.importer = new NodeImporter(1);

		// DO NOT TOUCH ABOVE
		handleBackground(g);
		handleBattles(g);

	}

	private void handleBattles(Graphics g) {

		ArrayList<BattleNode> battles = importer.getBattles();
//		System.out.println("hello");
		battles.stream().forEach(e -> {
			System.out.println(e.battleName);
			g.setColor(e.color);
			e.drawOn(g);
//			((Graphics2D) g).fill(e.getBoundingBox());
			g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			if (e.battleName != null) {
				g.setColor(Color.BLUE);
				g.drawString(e.battleName,(int)e.getX()-100,(int) e.getY() + 30);
			}
		});

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
