import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface{
	private Graph graph = new Graph();

	@Override
	/**
    * Adds a road with 2 towns and a road name
    * @param town1 name of town 1 (lastname, firstname)
    * @param town2 name of town 2 (lastname, firstname)
    * @param roadName name of road
    * @return true if the road was added successfully
    */
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		graph.addEdge(new Town(town1),new Town(town2),weight,roadName );
	    return true;
	}

	@Override
	/**
     * Returns the name of the road that both towns are connected through
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return name of road if town 1 and town2 are in the same road, returns null if not
     */
	public String getRoad(String town1, String town2) {
		 return graph.getEdge(new Town(town1), new Town(town2)).getName();
	}

	@Override
	/**
     * Adds a town to the graph
     * @param v the town's name  (lastname, firstname)
     * @return true if the town was successfully added, false if not
     */
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}

	@Override
	/**
     * Gets a town with a given name
     * @param name the town's name
     * @return the Town specified by the name, or null if town does not exist
     */
	public Town getTown(String name) {
		if(graph.containsVertex(new Town(name)) == true)
			return new Town(name);
		return null;	
	}

	@Override
	/**
     * Determines if a town is already in the graph
     * @param v the town's name
     * @return true if the town is in the graph, false if not
     */
	public boolean containsTown(String v) {
		 return graph.containsVertex(new Town(v));
	}

	@Override
	/**
     * Determines if a road is in the graph
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return true if the road is in the graph, false if not
     */
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.getEdge(new Town(town1), new Town(town2)) != null;
	}

	@Override
	/**
     * Creates an arraylist of all road titles in sorted order by road name
     * @return an arraylist of all road titles in sorted order by road name
     */
	public ArrayList<String> allRoads() {
		ArrayList<String> roads= new ArrayList<String>();
		Set<Road> set = graph.roadSet;
		for(Road t : set) {
			roads.add(t.getName());
		}
		Collections.sort(roads);
		return roads;
	}

	@Override
	/**
     * Deletes a road from the graph
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @param road the road name
     * @return true if the road was successfully deleted, false if not
     */
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		return graph.removeEdge(new Town(town1),new Town(town2),0,road) != null;
	}

	@Override
	/**
     * Deletes a town from the graph
     * @param v name of town (lastname, firstname)
     * @return true if the town was successfully deleted, false if not
     */
	public boolean deleteTown(String v) {
		return graph.removeVertex(new Town(v));
	}

	@Override
	/**
     * Creates an arraylist of all towns in alphabetical order (last name, first name)
     * @return an arraylist of all towns in alphabetical order (last name, first name)
     */
	public ArrayList<String> allTowns() {
		ArrayList<String> towns= new ArrayList<String>();
		Set<Town> set = graph.townSet;
		for(Town t : set) {
			towns.add(t.getName());
		}
		Collections.sort(towns);
		return towns;
	}

	@SuppressWarnings("unchecked")
	@Override
	 /**
     * Returns the shortest path from town 1 to town 2
     * @param town1 name of town 1 (lastname, firstname)
     * @param town2 name of town 2 (lastname, firstname)
     * @return an Arraylist of roads connecting the two towns together, null if the
     * towns have no path to connect them.
     */
	public ArrayList<String> getPath(String town1, String town2) {
		return graph.shortestPath(new Town(town1),new Town(town2));
	}

	
	public void populateTownGraph(File selectedFile) throws FileNotFoundException {
		Scanner scan = new Scanner(selectedFile);
		while(scan.hasNextLine()) {
			String [] line = scan.nextLine().split("[,;]");
			graph.addVertex(new Town(line[2]));
			graph.addVertex(new Town(line[3]));
			graph.addEdge(new Town(line[2]), new Town(line[3]), Integer.valueOf(line[1]), line[0]);
		}
		scan.close();
	} //Example: I270-N,14;Frederick;Clarksburg

}
