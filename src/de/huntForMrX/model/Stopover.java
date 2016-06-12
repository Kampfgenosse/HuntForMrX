package de.huntForMrX.model;

import java.util.LinkedList;
import java.util.NoSuchElementException;

// TODO expand javadoc
/**
 * <code>Stopovers</code> consist of {@link Node}s and {@link DirectedConnection}s.<br>
 * 
 * @author Kampfgenosse
 *
 */
public class Stopover {
	
	public Node node;
	private LinkedList<DirectedConnection> connections;
	
	/**
	 * <code>DirectedConnection</code>s are created using the bidirectional {@link Connection}.<br>
	 * A <code>DirectedConnection</code> is unique to its owning {@link Stopover} 
	 * and consists of only one {@link Node} (the end node) and the {@link ConType}.<br>
	 * The start <code>node</code> of the <code>DirectedConnection</code> is the one of the owning <code>stopover</code>.
	 * 
	 * @author Kampfgenosse
	 *
	 */
	private class DirectedConnection {
		Node endNode;
		ConType conType;
		
		/**
		 * End <code>node</code> is set in this constructor, but <code>conType</code> becomes <code>null</code>. 
		 * @param endNode
		 */
		public DirectedConnection (Node endNode) {
			this.endNode = endNode;
			conType = null;
		}

		public ConType getConType() {
			return conType;
		}

		public void setConType(ConType conType) {
			this.conType = conType;
		}

		public Node getEndNode() {
			return endNode;
		}

		@Override
		public String toString() {
			return " [endNode=" + endNode.getId() + ", conType=" + conType + "]";
		}		
		
	} // private class DirectedConnection
	
	/**
	 * The location (={@link node}) is set using this constructor 
	 * and an empty <code>LinkedList</code> of {@link DirectedConnection}s is created.<p>
	 * There are <b>no</b> <code>stopover</code>s <b>without</b> <code>DirectedConnection</code>s.
	 * @param node
	 */
	public Stopover(Node node) {
		this.node = node;
		connections = new LinkedList<DirectedConnection>();
	}
	
	/**
	 * Adds a {@link DirectedConnection} to the <code>LinkedList</code> of <code>DirectedConnection</code>s 
	 * of this <code>stopover</code>.<br>
	 * It does so, searching a {@link Connection} for the <code>stopover</code>'s <code>node</code> 
	 * and then setting the other <code>node</code> as the <code>DirectedConnection</code>'s end node.
	 * 
	 * @param con - <code>Connection</code> to examine
	 * 
	 * @return true, if the <code>Connection</code> had the <code>stopover</code>'s <code>node</code> as a part
	 */
	public boolean addConnection (Connection con) {		

		boolean found = false;
		// node1 of the connection is the stopover's node
		if (con.getNode1() == node) {
			connections.add(new DirectedConnection(con.getNode2()));
			found = true;
		}
		// node2 of the connection is the stopover's node
		else if (con.getNode2() == node){
			connections.add(new DirectedConnection(con.getNode1()));
			found = true;
		}
		// if search was successful, set the connection type (taxi, etc), otherwise return "false".
		if (found) {
			try {
				connections.getLast().setConType(con.getConType());
				return true;
			} catch (NoSuchElementException e) {
				// shouldn't happen, since a new DirectedConnection should have been created above
				// TODO: handle exception
			}
		}
		return false;
	}

	// TODO Javadoc, when method really used
	public LinkedList<DirectedConnection> getConnections() {
		return connections;
	}

	@Override
	public String toString() {
		return "Stopover (" + node + ", connections= " + connections.size() + " " + connections + ")";
	}
	
	
}
