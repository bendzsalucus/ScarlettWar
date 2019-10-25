package blah;

import java.awt.BorderLayout;
import java.awt.Dimension;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gui {
	public static Dimension windowSize = new Dimension(1000, 1000);
	
	public Gui() {
		JButton b1 = new JButton("Start");
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		JFrame frame2 = new JFrame();
		
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame2.setSize(windowSize);
				frame2.setTitle("Map");
				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame2.setVisible(true);
			}
		});
		
		panel.add(b1);
		
		frame.add(panel, BorderLayout.SOUTH);
		frame.setSize(windowSize);
		frame.setTitle("Welcome");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Gui();
	}
}
