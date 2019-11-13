package blah;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Game;

public class ClickingListener implements MouseListener {

	private Graph m;
	
	public ClickingListener(Graph mainMenu) {
		this.m=mainMenu;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount()==2) {
			
		}
		
		Point click=e.getPoint();
		switch(this.m.clickLock(click)) {
		case 1:
			System.out.println("Case 1 (Probably hit a node)");
		default:
			System.out.println("System broke");
			break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
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

}
