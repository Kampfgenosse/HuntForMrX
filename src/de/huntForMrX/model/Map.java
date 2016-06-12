package de.huntForMrX.model;

import java.util.ArrayList;
import java.util.LinkedList;

// TODO Javadoc, when class is fully implemented.
public class Map {
	
	public static LinkedList<Node> nodes;
	public static LinkedList<Connection> connections;
	public static ArrayList<Stopover> stopovers;
	
	public Map () {
		nodes = new LinkedList<Node>();
		connections = new LinkedList<Connection>();
		stopovers = new ArrayList<Stopover>();
	}

	@Override
	public String toString() {
		return "Nodes = "+nodes.size()+", Connections = "+connections.size()+", Stopovers = "+stopovers.size()+".";
	}
	
}
