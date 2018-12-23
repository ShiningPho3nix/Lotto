import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Klasse zum testen der Methoden der ProgrammFlow Klasse
 * 
 * @author Steffen Dworsky
 *
 */
public class ProgramFlowTest {

	/**
	 * Test f�r die Methode istNumerisch. �bergibt einen String mit leerzeichen und
	 * nicht Nummerischen Zeichen und einen mit leerzeichen und nur nummerischen
	 * Zeichen.
	 */
	@Test
	public void testIstNumerisch() {
		String trueString = "12345 2 34 5 67"; // String welcher nur Zahlen enth�lt, Funktion sollte true zur�ckgeben.
		String falseString = "12345 2 34 5 67 a 98b"; // String welcher nicht nur Zahlen enth�lt, Funktion sollte false
														// zur�ckgeben.

		assertTrue(ProgramFlow.istNumerisch(trueString));
		assertFalse(ProgramFlow.istNumerisch(falseString));
	}

}
