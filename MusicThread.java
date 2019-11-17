

import java.io.File;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.print.attribute.standard.Media;

import javazoom.jl.player.Player;

import java.net.MalformedURLException;
import java.net.URL;

public class MusicThread {

	public void play(String path) {
		new Thread() {
			private Player player;

			public void run() {
				try {
					FileInputStream fileInputStream = new FileInputStream(path);
					player = new Player(fileInputStream);
					player.play();
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}.start();

	}
}
