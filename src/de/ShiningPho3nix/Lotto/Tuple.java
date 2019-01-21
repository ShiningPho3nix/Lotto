package de.ShiningPho3nix.Lotto;

/**
 * Kleine Simple Tuple Klasse um die R�ckgabe von erfrage Lottozahlen
 * zur�ckgeben zu k�nnen. ERm�glicht es einen String und ein INteger[]
 * abzuspeichern.
 * 
 * @author Steffen Dworsky
 *
 */
public class Tuple {

	private Integer[] integerArr;
	private String string;

	/**
	 * Die Felder werden mit den �bergebenen Werten gef�llt.
	 */
	public Tuple(Integer[] arr, String str) {
		this.integerArr = arr;
		this.string = str;
	}

	/**
	 * Gibt das Integer[] des aktuellen Tuple Objektes zur�ck.
	 * 
	 * @return Das im Tuple enthaltene Integer[]
	 */
	public Integer[] getIntegerArr() {
		return integerArr;
	}

	/**
	 * Gibt den String des aktuellen Tuple Objektes zur�ck.
	 * 
	 * @return Den im Tupel enthaltenen String
	 */
	public String getString() {
		return string;
	}

	/**
	 * Setzt das Integer Array des aktuellen Objektes auf das �bergebene Integer
	 * Array.
	 * 
	 * @param newIntegerArr
	 */
	public void SetIntegerArr(Integer[] newIntegerArr) {
		this.integerArr = newIntegerArr;
	}

	/**
	 * Setzt den String des aktuellen Objektes auf den �bergebenen String.
	 * 
	 * @param newString
	 */
	public void setString(String newString) {
		this.string = newString;
	}

}
