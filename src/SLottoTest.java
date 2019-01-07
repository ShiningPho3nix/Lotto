import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Enthält diverse Tests zu Prüfen der Methoden der SLotto Klasse.
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
		sechsAusNeunundvierzig.zweiAusZehnTippzahlenArray = new ArrayList<>();
		sechsAusNeunundvierzig.erstelleCollection();

		eurojackpot.unglueckszahlenArray = new ArrayList<>();
		eurojackpot.tippzahlenArray = new ArrayList<>();
		eurojackpot.zweiAusZehnTippzahlenArray = new ArrayList<>();
		eurojackpot.erstelleCollection();

		copySechsAusNeunundvierzigArray = new ArrayList<>();
		copySechsAusNeunundvierzigArray.addAll(sechsAusNeunundvierzig.tippzahlenArray);
		copyFuenfAusFuenfzigArray = new ArrayList<>();
		copyFuenfAusFuenfzigArray.addAll(eurojackpot.tippzahlenArray);
		copyZweiAusZehnArray = new ArrayList<>();
		copyZweiAusZehnArray.addAll(eurojackpot.zweiAusZehnTippzahlenArray);

		assertAll("Array Größen", () -> assertEquals(49, copySechsAusNeunundvierzigArray.size()),
				() -> assertEquals(50, copyFuenfAusFuenfzigArray.size()),
				() -> assertEquals(10, copyZweiAusZehnArray.size()));
		// Prüft ob alle kopierten Listen die korrekte Größe haben.
	}

	/**
	 * Test um die Methode erstelleCollection auf korrektheit zu prüfen.
	 */
	@Test
	public void testErstelleCollection() {

		ArrayList<Integer> ergebnisUnglueckszahlenArray = new ArrayList<>(Arrays.asList(1, 2, 3, 49, 50, 4));
		// Das zu erwartene Ergebniss.

		for (Integer integer : ergebnisUnglueckszahlenArray) { // Entfernt die unglückszahlen für die Ergebniss Listen.
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

		eurojackpot.unglueckszahlenArray.addAll(ergebnisUnglueckszahlenArray); // Füllt die jeweiligen unglückszahlen
																				// Arrays mit den unglückszahlen
		sechsAusNeunundvierzig.unglueckszahlenArray.addAll(ergebnisUnglueckszahlenArray);

		sechsAusNeunundvierzig.tippzahlenArray = new ArrayList<>(); // Leert alle Listen, welche durch init eventuell
																	// bereits Werte enthalten.
		eurojackpot.tippzahlenArray = new ArrayList<>();
		eurojackpot.zweiAusZehnTippzahlenArray = new ArrayList<>();

		eurojackpot.erstelleCollection(); // Führt die zu testenden Methoden aus
		sechsAusNeunundvierzig.erstelleCollection();

		assertAll("Listen vergleichen", // Vergleicht die Listen nach ausführen der Methoden mit den erwarteten
										// Ergebnissen.
				() -> assertEquals(sechsAusNeunundvierzig.tippzahlenArray, copySechsAusNeunundvierzigArray),
				() -> assertEquals(eurojackpot.tippzahlenArray, copyFuenfAusFuenfzigArray),
				() -> assertEquals(eurojackpot.zweiAusZehnTippzahlenArray, copyZweiAusZehnArray));
		assertAll("Größe der jeweiligen unglückszahlenArrays ist 6", // Prüft ob unglückszahlenArray die korrekte größe
																		// besitzt.
				() -> assertTrue(sechsAusNeunundvierzig.unglueckszahlenArray.size() == 6),
				() -> assertTrue(eurojackpot.unglueckszahlenArray.size() == 6));
		assertAll("UnglückszahlenArray ist wie erwartet", // Prüft ob unglückszahlenArray wie zu erwarten sind.
				() -> assertEquals(ergebnisUnglueckszahlenArray, sechsAusNeunundvierzig.unglueckszahlenArray),
				() -> assertEquals(ergebnisUnglueckszahlenArray, eurojackpot.unglueckszahlenArray));
	}

	@Test
	public void testNeueUnglueckszahl() {

		ArrayList<Integer> ergebnisUnglueckszahlenFuenfAusFuenfzigArray = new ArrayList<>(
				Arrays.asList(1, 2, 3, 49, 50, 4));
		ArrayList<Integer> ergebnisUnglueckszahlenSechsAusNeunundvierzigArray = new ArrayList<>(
				Arrays.asList(1, 2, 3, 49, 4, 5));

		for (Integer integer : ergebnisUnglueckszahlenFuenfAusFuenfzigArray) {
			if (copyFuenfAusFuenfzigArray.contains(integer)) {
				copyFuenfAusFuenfzigArray.remove((Integer) integer);
			}
			if (copyZweiAusZehnArray.contains(integer)) {
				copyZweiAusZehnArray.remove((Integer) integer);
			}
		}
		for (Integer integer : ergebnisUnglueckszahlenSechsAusNeunundvierzigArray) {
			if (copySechsAusNeunundvierzigArray.contains(integer)) {
				copySechsAusNeunundvierzigArray.remove((Integer) integer);
			}
		}

		sechsAusNeunundvierzig.neueUnglueckszahlAusschliessen(testWerte);
		eurojackpot.neueUnglueckszahlAusschliessen(testWerte);

		assertAll("Arrays vergleichen",
				() -> assertEquals(sechsAusNeunundvierzig.tippzahlenArray, copySechsAusNeunundvierzigArray),
				() -> assertEquals(eurojackpot.tippzahlenArray, copyFuenfAusFuenfzigArray),
				() -> assertEquals(eurojackpot.zweiAusZehnTippzahlenArray, copyZweiAusZehnArray));
		assertAll("Größe nach entferneZahlen() ist kleiner",
				() -> assertFalse(sechsAusNeunundvierzig.tippzahlenArray.size() == 49),
				() -> assertFalse(eurojackpot.tippzahlenArray.size() == 50),
				() -> assertFalse(eurojackpot.zweiAusZehnTippzahlenArray.size() == 10));
		assertAll("Größe nach entferneZahlen() ist korrekt",
				() -> assertTrue(sechsAusNeunundvierzig.tippzahlenArray.size() == 43),
				() -> assertTrue(eurojackpot.tippzahlenArray.size() == 44),
				() -> assertTrue(eurojackpot.zweiAusZehnTippzahlenArray.size() == 6));
		assertAll("Größe der jeweiligen unglückszahlenArrays ist 6",
				() -> assertTrue(sechsAusNeunundvierzig.unglueckszahlenArray.size() == 6),
				() -> assertTrue(eurojackpot.unglueckszahlenArray.size() == 6));
		assertAll("UnglückszahlenArray ist wie erwartet",
				() -> assertEquals(ergebnisUnglueckszahlenSechsAusNeunundvierzigArray,
						sechsAusNeunundvierzig.unglueckszahlenArray),
				() -> assertEquals(ergebnisUnglueckszahlenFuenfAusFuenfzigArray, eurojackpot.unglueckszahlenArray));
	}

	@Test
	public void testUnglueckszahlWiederZulassen() {
		ArrayList<Integer> startUnglueckszahlenSechsAusNeunundvierzigArray = new ArrayList<>(
				Arrays.asList(1, 2, 3, 49, 4, 5));
		ArrayList<Integer> startUnglueckszahlenEurojackpotArray = new ArrayList<>(Arrays.asList(1, 2, 3, 49, 50, 4));
		ArrayList<Integer> ergebnisUnglueckszahlenArray = new ArrayList<>();

		sechsAusNeunundvierzig.neueUnglueckszahlAusschliessen(testWerte);
		eurojackpot.neueUnglueckszahlAusschliessen(testWerte);

		assertAll("UnglückszahlenArray ist wie startUnglueckszahlenArray",
				() -> assertEquals(startUnglueckszahlenSechsAusNeunundvierzigArray,
						sechsAusNeunundvierzig.unglueckszahlenArray),
				() -> assertEquals(startUnglueckszahlenEurojackpotArray, eurojackpot.unglueckszahlenArray));

		sechsAusNeunundvierzig.unglueckszahlWiederZulassen(testWerte);
		eurojackpot.unglueckszahlWiederZulassen(testWerte);

		assertAll("UnglückszahlenArray ist wie ergebnisszahlenArray",
				() -> assertEquals(ergebnisUnglueckszahlenArray, sechsAusNeunundvierzig.unglueckszahlenArray),
				() -> assertEquals(ergebnisUnglueckszahlenArray, eurojackpot.unglueckszahlenArray));
	}
}
