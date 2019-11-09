package blah;

import java.util.concurrent.ConcurrentHashMap;

public class Edge {

	private BattleNode connectionOne;
	private BattleNode connectionTwo;
	private double cost;
	
	public Edge(BattleNode endOne, BattleNode endTwo, double cost) {
		this.cost=cost;
		this.connectionOne=endOne;
		this.connectionTwo=endTwo;

	}
	
	public void updateCost(int newCost) {
		this.cost=newCost;

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

	public void printName() {
		System.out.println("edge");
		System.out.println(connectionOne.battleName);
		System.out.println(connectionTwo.battleName);
		System.out.println(cost);
	}
}
