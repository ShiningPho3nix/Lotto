import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Steffen Dworsky
 *
 */
public class ProgramFlowTest {

	@Test
	public void testIstNumerisch() {
		String trueString = "12345 2 34 5 67";
		String falseString = "12345 2 34 5 67 a";

		assertTrue(ProgramFlow.istNumerisch(trueString));
		assertFalse(ProgramFlow.istNumerisch(falseString));
	}

}
