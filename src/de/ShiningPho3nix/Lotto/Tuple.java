package de.ShiningPho3nix.Lotto;

/**
 * Kleine Simple Tuple Klasse um die Rückgabe von erfrage Lottozahlen
 * zurückgeben zu können. ERmöglicht es einen String und ein INteger[]
 * abzuspeichern.
 * 
 * @author Steffen Dworsky
 *
 */
public class Tuple {

	private Integer[] integerArr;
	private String string;

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
	 * @return Das im Tuple enthaltene Integer[]
	 */
	public Integer[] getIntegerArr() {
		return integerArr;
	}

	/**
	 * Gibt den String des aktuellen Tuple Objektes zurück.
	 * 
	 * @return Den im Tupel enthaltenen String
	 */
	public String getString() {
		return string;
	}

	/**
	 * Setzt das Integer Array des aktuellen Objektes auf das übergebene Integer
	 * Array.
	 * 
	 * @param newIntegerArr
	 */
	public void SetIntegerArr(Integer[] newIntegerArr) {
		this.integerArr = newIntegerArr;
	}

	/**
	 * Setzt den String des aktuellen Objektes auf den übergebenen String.
	 * 
	 * @param newString
	 */
	public void setString(String newString) {
		this.string = newString;
	}

}
