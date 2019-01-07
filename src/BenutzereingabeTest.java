import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

/**
 * Test Klasse um einige Methoden der Benutzereingabe Klasse auf korrektheit zu
 * pr�fen.
 * 
 * @author Steffen Dworsky
 *
 */
public class BenutzereingabeTest {

	Benutzereingabe benutzereingabe;

	/**
	 * Der Konstruktor erzeugt das f�r die Tests n�tige Benutzereingabe Objekt.
	 */
	public BenutzereingabeTest() {
		benutzereingabe = new Benutzereingabe();

	}

	/**
	 * Testet ob die Methode ErwarteBefehl einen �bergebenen String korrekt
	 * bearbeitet.
	 */

	@Test
	public void testErwarteBefehl() {
		Reader reader = new StringReader("test123"); // StringReader mit einem Teststring wird erzeugt.
		assertEquals("TEST123", benutzereingabe.erwarteBefehl(reader)); // Pr�ft ob die Funktion erwarteBefehl den
																		// �bergebenen String korrekt bearbeitet.
	}

	/**
	 * Testet ob erfrage Lottozahlen nur G�ltige Werte zur�ckgibt und ung�ltige
	 * entfernt werden.
	 */
	@Test
	public void testErfrageLottoZahlen() {
		Reader reader = new StringReader("2 3 4 5 a b"); // StringReader mit g�ltigen und ung�ltigen Werten wird
															// erzeugt.
		Integer[] ergebnis = new Integer[] { 2, 3, 4, 5 }; // Aufgrund des oben erzeugten Strings wird ein Integer Array
															// ohne die ung�ltigen Daten erzeugt.
		Tuple rueckgabe = Benutzereingabe.erfrageLottoZahlen(reader);
		Integer[] test = rueckgabe.getIntegerArr();
		assertArrayEquals(ergebnis, test);
	}

	/**
	 * Testet ob die Funktion welche null aus Arrays entfernen soll korrekt
	 * Funktioniert.
	 */
	@Test
	public void testNullEntfernen() {
		Integer[] testInput = new Integer[] { null, 2, 3, 4, 5, null, null }; // Erzeugen eines Arrays mit Werten sowie
																				// null.
		Integer[] ergebnis = new Integer[] { 2, 3, 4, 5 }; // Selbe Daten wie in dem testInput Array, jedoch ohne null.

		Integer[] test = Benutzereingabe.nullEntfernen(testInput);
		assertArrayEquals(ergebnis, test);
	}
}