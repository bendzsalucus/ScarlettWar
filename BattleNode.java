
import java.awt.Color;

import java.awt.Graphics;
import java.time.temporal.JulianFields;
import java.util.ArrayList;
import java.util.Stack;

public class BattleNode extends GameObject {

	public int x;
	public int y;
	public int width;
	public int height;
	public String battleName;
	public String battleDescription;
	public int soliderCount;
	public String imgPath;
	public boolean isUnion;
	public Color color;
	public ArrayList<Edge> edges;
	public int xOff;
	public int yOff;

	public BattleNode(double x, double y, double width, double height, String battleName, String description,
			int soldierCount, String imgPath, boolean isUnion, Color color, ArrayList<Edge> edges, int xOff, int yOff) {
		super(x, y, width, height);
		this.battleName = battleName;
		this.battleDescription = description;
		this.soliderCount = soldierCount;
		this.imgPath = imgPath;
		this.isUnion = isUnion;
		this.color = color;
		this.edges = edges;
		this.x = (int) x;
		this.y = (int) y;
		this.xOff = xOff;
		this.yOff = yOff;
	}

	public void addEdge(Edge edge, BattleNode node, double cost) {
		if (edges.contains(edge)) {
			for (int i = 0; i < edges.size(); i++) {
				if (edge == edges.get(i)) {
					edges.remove(i);
				}
			}
		}
		edges.add(new Edge(this, node, cost));
	}

	public ArrayList<BattleNode> shortestPath(String targetName, ArrayList<BattleNode> visitedNodes) {
		// checks if current node is desired destination
		if (this.battleName.equals(targetName)) {
			System.out.println("Fount it");
			visitedNodes.add(this);
			return visitedNodes;
		}

		// creates stack of edges with least edge cost on the top
		Stack<Edge> organizedPile = new Stack<Edge>();
		ArrayList<Edge> copyList = new ArrayList<>();
		if (edges == null || edges.size() == 0) {
			System.out.println(battleName + " has no edges! It's also not the battle you are looking for...");
			return null;
		}
		edges.stream().forEach(e -> {
			if(!visitedNodes.contains(e.connectionTwo)) {
			copyList.add(e);}
		});
		while (copyList.size() != 0) {

			Edge mostCost = copyList.get(0);

			// compares mostCost to other costs in the copyList
			for (int i = 0; i < copyList.size(); i++) {

				if (copyList.get(i).getCost() >= mostCost.getCost()) {
					mostCost = copyList.get(i);
				}
			}
			organizedPile.add(mostCost);
			copyList.remove(mostCost);
		}

		// Assumptions at this point: junk has at least one edge, junk is orders with
		// least at the top, and the target is not this node
		// Creates a comparison number and reference

		double leastPath = Double.POSITIVE_INFINITY;
		ArrayList<BattleNode> visitedCopy = new ArrayList<BattleNode>();
		visitedNodes.stream().forEach(e -> visitedCopy.add(e));
		ArrayList<BattleNode> leastList = new ArrayList<BattleNode>();
		if (organizedPile.size() == 0) {
			System.out.println("No unvisited edges. Returning from " + battleName);
			return null;
		}

		// while the stack is not empty, compare the costs of the edges
		while (organizedPile.size() > 0) {
			if (organizedPile.size() == 0) {
				System.out.println("Have gone through all available paths.");
				break;
			}
			Edge currentEdge = organizedPile.peek();
			if (!visitedCopy.contains(this)) {
				visitedCopy.add(this);
			}
			ArrayList<BattleNode> aListOfNodes = currentEdge.getNextNode().shortestPath(targetName, visitedCopy);

			if (aListOfNodes == null || aListOfNodes.size() == 0) {
				organizedPile.pop();
				continue;
			}
			if (aListOfNodes.size() > 0) {

				// should compare the cost of the path for either default value (-infinity) or
				// the current lowest cost path

				double costOfPath = getCostOfPath(aListOfNodes);
				if (costOfPath < leastPath) {
					System.out.println("Changed least path with " + costOfPath + " from " + leastPath);
					leastPath = costOfPath;
					leastList = aListOfNodes;
					organizedPile.pop();
					continue;
				}
			}
			organizedPile.pop();
		}

		// Finished loop with least list finalized
		if (leastList != null && leastList.size() > 0) {
			return leastList;
		}

		// should return if the entire stack has been emptied...
		return null;
	}

	public double getCostOfPath(ArrayList<BattleNode> pathList) {
		BattleNode currentNode;
		BattleNode nextNode;
		double cost = 0;
		for (int i = pathList.size() - 1; i >= 0; i--) {
			currentNode = pathList.get(pathList.size() - 1 - i);
			if ((pathList.size() - i >= pathList.size()) || pathList.get(pathList.size() - i).edges == null) {
				return cost;
			}
			nextNode = pathList.get(pathList.size() - i);
			for (int j = 0; j < currentNode.edges.size(); j++) {
				if (currentNode.edges.get(j).getNextNode() == nextNode) {
					cost += currentNode.edges.get(j).getCost();
				}
			}
		}
		return cost;
	}

	public String getName() {
		return battleName;
	}

	public void drawOn(Graphics g) {
		g.setColor(this.color);
		g.fillRect(x, y, width, height);
		g.drawRect(this.x, this.y, this.width, this.height);
		if (this.edges != null) {
			for (int i = 0; i < edges.size(); i++) {
				g.drawLine(this.x, this.y, (int) edges.get(i).getNextNode().getX(),
						(int) edges.get(i).getNextNode().getY());
				if (edges.get(i).getNextNode().edges == null) {
					g.drawString("Battle you have arrived at: " + edges.get(i).getNextNode().getName(), 10, 780);
					g.drawString("Battle Description: " + edges.get(i).getNextNode().battleDescription, 10, 800);
				}
			}
		}
	}
}
