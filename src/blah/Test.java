package blah;

import java.util.ArrayList;

public class Test {

	public static void main(String args[]) {
		NodeImporter importer = new NodeImporter(1);
		ArrayList<BattleNode> battles = importer.getBattles();
		battles.stream().forEach(e -> {
//			System.out.println("Battle Name: " + e.battleName);
//			System.out.println("Battle Description: " + e.battleDescription);
//			System.out.println("Soldier Count: " + e.soliderCount);
//			System.out.println("imgPath: " + e.imgPath);
//			System.out.println("x, y: " + e.x + " , " + e.y);
//			System.out.println("width: " + e.width);
//			System.out.println("height: " + e.height);
//			System.out.println("isUnion:" + e.isUnion);
//			System.out.println("edges: " + e.edges);
//			System.out.println("");
			if (e.edges != null) {
				e.edges.stream().forEach(f -> {
					if (f != null) {
						System.out.println("BATTLE:    " + e.battleName);
						f.printName();
					}
				});
			}

		});

	}
}
