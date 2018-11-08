import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

/**
 * @author Steffen Dworsky
 *
 */
public class BenutzereingabeTest {

	Benutzereingabe benutzereingabe;

	/**
	 * 
	 */
	public BenutzereingabeTest() {
		benutzereingabe = new Benutzereingabe();

	}

	@Test
	public void testErwarteBefehl() {
		Reader reader = new StringReader("test123");
		assertEquals("TEST123", benutzereingabe.erwarteBefehl(reader));
	}

	@Test
	public void testErfrageLottoZahlen() {
		Reader reader = new StringReader("2 3 4 5 a b");
		Integer[] ergebnis = new Integer[] { 2, 3, 4, 5 };
		Integer[] test = benutzereingabe.erfrageLottoZahlen(reader);
		assertArrayEquals(ergebnis, test);
	}

	@Test
	public void testNullEntfernen() {
		Integer[] ergebnis = new Integer[] { 2, 3, 4, 5 };
		Integer[] testInput = new Integer[] { null, 2, 3, 4, 5, null, null };
		Integer[] test = Benutzereingabe.nullEntfernen(testInput);
		assertArrayEquals(ergebnis, test);
	}

	@AfterAll
	public void quit() {
		Logging.quit();
	}
}
