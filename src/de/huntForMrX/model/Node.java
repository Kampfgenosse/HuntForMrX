package de.huntForMrX.model;

/**
 * Node class with constructor for all fields but only getter methods, for that the node cannot be modified after its creation.<br>
 * A Node has an ID, x coordinate and y coordinate.<br>
 * Nodes become part of a {@link Stopover}.
 * 
 * @author Kampfgenosse
 *
 */
public class Node {
	
	private int id;
	private int x_coord;
	private int y_coord;
	
	/**
	 * Full constructor:<br>
	 * All fields are set.
	 * 
	 * @param id - not produced, but assigned from the map file.
	 * @param x_coord - on the london map.
	 * @param y_coord - on the london map.
	 */
	public Node(int id, int x_coord, int y_coord) {
		this.id = id;
		this.x_coord = x_coord;
		this.y_coord = y_coord;
	}

	public int getId() {
		return id;
	}
	
	public int getX_coord() {
		return x_coord;
	}
	
	public int getY_coord() {
		return y_coord;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", x_coord=" + x_coord + ", y_coord=" + y_coord + "]";
	}	
	
}
