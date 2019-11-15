
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Creates a timer for the game which allows the component to update
 * as time goes on.
 * 
 *
 */
public class TimerListner implements ActionListener {

	private GuiComponenet component;
	
	public TimerListner(GuiComponenet component) {
		this.component = component;
	}
	
	/**
	 * While the game is not paused, updates the screen once every delay period
	 * 
	 * @param 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
//		if (!this.component.pauseGame()) this.component.updateScreen();
	}

}
