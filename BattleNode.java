
import java.awt.Color;

import java.awt.Graphics;
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

	public ArrayList<BattleNode> shortestPath(String targetName) {
		// checks if current node is desired destination
		if (this.battleName.equals(targetName)) {
			ArrayList<BattleNode> array = new ArrayList<BattleNode>();
			array.add(this);
			System.out.println("Target Node found. Returning an arrayList");
			return array;
		}

		// creates stack of edges with least edge cost on the top
		Stack<Edge> junkPile = new Stack<Edge>();
		ArrayList<Edge> copyList = edges;
		if (copyList == null) {
			System.out.println(battleName+" has no edges! It's also not the battle you are looking for...");
			return null;
		}
		
		while (copyList.size() != 0) {
			Edge mostCost = copyList.get(0);
			// compares mostCost to other costs in the copyList
			for (int i = 0; i < copyList.size(); i++) {
				if (copyList.get(i).getCost() >= mostCost.getCost()) {
					mostCost = copyList.get(i);
				}
			}
			junkPile.add(mostCost);
			copyList.remove(mostCost);
		}
		// Assumptions at this point: junk has at least one edge, junk is orders with
		// least at the top, and the target is not this node
		// Creates a comparison number and reference
				
		System.out.println(battleName);
		
		//Stuff above this should logically work...

		double leastPath = Double.POSITIVE_INFINITY;
		ArrayList<BattleNode> leastList = null;
		// while the stack is not empty, compare the costs of the edges
		while (junkPile.peek() != null) {
			Edge currentEdge = junkPile.peek();
			ArrayList<BattleNode> aListOfNodes = currentEdge.getNextNode(this).shortestPath(targetName);
			if (aListOfNodes == null) {
				junkPile.pop();
				break;
			}
			if (aListOfNodes != null) {

				// should compare the cost of the path for either default value (-infinity) or
				// the current lowest cost path

				double costOfPath = getCostOfPath(aListOfNodes);
				if (costOfPath < leastPath) {
					leastPath = costOfPath;
					leastList = aListOfNodes;
					junkPile.pop();
					break;// breaks while loop?
				}
			}
		}
		if (leastList != null) {
			leastList.add(this);
			return leastList;
		}
		return null;
	}

	
	
	public double getCostOfPath(ArrayList<BattleNode> pathList) {
		pathList.add(this);
		BattleNode currentNode;
		BattleNode nextNode;
		double cost = 0;
		for (int i = pathList.size() - 1; i >= 0; i--) {
			currentNode = pathList.get(i);
			if ((i - 1 < 0) || pathList.get(i - 1).edges == null) {
				return cost;
			}
			nextNode = pathList.get(i - 1);
			for (int j = 0; j < currentNode.edges.size(); j++) {
				if (currentNode.edges.get(j).getNextNode(currentNode) == nextNode) {
					cost += currentNode.edges.get(j).getCost();
					break;
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
				g.drawLine(this.x, this.y, (int) edges.get(i).getNextNode(this).getX(),	(int) edges.get(i).getNextNode(this).getY());
				if(edges.get(i).getNextNode(this).edges==null) {
					g.drawString("Battle you have arrived at: "+edges.get(i).getNextNode(this).getName(), 10, 780);
					g.drawString("Battle Description: "+edges.get(i).getNextNode(this).battleDescription, 10, 800);
				}
			}
		}
		}
}
