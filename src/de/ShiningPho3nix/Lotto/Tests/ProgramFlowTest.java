package de.ShiningPho3nix.Lotto.Tests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.ShiningPho3nix.Lotto.ProgramFlow;

/**
 * Klasse zum testen der Methoden der ProgrammFlow Klasse
 * 
 * @author Steffen Dworsky
 *
 */
public class ProgramFlowTest {

	/**
	 * Test für die Methode istNumerisch. Übergibt einen String mit leerzeichen und
	 * nicht Nummerischen Zeichen und einen mit leerzeichen und nur nummerischen
	 * Zeichen.
	 */
	@Test
	public void testIstNumerisch() {
		String trueString = "12345 2 34 5 67"; // String welcher nur Zahlen enthält, Funktion sollte true zurückgeben.
		String falseString = "12345 2 34 5 67 a 98b"; // String welcher nicht nur Zahlen enthält, Funktion sollte false
														// zurückgeben.

		assertTrue(ProgramFlow.istNumerisch(trueString));
		assertFalse(ProgramFlow.istNumerisch(falseString));
	}

}
