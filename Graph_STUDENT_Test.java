import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Graph_STUDENT_Test {
	private GraphInterface<Town,Road> graph;
	private Town a,b,c,d,e;
	private Road w,x,y,z;
	private Town[] town;
	
	@Before
	public void setUp() throws Exception {
		 graph = new Graph();
		 town = new Town[12];

		 a = new Town("Town_a");
		 b = new Town("Town_b");
		 c = new Town("Town_c");
		 d = new Town("Town_d");
		 e = new Town("Town_e");
				
		 
		 graph.addVertex(a);
		 graph.addVertex(b);
		 graph.addVertex(c);
		 graph.addVertex(d);
		 graph.addVertex(e);
		  
		 w = graph.addEdge(a, b, 6, "Road_1");
		 x = graph.addEdge(a, d, 1, "Road_2");
		 y = graph.addEdge(d, b, 2, "Road_3");
		 z = graph.addEdge(d, e, 1, "Road_4");
	}


	@Test
	public void testGetEdge() {
		assertEquals(w, graph.getEdge(a, b));
		assertEquals(z, graph.getEdge(d, e));
	}

	@Test
	public void testAddEdge() {
		assertFalse(graph.containsEdge(e, c));
		graph.addEdge(c, e, 5, "Road_5");
		assertTrue(graph.containsEdge(e, c));
	}

	@Test
	public void testAddVertex() {
		Town newTown = new Town("Town_6");
		assertFalse(graph.containsVertex(newTown));
		graph.addVertex(newTown);
		assertTrue(graph.containsVertex(newTown));
	}

	@Test
	public void testContainsEdge() {
		assertEquals(true, graph.containsEdge(a, b));
		assertEquals(false, graph.containsEdge(b, e));
	}

	@Test
	public void testContainsVertex() {
		assertEquals(true, graph.containsVertex(new Town("Town_d")));
		assertEquals(false, graph.containsVertex(new Town("Town 123")));
	}

	@Test
	public void testEdgeSet() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Road_1", roadArrayList.get(0));
		assertEquals("Road_2", roadArrayList.get(1));
		assertEquals("Road_3", roadArrayList.get(2));
		assertEquals("Road_4", roadArrayList.get(3));
	}

	@Test
	public void testEdgesOf() {
		Set<Road> roads = graph.edgesOf(d);
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("Road_2", roadArrayList.get(0));
		assertEquals("Road_3", roadArrayList.get(1));
		assertEquals("Road_4", roadArrayList.get(2));
	}
	

	
	@Test
	public void testRemoveEdge() {
		assertEquals(true, graph.containsEdge(d, b));
		graph.removeEdge(d, b, 2, "Road_3");
		assertEquals(false, graph.containsEdge(d, b));
	}
	
	@Test
	public void testRemoveVertex() {
		assertEquals(true, graph.containsVertex(c));
		graph.removeVertex(c);
		assertEquals(false, graph.containsVertex(c));
	}

	@Test
	public void testVertexSet() {
		Set<Town> roads = graph.vertexSet();
		assertEquals(true,roads.contains(a));
		assertEquals(true, roads.contains(b));
		assertEquals(true, roads.contains(d));
		assertEquals(true, roads.contains(e));
	}
	
	 @Test
	  public void testTown1ToTown_10() {
			  ArrayList<String> path = graph.shortestPath(a,e);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("Town_a via Road_2 to Town_d 1 mi",path.get(0).trim());
			  assertEquals("Town_d via Road_4 to Town_e 1 mi",path.get(1).trim());
	}
}
