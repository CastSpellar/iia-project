package searchalgorithm;
import java.util.*;
import searchproblem.State;
import searchproblem.Arc;

public class Node {
	private State s;
	private Node parent;
	private Object action;
	private double pathCost;
	private int depth;
	
	public Node(State root) {
		this.s = root;
		this.parent = null;
		this.action = null;
		this.pathCost = 0.0;
		this.depth=0;
	}
	
	Node(Node parent, Arc step) {
		this.s = step.getChild();
		this.parent = parent;
		this.action = step.getAction();
		this.pathCost = parent.pathCost+step.getStepCost();
		this.depth = parent.getDepth()+1;
	}
	
	public List<Node> Expand() {
		
		List<Node> successors = new LinkedList<Node>();
		
		for (Arc a : s.successorFunction()) {
			Node s = new Node(this,a);
			successors.add(s);
		}
		return successors;
	}

	public State getState() {
		return s;
	}

	public Node getParent() {
		return parent;
	}

	public Object getAction() {
		return action;
	}
	
	public double getPathCost() {
		return pathCost;
	}

	public int getDepth() {
		return depth;
	}
	
	public List<Object> getPath() {
		Vector<Object> path = new Vector<Object>(depth);
		path.setSize(depth);
		Node n = this;
		while ( n.getDepth() > 0 ) {
			path.setElementAt(n.getAction(),n.getDepth()-1);
			n = n.getParent();
		}
		return path;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((s == null) ? 0 : s.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Node other = (Node) obj;
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equals(other.s))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node{depth=" + depth + ", cost=" + pathCost + ", state=" + s.toString() + "}";
	}
	
}
