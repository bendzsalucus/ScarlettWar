

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This is what handles the Main Menu Frame. This handles the buttons for the
 * frame at the bottom.
 * 
 * @author Lucus Bendzsa. Created May 2019.
 */
public class Scarlett {

	private JLabel label = new JLabel();
	private JFrame frame;

	public static void main(String[] args) {
		new Scarlett();
	}

	public Scarlett() {

	
		
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
				
				MyBoolean booly = new MyBoolean();
				JFrame frame2 = new JFrame();
				JButton nextB=new JButton("Go!");
				JButton quitB=new JButton("Quit to Main Menu");
				GuiComponenet component=new GuiComponenet(booly);
				JTextField inputText1 = new JTextField("Battle of Wilderness");
				JTextField inputText2 = new JTextField("Battle of Winchester");

				frame2.setPreferredSize(new Dimension(1000, 1000));
				frame2.add(nextB);
				nextB.setBounds(830, 880, 70, 40);
				frame2.add(quitB);
				quitB.setBounds(450, 850, 140, 50);
				
				inputText1.setBounds(750, 845, 100, 30);
				frame2.add(inputText1);
				
				inputText2.setBounds(880, 845, 100, 30);
				frame2.add(inputText2);
				
				//add extras ABOVE this line
				frame2.add(component);
					
				class NextListner implements ActionListener{
					public void actionPerformed(ActionEvent e) {
						component.insertInput(inputText1.getText(), 1);
						component.insertInput(inputText2.getText(), 2);
						component.updateScreen();
						
					}
				}

				quitB.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(true);;
						frame2.dispose();
						return;
					}
				});
				
				nextB.addActionListener(new NextListner());
				frame2.setTitle("Map");
				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame2.pack();
				frame2.setVisible(true);
				frame2.setResizable(false);
				

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
class MyBoolean {
	private boolean value = true;

	public void setFalse() {
		value = false;
	}

	public boolean getValue() {
		return value;
	}

}
