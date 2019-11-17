

import java.util.concurrent.ConcurrentHashMap;


public class Edge {

	public BattleNode connectionOne;
	public BattleNode connectionTwo;
	private double cost;
	
	public Edge(BattleNode endOne, BattleNode endTwo, double cost) {
		this.cost=cost;
		this.connectionOne=endOne;
		this.connectionTwo=endTwo;
	}
	
	public Edge(BattleNode nextNode, double cost) {
		this.connectionTwo=nextNode;
		this.cost=cost;
	}
	
	public void updateCost(int newCost) {
		this.cost=newCost;
	}
	
	public BattleNode getNextNode() {
		return connectionTwo;
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
