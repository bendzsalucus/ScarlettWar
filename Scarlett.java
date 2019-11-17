
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * This is what handles the Main Menu Frame. This handles the buttons for the
 * frame at the bottom.
 * 
 * @author Lucus Bendzsa. Created May 2019.
 */
public class Scarlett {
	public int i;
	private JFrame frame;

	public static void main(String[] args) {
		new Scarlett(7);
	}

	public Scarlett(int i) {
		this.i = i;
		frame = new JFrame();
		frame.setTitle("Future War");
		JPanel panel = new JPanel();
		MainHelper menuComp = new MainHelper("Resoruces//FutureWarSim.jpg");
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

				JButton nextB = new JButton("Go!");
				JButton quitB = new JButton("Quit to Main Menu");
				GuiComponenet component = new GuiComponenet(i);

				frame2.setPreferredSize(new Dimension(1000, 1000));
				frame2.add(nextB);
				nextB.setBounds(807, 900, 70, 40);
				frame2.add(quitB);
				quitB.setBounds(0, 920, 140, 50);

				String[] choices = { "Black_Widow", "Hulk", "Iron_Man", "Black_Panther", "Groot", "Rocket", "Gamora",
						"Hawkeye", "Thor", "Captain_America", "Spider_Man", };

				String[] choices2 = { "Hulk", "Black_Widow", "Iron_Man", "Black_Panther", "Groot", "Rocket", "Gamora",
						"Hawkeye", "Thor", "Captain_America", "Spider_Man", };

				final JComboBox<String> cb1 = new JComboBox<String>(choices);
				final JComboBox<String> cb2 = new JComboBox<String>(choices2);

				cb1.setBounds(750, 800, 200, 30);
				cb2.setBounds(750, 850, 200, 30);

				frame2.add(cb1);
				frame2.add(cb2);

				// add extras ABOVE this line
				frame2.add(component);

				class NextListner implements ActionListener {
					public void actionPerformed(ActionEvent e) {
						component.battles.stream().forEach(r -> {
							System.out.println(r.battleName);
						});
						String battle1 = (String) cb1.getSelectedItem();
						String battle2 = (String) cb2.getSelectedItem();
						// . . 
					    StringBuilder sb = new StringBuilder(battle1);
					    sb.insert(0, ". . ");
					    battle1 = sb.toString();
					    StringBuilder sb2 = new StringBuilder(battle2);
					    sb2.insert(0, ". . ");
					    battle2 = sb2.toString();
						
					    System.out.println(battle1 + "  " + battle2);
						
						BattleNode bat1 = component.searchFor(battle1);
						int lenny1 = bat1.battleDescription.length();
						String cat1 = "";
						String cat2 = "";

						if (lenny1 > 50) {
							cat1 = bat1.battleDescription.substring(0, lenny1 / 2);
							cat2 = bat1.battleDescription.substring(lenny1 / 2);
						} else {
							cat1 = bat1.battleDescription;
							cat2 = "";
						}
						JTextArea bob = new JTextArea();
						JTextArea bill = new JTextArea();
						bob.setBounds(4, 750, 700, 15);
						bob.setText(cat1);
						bob.setEditable(false);
						bill.setBounds(4, 765, 700, 15);
						bill.setText(cat2);
						bill.setEditable(false);

						BattleNode bat2 = component.searchFor(battle2);
						int lenny2 = bat2.battleDescription.length();
						String cat3 = "";
						String cat4 = "";

						if (lenny2 > 75) {
							cat3 = bat2.battleDescription.substring(0, lenny1 / 2);
							cat4 = bat2.battleDescription.substring(lenny1 / 2);
						} else {
							cat3 = bat2.battleDescription;
							cat4 = "";
						}
						JTextArea bob2 = new JTextArea();
						JTextArea bill2 = new JTextArea();
						bob2.setBounds(4, 834, 700, 15);
						bob2.setText(cat3);

						bob2.setEditable(false);
						bill2.setBounds(4, 849, 700, 15);
						bill2.setText(cat4);
						bill2.setEditable(false);

						frame2.add(bob);
						frame2.add(bill);
						frame2.add(bob2);
						frame2.add(bill2);
						component.insertInput(battle1, 1);
						component.insertInput(battle2, 2);
						component.updateScreen();
						component.addInfo(battle1, battle2);

					}
				}

				quitB.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(true);
						;
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
				new Main();

			}
		}

		class MusicListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				MusicThread thread = new MusicThread();
				thread.play("Resoruces\\Avengers.mp3");
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
