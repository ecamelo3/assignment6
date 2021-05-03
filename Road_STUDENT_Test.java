import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Road_STUDENT_Test {
	private Road road1, road2, road3;
	private Graph graph;
	private String[] town = new String[5];

	@BeforeEach
	void setUp() throws Exception {
		graph = new Graph();
		
		for(int i = 0; i < 5; i++) {
			town[i] = "Town " + i;
			graph.addVertex(new Town(town[i]));
		}
		
		road1 = graph.addEdge(new Town(town[0]), new Town(town[2]), 4, "1");
		road2 = graph.addEdge(new Town(town[1]), new Town(town[2]), 5, "2");
		road3 = graph.addEdge(new Town(town[3]), new Town(town[4]), 6, "3");
	}

	@AfterEach
	void tearDown() throws Exception {
		graph = null;
	}

	@Test
	void testContains() {
		Town t1 = new Town(town[0]);
		assertTrue(road1.contains(t1));
	}
	
	@Test
	void testToString() {
		assertEquals("1", road1.toString());
		assertEquals("2", road2.toString());
		assertEquals("3", road3.toString());
		
	}
	
	@Test
	void testGetName() {
		assertEquals("1", road1.getName());
		assertEquals("2", road2.getName());
		assertEquals("3", road3.getName());
	}

	@Test
	void testGetDestination() {
		assertEquals(town[2].toString(), road2.getDestination().toString());
		assertEquals(town[4].toString(), road3.getDestination().toString());
	}
	
	@Test
	void testGetSource(){
		assertEquals(town[1].toString(), road2.getSource().toString());
	}
	
	@Test
	void testCompareTo(){//
		assertEquals(-1, road2.compareTo(road1));
		assertEquals(0, road2.compareTo(road2));
	}
	
	@Test
	void testGetWeight() {
		assertEquals(4, road1.getWeight());
		assertEquals(5, road2.getWeight());
		assertEquals(6, road3.getWeight());
	}
	
	@Test
	void testEquals() {
		assertTrue(road1.equals(road1));
		assertFalse(road3.equals(road1));
	}
}
