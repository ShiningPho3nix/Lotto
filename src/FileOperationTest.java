import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Klasse zum Testen diverser Methoden der FileOpperation Klasse.
 * 
 * @author Steffen Dworsky
 *
 */
public class FileOperationTest {

	private ArrayList<Integer> testArray;
	private ArrayList<Integer> ergebnisArray;

	/**
	 * Vor jedem Test werden die Arrays und Testvalues auf einen Standardwert
	 * gesetzt.
	 */
	@BeforeEach
	void init() {
		testArray = new ArrayList<>();
		ergebnisArray = new ArrayList<>();
		Integer[] testValues = new Integer[] { 6, 6, 70, 0, 5, 3, 50, 100, 51, 51, 1, 2, 3, 4, 5, 6 };
		testArray.addAll(Arrays.asList(testValues));
	}

	/**
	 * Ein Test welcher die Methode checkArray prüft. Es wird das Array testValues
	 * übergeben.
	 */
	@Test
	void testCheckArray() {
		Integer[] ergebnis = new Integer[] { 6, 5, 3, 50, 1, 2 }; // Das zu erwartene Ergebniss, als Array um es im
		ergebnisArray.addAll(Arrays.asList(ergebnis)); // nächsten Schritt zur Liste hinzuzufügen.
		ArrayList<Integer> localTestArray = FileOperation.checkArray(testArray);
		assertTrue(localTestArray.size() == 6);
		assertEquals(localTestArray, ergebnisArray);
	}

	/**
	 * Test um die Methode zu Prüfen, welche doppelte Zahlen aus einem Array
	 * entfernen soll.
	 */
	@Test
	void testDoppelteZahlen() {
		Integer[] ergebnis = new Integer[] { 6, 70, 0, 5, 3, 50, 100, 51, 1, 2, 4 }; // Das zu erwartene Ergebniss
		ergebnisArray.addAll(Arrays.asList(ergebnis));
		ArrayList<Integer> localTestArray = FileOperation.doppelteZahlen(testArray);
		assertEquals(ergebnisArray, localTestArray);
	}

	/**
	 * Testet die Methode ungültigeWerte, welche Zahlen aus einem Array entfernen
	 * soll, die außerhalb des gültigen bereiches liegen.
	 */
	@Test
	void testUngueltigeWerte() {
		Integer[] ergebnis = new Integer[] { 6, 6, 5, 3, 50, 1, 2, 3, 4, 5, 6 }; // Das zu erwartene Ergebniss
		ergebnisArray.addAll(Arrays.asList(ergebnis));
		ArrayList<Integer> localTestArray = FileOperation.ungueltigeWerte(testArray);
		assertEquals(ergebnisArray, localTestArray);
	}

	/**
	 * Testet die Methode welche eine Array auf 6 Zahlen begrenzt.
	 */
	@Test
	void testMehrAlsSechsWerte() {
		Integer[] ergebnis = new Integer[] { 6, 6, 70, 0, 5, 3 }; // Das zu erwartene Ergebniss
		ergebnisArray.addAll(Arrays.asList(ergebnis));
		ArrayList<Integer> localTestArray = FileOperation.mehrAlsSechsWerte(testArray);
		assertAll("sizes", () -> assertTrue(ergebnisArray.size() == 6), () -> assertTrue(localTestArray.size() == 6));
		assertEquals(ergebnisArray, localTestArray);
	}

	/**
	 * Beendet das logging für diese Testklasse.
	 */
	@AfterAll
	public void quit() {
		Logging.quit();
	}
}
