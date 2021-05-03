
public class Road implements Comparable<Road>{

	protected Town source;
	protected Town destination;
	protected int weight; //is degrees
	protected String name;
	
	public Road(Town source, Town destination, int degrees, String name) {
		this.source = source;
		this.destination = destination;
		this.weight = degrees;
		this.name = name;
	}
	
	//Constructor with weight preset at 1 
	public Road(Town source, Town destination, String name) {
		this.source = source;
		this.destination = destination;
		this.name = name;
	}
	
	/**
	 * Returns true only if the edge = (v1, v2) contains the given town 
	 * @param town
	 * @return true only if the edge is connected to the given vertex
	 */
	public boolean contains(Town town) {
		return (this.getDestination().equals(town) || this.getSource().equals(town));
	}
	
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}
	
	public Town getDestination() {
		return destination;
	}
	
	public Town getSource() {
		return source;
	}
	
	@Override
	/**
	 * 0 if the road names are the same, a positive or negative number if the road names are not the same
	 */
	public int compareTo(Road o) {
		return o.name.compareTo(this.name);
	}

	public int getWeight() {
		return weight;
	}
	
	/**
	 * Returns true if each of the ends of the road r is the same as the ends of this road.
	 * Remember that a road that goes from point A to point B is the same as a road that goes from point B to point A. 
	 */
	public boolean equals(Object r) {
		Road rd = (Road) r;
		if(rd.contains(destination) && rd.contains(source)) 
			return true;
		return false;
	}
	
}
