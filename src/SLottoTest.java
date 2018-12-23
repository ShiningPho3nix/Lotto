import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Enth�lt diverse Tests zu Pr�fen der Methoden der SLotto Klasse.
 * 
 * @author Steffen Dworsky
 *
 */
public class SLottoTest {

	SechsAusNeunundvierzig sechsAusNeunundvierzig;
	Eurojackpot eurojackpot;
	ArrayList<Integer> copySechsAusNeunundvierzigArray;
	ArrayList<Integer> copyFuenfAusFuenfzigArray;
	ArrayList<Integer> copyZweiAusZehnArray;
	private Integer[] testWerte = new Integer[] { 0, 1, 1, 2, 3, 49, 49, 50, 51, 4, 5, 6, 7, 8, 9, 10 };

	public SLottoTest() {
		init();
	}

	/**
	 * Vor jedem Test werden die jeweiligen Objekte neu erzeugt, die Arrays und
	 * Listen neu Inizialisiert, sodas jeder Test die gleichen Startbedingungen hat.
	 */
	@BeforeEach
	public void init() {
		sechsAusNeunundvierzig = new SechsAusNeunundvierzig();
		eurojackpot = new Eurojackpot();

		sechsAusNeunundvierzig.unglueckszahlenArray = new ArrayList<>();
		sechsAusNeunundvierzig.tippzahlenArray = new ArrayList<>();
		sechsAusNeunundvierzig.erstelleCollection();

		eurojackpot.unglueckszahlenArray = new ArrayList<>();
		eurojackpot.tippzahlenArray = new ArrayList<>();
		eurojackpot.zweiAusZehntippzahlenArray = new ArrayList<>();
		eurojackpot.erstelleCollection();

		copySechsAusNeunundvierzigArray = new ArrayList<>();
		copySechsAusNeunundvierzigArray.addAll(sechsAusNeunundvierzig.tippzahlenArray);
		copyFuenfAusFuenfzigArray = new ArrayList<>();
		copyFuenfAusFuenfzigArray.addAll(eurojackpot.tippzahlenArray);
		copyZweiAusZehnArray = new ArrayList<>();
		copyZweiAusZehnArray.addAll(eurojackpot.zweiAusZehntippzahlenArray);

		assertAll("Array Gr��en", () -> assertEquals(49, copySechsAusNeunundvierzigArray.size()),
				() -> assertEquals(50, copyFuenfAusFuenfzigArray.size()),
				() -> assertEquals(10, copyZweiAusZehnArray.size()));
		// Pr�ft ob alle kopierten Listen die korrekte Gr��e haben.
	}

	/**
	 * Test um die Methode erstelleCollection auf korrektheit zu pr�fen.
	 */
	@Test
	public void testErstelleCollection() {

		ArrayList<Integer> ergebnisUnglueckszahlenArray = new ArrayList<>(Arrays.asList(1, 2, 3, 49, 50, 4));
		// Das zu erwartene Ergebniss.

		for (Integer integer : ergebnisUnglueckszahlenArray) { // Entfernt die ungl�ckszahlen f�r die Ergebniss Listen.
			if (copySechsAusNeunundvierzigArray.contains(integer)) {
				copySechsAusNeunundvierzigArray.remove((Integer) integer);
			}
			if (copyFuenfAusFuenfzigArray.contains(integer)) {
				copyFuenfAusFuenfzigArray.remove((Integer) integer);
			}
			if (copyZweiAusZehnArray.contains(integer)) {
				copyZweiAusZehnArray.remove((Integer) integer);
			}
		}

		eurojackpot.unglueckszahlenArray.addAll(ergebnisUnglueckszahlenArray); // F�llt die jeweiligen ungl�ckszahlen
																				// Arrays mit den ungl�ckszahlen
		sechsAusNeunundvierzig.unglueckszahlenArray.addAll(ergebnisUnglueckszahlenArray);

		sechsAusNeunundvierzig.tippzahlenArray = new ArrayList<>(); // Leert alle Listen, welche durch init eventuell
																	// bereits Werte enthalten.
		eurojackpot.tippzahlenArray = new ArrayList<>();
		eurojackpot.zweiAusZehntippzahlenArray = new ArrayList<>();

		eurojackpot.erstelleCollection(); // F�hrt die zu testenden Methoden aus
		sechsAusNeunundvierzig.erstelleCollection();

		assertAll("Listen vergleichen", // Vergleicht die Listen nach ausf�hren der Methoden mit den erwarteten
										// Ergebnissen.
				() -> assertEquals(sechsAusNeunundvierzig.tippzahlenArray, copySechsAusNeunundvierzigArray),
				() -> assertEquals(eurojackpot.tippzahlenArray, copyFuenfAusFuenfzigArray),
				() -> assertEquals(eurojackpot.zweiAusZehntippzahlenArray, copyZweiAusZehnArray));
		assertAll("Gr��e der jeweiligen ungl�ckszahlenArrays ist 6", // Pr�ft ob ungl�ckszahlenArray die korrekte gr��e
																		// besitzt.
				() -> assertTrue(sechsAusNeunundvierzig.unglueckszahlenArray.size() == 6),
				() -> assertTrue(eurojackpot.unglueckszahlenArray.size() == 6));
		assertAll("Ungl�ckszahlenArray ist wie erwartet", // Pr�ft ob ungl�ckszahlenArray wie zu erwarten sind.
				() -> assertEquals(ergebnisUnglueckszahlenArray, sechsAusNeunundvierzig.unglueckszahlenArray),
				() -> assertEquals(ergebnisUnglueckszahlenArray, eurojackpot.unglueckszahlenArray));
	}

	@Test
	public void testEntferneZahlen() {

		ArrayList<Integer> ergebnisUnglueckszahlenArray = new ArrayList<>(Arrays.asList(1, 2, 3, 49, 50, 4));

		for (Integer integer : ergebnisUnglueckszahlenArray) {
			if (copySechsAusNeunundvierzigArray.contains(integer)) {
				copySechsAusNeunundvierzigArray.remove((Integer) integer);
			}
			if (copyFuenfAusFuenfzigArray.contains(integer)) {
				copyFuenfAusFuenfzigArray.remove((Integer) integer);
			}
			if (copyZweiAusZehnArray.contains(integer)) {
				copyZweiAusZehnArray.remove((Integer) integer);
			}
		}

		sechsAusNeunundvierzig.entferneZahlen(testWerte);
		eurojackpot.entferneZahlen(testWerte);

		assertAll("Arrays vergleichen",
				() -> assertEquals(sechsAusNeunundvierzig.tippzahlenArray, copySechsAusNeunundvierzigArray),
				() -> assertEquals(eurojackpot.tippzahlenArray, copyFuenfAusFuenfzigArray),
				() -> assertEquals(eurojackpot.zweiAusZehntippzahlenArray, copyZweiAusZehnArray));
		assertAll("Gr��e nach entferneZahlen() ist kleiner",
				() -> assertFalse(sechsAusNeunundvierzig.tippzahlenArray.size() == 49),
				() -> assertFalse(eurojackpot.tippzahlenArray.size() == 50),
				() -> assertFalse(eurojackpot.zweiAusZehntippzahlenArray.size() == 10));
		assertAll("Gr��e nach entferneZahlen() ist korrekt",
				() -> assertTrue(sechsAusNeunundvierzig.tippzahlenArray.size() == 44),
				() -> assertTrue(eurojackpot.tippzahlenArray.size() == 44),
				() -> assertTrue(eurojackpot.zweiAusZehntippzahlenArray.size() == 6));
		assertAll("Gr��e der jeweiligen ungl�ckszahlenArrays ist 6",
				() -> assertTrue(sechsAusNeunundvierzig.unglueckszahlenArray.size() == 6),
				() -> assertTrue(eurojackpot.unglueckszahlenArray.size() == 6));
		assertAll("Ungl�ckszahlenArray ist wie erwartet",
				() -> assertEquals(ergebnisUnglueckszahlenArray, sechsAusNeunundvierzig.unglueckszahlenArray),
				() -> assertEquals(ergebnisUnglueckszahlenArray, eurojackpot.unglueckszahlenArray));
	}

	@Test
	public void testEntferneUnglueckszahlen() {
		ArrayList<Integer> startUnglueckszahlenArray = new ArrayList<>(Arrays.asList(1, 2, 3, 49, 50, 4));
		ArrayList<Integer> ergebnisUnglueckszahlenArray = new ArrayList<>();

		sechsAusNeunundvierzig.entferneZahlen(testWerte);
		eurojackpot.entferneZahlen(testWerte);

		assertAll("Ungl�ckszahlenArray ist wie startUnglueckszahlenArray",
				() -> assertEquals(startUnglueckszahlenArray, sechsAusNeunundvierzig.unglueckszahlenArray),
				() -> assertEquals(startUnglueckszahlenArray, eurojackpot.unglueckszahlenArray));

		sechsAusNeunundvierzig.entferneUnglueckszahl(testWerte);
		eurojackpot.entferneUnglueckszahl(testWerte);

		assertAll("Ungl�ckszahlenArray ist wie ergebnisszahlenArray",
				() -> assertEquals(ergebnisUnglueckszahlenArray, sechsAusNeunundvierzig.unglueckszahlenArray),
				() -> assertEquals(ergebnisUnglueckszahlenArray, eurojackpot.unglueckszahlenArray));
	}
}
