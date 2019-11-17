
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This is what handles the Main Menu Frame. This handles the buttons for the
 * frame at the bottom.
 * 
 * @author Lucus Bendzsa. Created May 2019.
 */
public class Main {

	private JLabel label = new JLabel();
	private JFrame frame;

	public static void main(String[] args) {
		new Main();
	}

	public Main() {

		frame = new JFrame();
		frame.setTitle("War Simulator by Second Order");
		JPanel panel = new JPanel();
		MainHelper menuComp = new MainHelper("Resoruces//WarSim.jpg");
		frame.getContentPane().setBackground(Color.BLACK);
		frame.add(menuComp, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.SOUTH);
		frame.setFocusable(true);
		frame.setBackground(Color.PINK);
		JButton newGameCivil = new JButton("Civl War Classic");
		JButton newGameFuture = new JButton("The Future!");
		JButton quit = new JButton("Quit");
		panel.setBackground(Color.BLACK);
		panel.add(newGameCivil);
		panel.add(newGameFuture);
		panel.add(quit);

		class CivilListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new CivilStarter(6);
			}

		}

		class QuitListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

			}
		}
		
		class FutureListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Scarlett(7);
			}

		}

		quit.addActionListener(new QuitListener());
		newGameCivil.addActionListener(new CivilListener());
		newGameFuture.addActionListener(new FutureListener());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(520, 520);
		frame.setVisible(true);

	}

}
