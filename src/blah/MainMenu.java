package blah;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * This is what handles the Main Menu Frame. This handles the buttons for the
 * frame at the bottom.
 * 
 * @author Lucus Bendzsa. Created May 2019.
 */
public class MainMenu {

	private JLabel label = new JLabel();
	private JFrame frame;

	public static void main(String[] args) {
		new MainMenu();
	}

	public MainMenu() {
		ArrayList b = new ArrayList();
		ArrayList a = new ArrayList();
		a.add(1);
		b.add(a);
		
		frame = new JFrame();
		JPanel panel = new JPanel();
		MenuComponent menuComp = new MenuComponent();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.add(menuComp, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.SOUTH);
		frame.setFocusable(true);
		frame.setBackground(Color.PINK);
		JButton newGame = new JButton("New Game");
		JButton quit = new JButton("Quit");
		JButton enableMusic = new JButton("Enable Music");
		panel.setBackground(Color.BLACK);
		panel.add(newGame);
		panel.add(quit);
		panel.add(enableMusic);

		class NewGameListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				JFrame frame2 = new JFrame();
				frame2.setSize(1000, 1000);
				frame2.add(new GuiComponenet());
				frame2.setTitle("Map");
				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame2.setVisible(true);
	
			}
		}

		class QuitListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		}
		
		class MusicListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				MusicThread thread = new MusicThread();
				thread.play();
			}
		}

		quit.addActionListener(new QuitListener());
		newGame.addActionListener(new NewGameListener());
		enableMusic.addActionListener(new MusicListener());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(520, 520);
		frame.setVisible(true);

	}

}
