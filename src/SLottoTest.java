import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

/**
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

		assertAll("Array Größen", () -> assertEquals(49, copySechsAusNeunundvierzigArray.size()),
				() -> assertEquals(50, copyFuenfAusFuenfzigArray.size()),
				() -> assertEquals(10, copyZweiAusZehnArray.size()));
	}

	@Test
	public void testErstelleCollection() {

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

		eurojackpot.unglueckszahlenArray.addAll(ergebnisUnglueckszahlenArray);
		sechsAusNeunundvierzig.unglueckszahlenArray.addAll(ergebnisUnglueckszahlenArray);

		sechsAusNeunundvierzig.tippzahlenArray = new ArrayList<>();
		eurojackpot.tippzahlenArray = new ArrayList<>();
		eurojackpot.zweiAusZehntippzahlenArray = new ArrayList<>();

		eurojackpot.erstelleCollection();
		sechsAusNeunundvierzig.erstelleCollection();

		assertAll("Arrays vergleichen",
				() -> assertEquals(sechsAusNeunundvierzig.tippzahlenArray, copySechsAusNeunundvierzigArray),
				() -> assertEquals(eurojackpot.tippzahlenArray, copyFuenfAusFuenfzigArray),
				() -> assertEquals(eurojackpot.zweiAusZehntippzahlenArray, copyZweiAusZehnArray));
		assertAll("Größe der jeweiligen unglückszahlenArrays ist 6",
				() -> assertTrue(sechsAusNeunundvierzig.unglueckszahlenArray.size() == 6),
				() -> assertTrue(eurojackpot.unglueckszahlenArray.size() == 6));
		assertAll("UnglückszahlenArray ist wie erwartet",
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
		assertAll("Größe nach entferneZahlen() ist kleiner",
				() -> assertFalse(sechsAusNeunundvierzig.tippzahlenArray.size() == 49),
				() -> assertFalse(eurojackpot.tippzahlenArray.size() == 50),
				() -> assertFalse(eurojackpot.zweiAusZehntippzahlenArray.size() == 10));
		assertAll("Größe nach entferneZahlen() ist korrekt",
				() -> assertTrue(sechsAusNeunundvierzig.tippzahlenArray.size() == 44),
				() -> assertTrue(eurojackpot.tippzahlenArray.size() == 44),
				() -> assertTrue(eurojackpot.zweiAusZehntippzahlenArray.size() == 6));
		assertAll("Größe der jeweiligen unglückszahlenArrays ist 6",
				() -> assertTrue(sechsAusNeunundvierzig.unglueckszahlenArray.size() == 6),
				() -> assertTrue(eurojackpot.unglueckszahlenArray.size() == 6));
		assertAll("UnglückszahlenArray ist wie erwartet",
				() -> assertEquals(ergebnisUnglueckszahlenArray, sechsAusNeunundvierzig.unglueckszahlenArray),
				() -> assertEquals(ergebnisUnglueckszahlenArray, eurojackpot.unglueckszahlenArray));
	}

	@Test
	public void testEntferneUnglueckszahlen() {
		ArrayList<Integer> startUnglueckszahlenArray = new ArrayList<>(Arrays.asList(1, 2, 3, 49, 50, 4));
		ArrayList<Integer> ergebnisUnglueckszahlenArray = new ArrayList<>();

		sechsAusNeunundvierzig.entferneZahlen(testWerte);
		eurojackpot.entferneZahlen(testWerte);

		assertAll("UnglückszahlenArray ist wie startUnglueckszahlenArray",
				() -> assertEquals(startUnglueckszahlenArray, sechsAusNeunundvierzig.unglueckszahlenArray),
				() -> assertEquals(startUnglueckszahlenArray, eurojackpot.unglueckszahlenArray));

		sechsAusNeunundvierzig.entferneUnglueckszahl(testWerte);
		eurojackpot.entferneUnglueckszahl(testWerte);

		assertAll("UnglückszahlenArray ist wie ergebnisszahlenArray",
				() -> assertEquals(ergebnisUnglueckszahlenArray, sechsAusNeunundvierzig.unglueckszahlenArray),
				() -> assertEquals(ergebnisUnglueckszahlenArray, eurojackpot.unglueckszahlenArray));
	}
}
