package de.huntForMrX.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import de.huntForMrX.model.Connection;
import de.huntForMrX.model.Map;
import de.huntForMrX.model.Node;
import de.huntForMrX.model.Stopover;

/**
 * The sole purpose of <code>MapReader</code> class is to import the information about {@link node}s 
 * and {@link connection}s from a text file into the program. Used at the launch of the program.<p>
 * 
 * The text file needs to have the following structure:<p>
 * 
 * Comment lines start with a "#".<p>
 * 
 * Nodes are qualified as:<br>
 * node [whitespace] nodeID [whitespace] x [whitespace] y<p>
 * 
 * Connections are qualified as:<br>
 * "transport" [whitespace] nodeID [whitespace] nodeID<br>
 * where "transport" is either taxi, bus, subway or ferry (without the "")<p>
 * 
 * Columns can be separated by whitespaces, e.g. [tab] or [space].
 * 
 * 
 * @author Kampfgenosse
 *
 */
public class MapReader {
	
	private final static String FILE_NAME = "resources/london.map";	
	private static List <String> mapStrings;
	
	/**
	 * Reads ALL the lines in a text file and returns them stored in a <code>List</code> of <code>string</code>s.
	 * 
	 * @param aFileName - location and file name (full path)
	 * 
	 * @return EVERY line of the text file as a <code>string</code>, all of them in a <code>List</code>.
	 * 
	 * @throws IOException
	 */
	private List<String> readSmallTextFile (String aFileName) throws IOException {
		Path path = Paths.get(aFileName);
		return Files.readAllLines(path);
	}
	
	/**
	 * Processes <code>string</code>s in a <code>List</code>.<br>
	 * Stores them as {@link Node}s, {@link Connection}s and {@link Stopover}s.
	 */
	private void sortStrings() {
		// every line of the primary text file (stored in a list of strings) is looked at 
		for (String item : mapStrings) {
			// Comment lines and empty lines are skipped
			if (!item.startsWith("#") && item.length()>0) {
				
				// The line is split into an Array of strings, using whitespaces (e.g. tab or space) as a delimiter
				String[] parts = item.split("\\s+");
				
				// if line starts with "node"
				if (parts[0].equals("node")) {
					Node n = new Node(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
					Map.nodes.add(n);
					// stopovers are set up simultaneously
					Map.stopovers.add(new Stopover(Map.nodes.getLast()));
				} else
					if (parts[0].equals("taxi") || parts[0].equals("bus") || parts[0].equals("subway") || parts[0].equals("ferry")) {
						for (Node nd : Map.nodes) {
							// node ids need to be converted from string to integer
							if (nd.getId()==Integer.parseInt(parts[1]) || nd.getId()==Integer.parseInt(parts[2])) {
								try {
									// if the last connection in the list is already complete, set up a new connection
									if (Map.connections.getLast().has2Nodes()) {
										Map.connections.add(new Connection(nd));
										Map.connections.getLast().setConType(Connection.convertToConType(parts[0]));
									} else if (Map.connections.getLast().has1Node()) {
										// if the last connection has only one node set up, register the 2nd node
										Map.connections.getLast().setNode2(nd);
									}
								} catch (NoSuchElementException e) {
									// catches the first assignment, when "connections.getLast()" produces an exception. (= empty list)
									Map.connections.add(new Connection(nd));
									Map.connections.getLast().setConType(Connection.convertToConType(parts[0]));
								} // try - catch
							} // if (nd. || nd.)
						} // for (nodes)
					} // if (taxi, bus, etc)
			} // if (startswith #)
		} // for (mapstrings)
		
		// for every stopover (= number of nodes) all connections are checked (and added, if necessary)
		for (Stopover stopover : Map.stopovers) {
			for (Connection connection : Map.connections) {
				stopover.addConnection(connection);
			}
		}
	} // sortStrings ()

	public MapReader () {
		// Map is static
		new Map();
		mapStrings = new ArrayList<String>();
		try {
			mapStrings = readSmallTextFile(FILE_NAME);
		} catch (IOException e) {
			// TODO handle exception
			e.printStackTrace();
		}
		sortStrings();
	}

//	public static void main(String[] args) {
//		new MapReader();
//		
//		// test output
////		for (Node knoten : Map.nodes) {
////			System.out.println(knoten);
////		}
////		
////		for (Connection verbindung : Map.connections) {
////			System.out.println(verbindung);
////		}
//		
//		for (Stopover haltestelle : Map.stopovers) {
//			System.out.println(haltestelle);
//		}
//		
//	}

}
