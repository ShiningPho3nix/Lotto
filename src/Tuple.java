/**
 * Kleine Simple Tuple Klasse um die Rückgabe von erfrage Lottozahlen
 * zurückgeben zu können. ERmöglicht es einen String und ein INteger[]
 * abzuspeichern.
 * 
 * @author Steffen Dworsky
 *
 */
public class Tuple {

	public final Integer[] integerArr;
	public final String string;

	/**
	 * Die Felder werden mit den übergebenen Werten gefüllt.
	 */
	public Tuple(Integer[] arr, String str) {
		this.integerArr = arr;
		this.string = str;
	}

	/**
	 * Gibt das Integer[] des aktuellen Tuple Objektes zurück.
	 * 
	 * @return
	 */
	public Integer[] getIntegerArr() {
		return integerArr;
	}

	/**
	 * Gibt den String des aktuellen Tuple Objektes zurück
	 * @return
	 */
	public String getString() {
		return string;
	}

}
