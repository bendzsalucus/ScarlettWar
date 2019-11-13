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
//		connectionOne.addEdge(this);
	}
	
	public Edge(BattleNode nextNode, double cost) {
		this.connectionTwo=nextNode;
		this.cost=cost;
	}
	
	public void updateCost(int newCost) {
		this.cost=newCost;
//		connectionOne.addEdge(this, connectionTwo, newCost);
//		connectionTwo.addEdge(this, connectionCne, newCost);
	}
	
	public BattleNode getNextNode(BattleNode currentNode) {
//		if(currentNode==this.connectionOne) {
//			return connectionTwo;
//		} else {
//			return connectionOne;
//		}
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
