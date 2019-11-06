package blah;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
/**
 * This made programming everything else so easy. 
 * This class contains some awesome methods dealing with
 * basic movement and positioning. This also handles dead. 
 * 
 * @author Lucus Bendzsa
 */
public abstract class GameObject {

	protected double x;
	protected double y;
	protected double width;
	protected double height;

	public GameObject(double x, double y, double width,
			double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;


	}

	public double getX() {
		// Returns Top left X Value
		return this.x;
	}

	public double getY() {
		// Returns Top left Y Value
		return this.y;
	}
	
	public double getXCenter() {
		return this.x + (this.width/2);
	}
	
	public double getYCenter() {
		return this.y + (this.height/2);
	}

	public double getWidth() {
		// Returns Width
		return this.width;
	}

	public double getHeight() {
		// Returns Height
		return this.height;
	}


	public Rectangle2D.Double getBoundingBox() {
		// Gets Bounding Box of Character
		return new Rectangle2D.Double(this.x, this.y, getWidth(), getHeight());
	}

	public boolean intersects(Rectangle2D rectangle2d) {
		// Returns if Character is intersecting another object
		return getBoundingBox().intersects(rectangle2d);
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

}
