package blah;
import BattleNode;

public class Edge {

	private BattleNode connectionOne;
	private BattleNode connectionTwo;
	private double cost;
	
	public Edge(BattleNode endOne, BattleNode endTwo, double cost) {
		this.cost=cost;
		this.connectionOne=endOne;
		this.connectionTwo=endTwo;
		connectionOne.addEdge(this);
		connectionTwo.addEdge(this);
	}
	
	public void updateCost(int newCost) {
		this.cost=newCost;
		connectionOne.addEdge(this);
		connectionTwo.addEdge(this);
	}
	
	public BattleNode getNextNode(BattleNode currentNode) {
		if(currentNode==this.connectionOne) {
			return connectionTwo;
		} else {
			return connectionOne;
		}
	}
	public double getCost() {
		return this.cost;
	}
}
