package de.ShiningPho3nix.Lotto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Kleine Simple Tuple Klasse um die R�ckgabe von erfrage Lottozahlen
 * zur�ckgeben zu k�nnen. ERm�glicht es einen String und ein INteger[]
 * abzuspeichern.
 * 
 * @author Steffen Dworsky
 *
 */
public class Tuple {

	private final static Logger logger = LogManager.getLogger(Tuple.class);
	private Integer[] integerArr;
	private String string;

	/**
	 * Die Felder werden mit den �bergebenen Werten gef�llt.
	 */
	public Tuple(Integer[] arr, String str) {
		this.integerArr = arr;
		this.string = str;
		logger.info("Neuer Tupel wurde erzeugt mit Array: " + arr.toString() + " und String: " + str);

	}

	/**
	 * Gibt das Integer[] des aktuellen Tuple Objektes zur�ck.
	 * 
	 * @return Das im Tuple enthaltene Integer[]
	 */
	public Integer[] getIntegerArr() {
		logger.info("Array von Tuple: " + System.identityHashCode(this) + " zur�ckgegeben. Array: " + integerArr);
		return integerArr;
	}

	/**
	 * Gibt den String des aktuellen Tuple Objektes zur�ck.
	 * 
	 * @return Den im Tupel enthaltenen String
	 */
	public String getString() {
		logger.info("String von Tuple: " + System.identityHashCode(this) + " zur�ckgegeben. String: " + string);
		return string;
	}

	/**
	 * Setzt das Integer Array des aktuellen Objektes auf das �bergebene Integer
	 * Array.
	 * 
	 * @param newIntegerArr
	 */
	public void SetIntegerArr(Integer[] newIntegerArr) {
		logger.info("Neues Array festgelegt f�r Tuple: " + System.identityHashCode(this) + ". Array: " + newIntegerArr);
		this.integerArr = newIntegerArr;
	}

	/**
	 * Setzt den String des aktuellen Objektes auf den �bergebenen String.
	 * 
	 * @param newString
	 */
	public void setString(String newString) {
		logger.info("Neuer String festgelegt f�r Tuple: " + System.identityHashCode(this) + ". AString: " + newString);
		this.string = newString;
	}

}
