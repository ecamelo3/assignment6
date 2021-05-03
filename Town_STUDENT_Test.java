import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Town_STUDENT_Test {
	private Town town1, town2, town3, town4, town5, town6;

	@BeforeEach
	void setUp() throws Exception {
		town1 = new Town("a");
		town2 = new Town("b");
		town3 = new Town("c");
		town4 = new Town("d");
		town5 = new Town("e");
		town6 = new Town("f");
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetName() {
		assertEquals("a", town1.getName());
		assertEquals("c", town3.getName());
		assertEquals("e", town5.getName());
	}
	
	@Test
	void testCompareTo() {
		assertEquals(0, town2.compareTo(town2));
		assertEquals(-1, town4.compareTo(town5));
	}
	
	@Test
	void testToString() {
		assertEquals("b", town2.toString());
		assertEquals("d", town4.toString());
		assertEquals("f", town6.toString());
	}
	
	@Test
	void testHashCode() {
		assertEquals(102, town6.hashCode());
	}
	
	@Test
	void equals() {
		assertTrue(town2.equals(town2));
	}

}
