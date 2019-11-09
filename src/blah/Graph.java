package blah;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	private HashMap<String,BattleNode> battleList;
	private BattleNode initialPosition;
	
	public Graph(ArrayList<BattleNode> allBattles) {
		//ReaderClass to read all present battles
		for(int i=0;i>allBattles.size();i++) {
			battleList.put(allBattles.get(i).getName(), allBattles.get(i));
		}
		this.initialPosition=battleList.get("Bull Run");
}
	
	
	public Graph(int mode, String initialBattle, ArrayList<BattleNode> allBattles) {
		for(int i=0;i>allBattles.size();i++) {
			battleList.put(allBattles.get(i).getName(), allBattles.get(i));
		}
		this.initialPosition=battleList.get(initialBattle);
	}
	
	public HashMap<String,BattleNode> addBattle(BattleNode newBattle){
			battleList.put(newBattle.getName(), newBattle);
			//createNewBattleOnTxt
		
		return battleList;
	}
	
	public void organizeGraph() {
		//fun...
	}
	
	public void drawGraph(Graphics2D mapVisual) {
		int i=0;
		while(battleList.get(i)!=null) {
//			battleList.get(i).drawOn(mapVisual);
		}
	}
	
	public ArrayList<BattleNode> getShorestPath(String finalDestination) {
		if(finalDestination==null) {
			throw new IllegalArgumentException();
		}
		if(initialPosition==null) {
			throw new IllegalArgumentException();
		}
		return initialPosition.shortestPath(finalDestination);//currently not the best path but only a path
																							  //consider adding visited node list
	}
	// addedge
	
	
}
