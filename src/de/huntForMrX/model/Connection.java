package de.huntForMrX.model;

/**
 * Connection is mainly used during initialization.
 * It provides an ID, the connection type ({@link ConType}) and the two {@link Node}s of a bidirectional connection.
 * 
 * @author Kampfgenosse
 *
 */
public class Connection {
	
	private static int nextID = 1;
	
	private final int id;
	private ConType conType;
	private Node node1;
	private Node node2;	
	
	/**
	 * Simplest constructor. Provides ID and initializes fields to <code>null</code>.
	 */
	public Connection () {
		id = nextID++;
		node1 = null;
		node2 = null;
		conType = null;
	}
	
	/**
	 * Provides ID and initializes fields <code>conType</code> and <code>node2</code> to <code>null</code>.
	 * Sets <code>node1</code>.
	 * 
	 * @param node1
	 */
	public Connection (Node node1) {
		id = nextID++;
		this.node1 = node1;
		node2 = null;
		conType = null;
	}
	
	/**
	 * Provides ID and initializes <code>conType</code> to <code>null</code>.
	 * Sets <code>node1</code> and <code>node2</code>.
	 * 
	 * @param node1
	 * @param node2
	 */
	public Connection (Node node1, Node node2) {
		id = nextID++;
		this.node1 = node1;
		this.node2 = node2;
		conType = null;
	}
	
	/**
	 * Full constructor:
	 * Provides ID. Sets <code>conType</code>, <code>node1</code> and <code>node2</code>.
	 * 
	 * @param conType
	 * @param node1
	 * @param node2
	 */
	public Connection(ConType conType, Node node1, Node node2) {
		id = nextID++;
		this.conType = conType;
		this.node1 = node1;
		this.node2 = node2;		
	}
	
	public void setConType(ConType conType) {
		this.conType = conType;
	}

	public void setNode1(Node node1) {
		this.node1 = node1;
	}

	public void setNode2(Node node2) {
		this.node2 = node2;
	}
	
	public int getId() {
		return id;
	}

	public ConType getConType() {
		return conType;
	}

	public Node getNode1() {
		return node1;
	}

	public Node getNode2() {
		return node2;
	}
	
	/**
	 * Checks if the <code>connection</code> has a value for <code>node1</code> and <code>null</code> for <code>node2</code>.
	 * 
	 * @return boolean.
	 */
	public boolean has1Node () {
		if (node1 != null && node2 == null) return true;
		else return false;
	}
	
	/**
	 * Checks if the <code>connection</code> has a value for both <code>node1</code> and <code>node2</code>.
	 * 
	 * @return boolean
	 */
	public boolean has2Nodes () {
		if (node1 != null && node2 != null) return true;
		else return false;
	}

	/**
	 * Converts either of the 4 <code>string</code>s "taxi", "bus", "subway" or "ferry" into the corresponding {@link ConType}.
	 * 
	 * @param conType - a <code>string</code>
	 * 
	 * @return {@link ConType} or <code>null</code>, if conversion fails.
	 */
	public static ConType convertToConType (String conType) {
		switch (conType) {
		case "taxi":
			return ConType.TAXI;
		case "bus":
			return ConType.BUS;
		case "subway":
			return ConType.SUBWAY;
		case "ferry":
			return ConType.FERRY;
		default:
			return null;
		}
	}

	@Override
	public String toString() {
		return "Connection [id=" + id + ", conType=" + conType + ", node1=" + node1.getId() + ", node2=" + node2.getId() + "]";
	}
	
	
}
