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

	public final Integer[] integerArr;
	public final String string;

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
	 * @return
	 */
	public Integer[] getIntegerArr() {
		return integerArr;
	}

	/**
	 * Gibt den String des aktuellen Tuple Objektes zur�ck
	 * @return
	 */
	public String getString() {
		return string;
	}

}
