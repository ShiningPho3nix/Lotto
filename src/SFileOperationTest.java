
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Steffen Dworsky
 *
 */
public class SFileOperationTest {

	private ArrayList<Integer> testArray;
	private SFileOperation testObjekt;
	private ArrayList<Integer> ergebnisArray;

	public SFileOperationTest() {
		testObjekt = new SFileOperation();
	}

	@BeforeEach
	void init() {
		testArray = new ArrayList<>();
		ergebnisArray = new ArrayList<>();
		Integer[] testValues = new Integer[] { 6, 6, 70, 0, 5, 3, 50, 100, 51, 51, 1, 2, 3, 4, 5, 6 };
		testArray.addAll(Arrays.asList(testValues));
	}

	@Test
	void testCheckArray() {
		Integer[] ergebnis = new Integer[] { 6, 5, 3, 50, 1, 2 };
		ergebnisArray.addAll(Arrays.asList(ergebnis));
		ArrayList<Integer> localTestArray = testObjekt.checkArray(testArray);
		assertTrue(localTestArray.size() == 6);
		assertEquals(localTestArray, ergebnisArray);
	}

	@Test
	void testDoppelteZahlen() {
		Integer[] ergebnis = new Integer[] { 6, 70, 0, 5, 3, 50, 100, 51, 1, 2, 4 };
		ergebnisArray.addAll(Arrays.asList(ergebnis));
		ArrayList<Integer> localTestArray = testObjekt.doppelteZahlen(testArray);
		assertEquals(ergebnisArray, localTestArray);
	}

	@Test
	void testUngueltigeWerte() {
		Integer[] ergebnis = new Integer[] { 6, 6, 5, 3, 50, 1, 2, 3, 4, 5, 6 };
		ergebnisArray.addAll(Arrays.asList(ergebnis));
		ArrayList<Integer> localTestArray = testObjekt.ungueltigeWerte(testArray);
		assertEquals(ergebnisArray, localTestArray);
	}

	@Test
	void testMehrAlsSechsWerte() {
		Integer[] ergebnis = new Integer[] { 6, 6, 70, 0, 5, 3 };
		ergebnisArray.addAll(Arrays.asList(ergebnis));
		ArrayList<Integer> localTestArray = testObjekt.mehrAlsSechsWerte(testArray);
		assertAll("sizes", () -> assertTrue(ergebnisArray.size() == 6), () -> assertTrue(localTestArray.size() == 6));
		assertEquals(ergebnisArray, localTestArray);
	}

	@AfterAll
	public void quit() {
		Logging.quit();
	}
}
