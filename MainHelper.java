
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 * This is what makes the awesome image on the 
 * start screen. 
 * 
 * @author Lucus Bendzsa. Created May 2019.
 */

@SuppressWarnings("serial")
public class MainHelper extends JComponent {

	private Graphics2D g2;
	private ImageIcon titleImage;
	private String string;

	public MainHelper(String path) {
	    this.string = path;
		
	}

	/**
	 * Paint component handles repainting all of the objects on the scene..
	 * 
	 * @param Graphics g
	 */
	protected void paintComponent(Graphics g) {
		// DO NOT MOVE ME
		super.paintComponent(g);
		this.g2 = (Graphics2D) g;
		// DO NOT MOVE ME
		String imgString = (string);

		this.titleImage = new ImageIcon(imgString);
		this.titleImage.paintIcon(this, this.g2, 0, 0);

		// Draws Three of the Bounds, Bottom bound best as a platform.

	}

	public void drawScreen() {
		// DO NOT TOUCH ME
		this.revalidate();
		this.repaint();
		// DO NOT TOUCH ME
	}

}
