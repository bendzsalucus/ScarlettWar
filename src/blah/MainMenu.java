package blah;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * This is what handles the Main Menu Frame. 
 * This handles the buttons for the frame at the bottom. 
 * 
 * @author Lucus Bendzsa. Created May 2019.
 */
public class MainMenu {

	int strike;
	int ball;

	private JLabel label = new JLabel();
	private JFrame frame;

	public static void main(String[] args) {
		new MainMenu();
	}

	public MainMenu() {
		frame = new JFrame();
//		Sound.playMusic("Sounds\\Wii.wav");
		JPanel panel = new JPanel();
		MenuComponent menuComp = new MenuComponent();
	    frame.getContentPane().setBackground(Color.BLACK);
		frame.add(menuComp, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.SOUTH);
		frame.setFocusable(true);
		frame.setBackground(Color.PINK);
		JButton newGame = new JButton("NewGame");
		JButton quit = new JButton("Quit");
		JButton blah = new JButton("Blah");
		panel.setBackground(Color.BLACK);
		panel.add(newGame);
		panel.add(quit);
		panel.add(blah);
		class NewGameListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				new Gui();
				frame.dispose();
//				Sound.stop();
			}
		}


		class QuitListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		}

		quit.addActionListener(new QuitListener());
		newGame.addActionListener(new NewGameListener());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setVisible(true);



	}
	
	
}
