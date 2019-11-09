package blah;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * This class draws simple objects using Graphics2D.
 * 
 * @author Curt Clifton. Created Sep 10, 2008.
 */
public class GuiComponenet extends JComponent {
	/*
	 * No Javadoc is needed, because we inherit one. (Hover on paintComponent.)
	 */
	@Override
	protected void paintComponent(Graphics g) {
		// Asks the superclass to do its work
		super.paintComponent(g);
		
		Graphics2D graphics2 = (Graphics2D) g;
		
		NodeImporter importer = new NodeImporter(1);
		ArrayList<BattleNode> battles = importer.getBattles();
		
		battles.stream().forEach(e -> {
			graphics2.setColor(e.color);
			graphics2.fill(e.getBoundingBox());
		});
		
		
	}

}
