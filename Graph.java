


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Graph implements GraphInterface<Town, Road>{
	
	Set<Town> townSet = new HashSet<Town>();
	Set<Road> roadSet = new HashSet<Road>();
	
	@Override
	/**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. 
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		return iteratorEdge(sourceVertex, destinationVertex);
	}
	
	
	@Override
	/**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if(!(townSet.contains(destinationVertex) || townSet.contains(sourceVertex)))
			throw new IllegalArgumentException();
		
		if(sourceVertex == null || destinationVertex == null)
			throw new NullPointerException();
			
		Road newRoad = new Road((Town)sourceVertex, (Town)destinationVertex,  weight,  description);
		roadSet.add(newRoad);
		return newRoad;
	}

	@Override
	/**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     * @return true if this graph did not already contain the specified
     * vertex.
     * @throws NullPointerException if the specified vertex is null.
     */
	public boolean addVertex(Town v) {
		if(v == null)
			throw new NullPointerException();
		return townSet.add((Town) v);
	}
	
	/**
	 * Private class that iterators though the edges and finds the requested one.
	 * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @return an edge from the given vertexes
	 */
	private Road iteratorEdge(Town sourceVertex, Town destinationVertex) {
		Iterator<Road> it = roadSet.iterator();
		while(it.hasNext()) {
			Road road = it.next();
			
			if(road.contains((Town)sourceVertex) && road.contains((Town) destinationVertex))
				return road;
		}
		return null;
	}

	@Override
	 /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		return iteratorEdge(sourceVertex, destinationVertex) != null;
	}
	
	
	@Override
	/**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
	public boolean containsVertex(Town v) {
		Iterator<Town> it = townSet.iterator();
		
		while(it.hasNext()) {
			Town town = it.next();
			if(town.equals(v))
				return true;
		}

		return false;
	}

	@Override
	/**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	public Set<Road> edgeSet() {
		return roadSet;
	}

	@Override
	 /**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	public Set<Road> edgesOf(Town vertex) {
		if(!(townSet.contains(vertex)))
			throw new IllegalArgumentException();
		
		if(vertex == null)
			throw new NullPointerException();
		
		Set<Road> subRoads = new HashSet<Road>();
		Iterator<Road> it = roadSet.iterator();
		
		while(it.hasNext()) {
			Road road = it.next();
			if(road.contains((Town)vertex))
				subRoads.add(road);
		}
		return subRoads;
	}

	@Override
	/**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if(weight < -1 || description == null)
			return null;
		
		Road remove = (Road) iteratorEdge(sourceVertex, destinationVertex); //finds the edge to remove
		roadSet.remove(remove);
		return remove;
	}

	@Override
	/**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	public boolean removeVertex(Town v) {
		if(v == null)
			return false;
		Set<Road> subRoads = edgesOf(v);
		roadSet.removeAll(subRoads);
		
		return townSet.remove(v);
	}

	@Override
	/**
     * Returns a set of the vertices contained in this graph. 
     * @return a set view of the vertices contained in this graph.
     */
	public Set<Town> vertexSet() {
		return townSet;
	}
	
	
	 /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList shortestPath(Town sourceVertex, Town destinationVertex) {
		ArrayList <String> path = new ArrayList<String>();
		dijkstraShortestPath(sourceVertex);
	
		while(destinationVertex != sourceVertex) {
			if(backPath[towns.indexOf(destinationVertex)] == null)
				break;
			Road r = getEdge(destinationVertex, backPath[towns.indexOf(destinationVertex)]);
			path.add(0, backPath[towns.indexOf(destinationVertex)] + " via " + r.name + " to " + destinationVertex + " " + r.weight + " mi");
			destinationVertex = backPath[towns.indexOf(destinationVertex)];
		}
		return path;
	}
	
	/**
	 * Gets the weight to the starting vertex
	 * @param weight pass the array of weight values
	 * @param road current edge 
	 * @param sourceVertex
	 * @param vertex
	 * @param minAdjVertex
	 * @return the smallest weight between the Source and minAdjVertex
	 */
	private Integer getWeightToSource(int[] weight, Road road, Town sourceVertex, Town vertex, Town minAdjVertex) {	
		int wt = 0;
		int index = towns.indexOf(minAdjVertex);
		
		if(backPath[index] == null) 
			backPath[index] = vertex; 

		if(vertex.equals(sourceVertex)) {
			return road.weight;
		}
		else {
			wt += road.weight;
			while(!(vertex.equals(sourceVertex))) {
				if(backPath[towns.indexOf(vertex)] == null)
					break;
				Road r = getEdge(vertex, backPath[towns.indexOf(vertex)]);
				vertex = backPath[towns.indexOf(vertex)];
				index = towns.indexOf(vertex);
				wt += r.weight;
			}
		}return wt;
	}
	
	
	Set<Town> closed = new HashSet<Town>(); 
	Set<Town> open = new HashSet<Town>();
	List<Town> towns = new ArrayList<Town>();
	int [] weight;
	Town [] backPath;

	@SuppressWarnings("rawtypes")
	@Override
	/**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	public void dijkstraShortestPath(Town sourceVertex) 
	{
		//Initialize
		backPath = new Town[townSet.size()];
		weight = new int[townSet.size()];
		open.addAll(townSet);
		towns.addAll(townSet);
		
		//Setting all values
		for(int x=0; x<townSet.size(); x++) {
			weight[x] = Integer.MAX_VALUE;
			backPath[x] = null;
		}
			
		//Start of the first case
		closed.add(sourceVertex);
		open.remove(sourceVertex);
		int i = towns.indexOf(sourceVertex);
		weight[i] = 0;
		backPath[i] = null;
		
		Town minAdjVertex = sourceVertex;
		int minWt = Integer.MAX_VALUE;
		while(!open.isEmpty())
		{
			for(Town prevVertex: closed) 
			{
				Set edge = edgesOf(prevVertex); //gets all edges connected to vertex
				Iterator sub = edge.iterator();
				while(sub.hasNext())
				{
					Road road = (Road) sub.next();
					if(road.source.equals(prevVertex)) {
						if(closed.contains(road.destination))
							continue;
						minAdjVertex = road.destination;
					}
					else {
						if(closed.contains(road.source))
								continue;
						minAdjVertex = road.source;
					}
					
					
					int wt = getWeightToSource(weight, road, sourceVertex, prevVertex, minAdjVertex);
					int index = towns.indexOf(minAdjVertex);
					
					if(wt < weight[index]) {
						minWt = wt;
						weight[index] = minWt;
						backPath[index] = prevVertex;
					}
				}
			}
				open.remove(minAdjVertex);
				closed.add(minAdjVertex);
				
				if(open.size() == 1) {
					open.removeAll(open);
				}
		}
	}
}
