package de.ShiningPho3nix.Lotto.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;

import javax.swing.text.NumberFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.ShiningPho3nix.Lotto.Benutzereingabe;
import de.ShiningPho3nix.Lotto.FileOperation;
import de.ShiningPho3nix.Lotto.StringSammlung;
import de.ShiningPho3nix.Lotto.TippGenerator;
import de.ShiningPho3nix.Lotto.Tuple;

import javax.swing.JRadioButton;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.text.NumberFormat;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JFormattedTextField;
import java.awt.TextArea;

public class LottoTippGeneratorGUI {

	private ApplicationContext context;
	private JFrame frmLottoTippgenerator;
	private TippGenerator tippGenerator;
	private final static Logger logger = LogManager.getLogger(LottoTippGeneratorGUI.class);
	private int anzahlTipps = 1;
	private final TextArea textArea = new TextArea();
	Action generierenAction;
	Action unglueckszahlenAction;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// new Logging();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LottoTippGeneratorGUI window = new LottoTippGeneratorGUI();
					window.frmLottoTippgenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Im Konstruktor werden die Unglückszahlen aus der Datei geladen, TippGenerator
	 * wird mit SechsAusNeunundvierzig inizialisiert und initialize wird ausgeführt.
	 */
	public LottoTippGeneratorGUI() {
		context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

		tippGenerator = (TippGenerator) context.getBean("tippGenSechs");
		String stringZahlen = makeStringFromArrList(FileOperation.laden());
		initialize(stringZahlen);
		textArea.append(StringSammlung.begruessungGUI());
		logger.info("Konstruktor durchgelaufen, GUI gestartet.");

	}

	private String makeStringFromArrList(ArrayList<Integer> arrayList) {
		if (arrayList.isEmpty()) {
			logger.info("Keine Unglückszahlen in der ArrayList.");
			return "Keine Unglückszahlen festgelegt.";
		}
		Collections.sort(arrayList, Collections.reverseOrder().reversed());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arrayList.size(); i++) {
			sb.append(arrayList.get(i));
			sb.append(", ");
		}
		String stringZahlen = sb.toString();
		stringZahlen = stringZahlen.substring(0, stringZahlen.length() - 2);
		logger.info("Es wurde der String: " + stringZahlen + " aus dem Array der gesperrten Zahlen generiert.");
		return stringZahlen;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String stringZahlen) {
		frmLottoTippgenerator = new JFrame();
		frmLottoTippgenerator.setSize(800, 400);
		frmLottoTippgenerator.setTitle("Lotto Tipp-Generator");
		frmLottoTippgenerator.getContentPane().setBackground(Color.GRAY);
		frmLottoTippgenerator.getContentPane().setLayout(null);
		frmLottoTippgenerator.setResizable(false);
		frmLottoTippgenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ButtonGroup spielmodusRadio = new ButtonGroup();

		/**
		 * Oberes Textfeld in welchem die Ausgeschlossenen Zahlen dem Nutzer gezeigt
		 * werden.
		 */
		JTextArea txtrAktuellAusgeschlosseneZahlen = new JTextArea();
		txtrAktuellAusgeschlosseneZahlen
				.setToolTipText("Die hier aufgelisteten Zahlen sind aktuell von der Tippgenerierung ausgeschlossen.");
		txtrAktuellAusgeschlosseneZahlen.setForeground(new Color(204, 0, 0));
		txtrAktuellAusgeschlosseneZahlen.setBackground(new Color(255, 255, 255));
		txtrAktuellAusgeschlosseneZahlen.setEditable(false);
		txtrAktuellAusgeschlosseneZahlen.setBounds(0, 0, 695, 25);
		txtrAktuellAusgeschlosseneZahlen.setText(" Aktuell ausgeschlossene Ungl\u00FCckszahlen: " + stringZahlen);
		frmLottoTippgenerator.getContentPane().add(txtrAktuellAusgeschlosseneZahlen);
		logger.info("TextArea mit Unglückszahlen durchgelaufen Unglückszahlen: " + stringZahlen);

		/**
		 * Reset Button + Option Pane zur Bestätigung
		 */
		JButton btnResetButton = new JButton("Reset");
		btnResetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.info("Reset Button gedrückt.");
				int n = JOptionPane.showOptionDialog(new JFrame(),
						"Durch einen Reset werden die aktuell gesperrten Zahlen wieder erlaubt! Bitte bestätigen.",
						"Reset bitte bestätigen", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);

				if (n == JOptionPane.YES_OPTION) {
					tippGenerator.reset();
					String stringZahlen = makeStringFromArrList(FileOperation.laden());
					textArea.append("Unglückszahlen wurden zurückgesetzt!");
					txtrAktuellAusgeschlosseneZahlen.setText("Aktuell ausgeschlossene Zahlen: " + stringZahlen);
					logger.info("Reset Option YES gewählt, Unglückszahlen wurden resettet");
				} else if (n == JOptionPane.NO_OPTION) {
					textArea.append("Zurücksetzen der Unglückszahlen abgebrochen!");
					logger.info("Reset Option NO gewählt, Unglückszahlen wurden nicht resettet");
				} else if (n == JOptionPane.CLOSED_OPTION) {
					logger.info("Reset OptionPane geschlossen, Unglückszahlen wurden nicht resettet");
				}
			}
		});
		btnResetButton.setBounds(695, 0, 100, 25);
		frmLottoTippgenerator.getContentPane().add(btnResetButton);
		logger.info("Reset Button durchgelaufen.");

		/**
		 * Panel in welchem die Optionen für den Generator zu finden sind (Modus,
		 * Textfeld zur Anzahl an Tipps, welche Generiert werden sollen).
		 */
		JPanel panelGeneratorOprionen = new JPanel();
		panelGeneratorOprionen.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Generator Optionen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelGeneratorOprionen.setBounds(0, 25, 270, 80);
		frmLottoTippgenerator.getContentPane().add(panelGeneratorOprionen);
		panelGeneratorOprionen.setLayout(null);
		logger.info("Generator Optionen Panel durchgelaufen.");

		/**
		 * Einer der beiden Radio Buttons zur Modus Auswahl 6aus49
		 */
		JRadioButton rdbtnaus = new JRadioButton("6aus49");
		rdbtnaus.setBounds(5, 15, 110, 30);
		panelGeneratorOprionen.add(rdbtnaus);
		rdbtnaus.setToolTipText("Setzt den gewählten Spielmodus auf 6aus49");
		rdbtnaus.setSelected(true);
		spielmodusRadio.add(rdbtnaus);
		logger.info("6aus49 Radio Button durchgelaufen.");
		rdbtnaus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tippGenerator = (TippGenerator) context.getBean("tippGenSechs");
				logger.info("Radio Button auf 6aus49 gesetzt.");
			}
		});

		/**
		 * Einer der beiden Radio Buttons zur Modus Auswahl Eurojackpot
		 */
		JRadioButton rdbtnEurojackpot = new JRadioButton("Eurojackpot");
		rdbtnEurojackpot.setBounds(5, 45, 110, 30);
		panelGeneratorOprionen.add(rdbtnEurojackpot);
		rdbtnEurojackpot.setToolTipText("Setzt den gewählten Spielmodus auf Eurojackpot");
		spielmodusRadio.add(rdbtnEurojackpot);
		logger.info("Eurojackpot Radio Button durchgelaufen.");
		rdbtnEurojackpot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tippGenerator = (TippGenerator) context.getBean("tippGenEuro");
				logger.info("Radio Button auf Eurojackpot gesetzt.");
			}
		});

		/**
		 * Formatted Text Feld zur Eingabe der Anzahl an Tipps welche generiert werden
		 * sollen. Nimmt nur Zahlen zwischen 0 und MAX_VALUE an
		 */
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(false);
		// If you want the value to be committed on each keystroke instead of focus lost
		formatter.setCommitsOnValidEdit(true);
		JFormattedTextField formattedTextFieldAnzahlTipps = new JFormattedTextField(formatter);
		formattedTextFieldAnzahlTipps.setBounds(121, 35, 60, 20);
		formattedTextFieldAnzahlTipps.addActionListener(generierenAction); // TODO Funktioniert noch nicht wie gewollt,
																			// nichts passiert.
		panelGeneratorOprionen.add(formattedTextFieldAnzahlTipps);
		logger.info("FormattetTextField um die Anzahl an zu generierenden Tipps einzugeben durchgelaufen.");

		/**
		 * Label welches unter dem Textfeld zur Tippanzahl-Eingabe erscheint, wenn eine
		 * ungültige eingabe getätigt wurde (>1.000).
		 */
		JLabel lblNewLabelUngueltig = new JLabel("Ungültige Eingabe.");
		lblNewLabelUngueltig.setBounds(121, 55, 139, 20);
		panelGeneratorOprionen.add(lblNewLabelUngueltig);
		lblNewLabelUngueltig.setVisible(false);
		lblNewLabelUngueltig.setForeground(Color.RED);
		lblNewLabelUngueltig.setToolTipText("Nur Zahlen zwischen 0 und 1.000 sind erlaubt!");
		logger.info("Label für ungültige Eingabe durchgelaufen.");

		/**
		 * Panel oben Rechts. Für auschließen/wieder erlauben von zahlen zuständig
		 */
		JPanel panelObenRechts = new JPanel();
		panelObenRechts.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Unglückszahlen bearbeiten", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelObenRechts.setBounds(270, 25, 524, 80);
		frmLottoTippgenerator.getContentPane().add(panelObenRechts);
		panelObenRechts.setLayout(null);
		logger.info("Unglückszahlen bearbeiten Panel durchgelaufen.");

		/**
		 * Die Text Felder zum Eingeben der Unglückszahlen
		 */
		JTextField unglueckszahlenAusschliessenField = new JTextField();
		unglueckszahlenAusschliessenField.setToolTipText(
				"Eine oder mehrere Zahlen durch ein Leerzeichen getrennt eingeben. Die eingegebenen Zahlen werden von der Tippgenerierung ausgeschlossen.\n"
						+ " Ung\u00FCltige Zahlen und Zeichen werden Ignoriert. Es k\u00F6nnen Maximal 6 Zahlen von der Tippgenerierung ausgeschlossen sein.");
		unglueckszahlenAusschliessenField.setBounds(200, 20, 75, 20);
		unglueckszahlenAusschliessenField.addActionListener(unglueckszahlenAction);
		panelObenRechts.add(unglueckszahlenAusschliessenField);
		logger.info("Unglückszahlen ausschließen Textfield durchgelaufen.");

		JTextField unglueckszahlenZulassenField = new JTextField();
		unglueckszahlenZulassenField.setToolTipText(
				"Eine oder mehrere Zahlen durch ein Leerzeichen getrennt eingeben. Die eingegebenen Zahlen werden zur Tippgenerierung wieder zugelassen.\n"
						+ " Ung\u00FCltige Zahlen und Zeichen werden Ignoriert. Es k\u00F6nnen nur Zahlen verwendet werden, welche aktuell unter den ausgeschlossenen"
						+ " Zahlen sind.");
		unglueckszahlenZulassenField.setBounds(200, 50, 75, 20);
		unglueckszahlenZulassenField.addActionListener(unglueckszahlenAction);
		panelObenRechts.add(unglueckszahlenZulassenField);
		unglueckszahlenZulassenField.setColumns(10);
		logger.info("Unglückszahlen zulassen Textfield durchgelaufen.");

		/**
		 * Die Labels für die Unglückszahlen Felder.
		 */
		JLabel lblZahlenFestlegenLabel = new JLabel("Zahl(en) festlegen");
		lblZahlenFestlegenLabel.setBounds(10, 20, 180, 15);
		panelObenRechts.add(lblZahlenFestlegenLabel);
		logger.info("Unglückszahlen festlegen Label durchgelaufen.");

		JLabel lblZahlenLoeschenLabel = new JLabel("Zahl(en) l\u00F6schen");
		lblZahlenLoeschenLabel.setBounds(10, 50, 180, 15);
		panelObenRechts.add(lblZahlenLoeschenLabel);
		logger.info("Unglückszahlen löschen Label durchgelaufen.");

		/**
		 * Der Bestätigen Button für die Unglückszahlen
		 */
		JButton btnBestaetigenButton = new JButton("Best\u00E4tigen");
		btnBestaetigenButton.setToolTipText(
				"Zun\u00E4cht werden die Zahlen, wenn vorhanden, aus dem \"festlegen\" Feld behandelt. Danach werden die Zahlen, wenn vorhanden, aus dem \"l\u00F6schen\" Feld behandelt.");
		btnBestaetigenButton.setBounds(415, 45, 100, 25);
		panelObenRechts.add(btnBestaetigenButton);
		logger.info("Bestätigen Button durchgelaufen.");

		/**
		 * Die Action für das Bestätigen der Unglückszahlen.
		 */
		unglueckszahlenAction = new AbstractAction() {

			private static final long serialVersionUID = 7649126247316473524L;

			@Override
			public void actionPerformed(ActionEvent e) {
				StringReader unglueckszahlenAusschliessen = new StringReader(
						unglueckszahlenAusschliessenField.getText().toString());
				StringReader unglueckszahlenZulassen = new StringReader(
						unglueckszahlenZulassenField.getText().toString());

				Tuple rueckgabeAusschliessen = Benutzereingabe.verarbeiteZahlen(unglueckszahlenAusschliessen);
				Tuple rueckgabeZulassen = Benutzereingabe.verarbeiteZahlen(unglueckszahlenZulassen);

				textArea.append(rueckgabeAusschliessen.getString()); // Ungültig eingegebene Zeichen
				textArea.append(tippGenerator.neueUnglueckszahlAusschliessen(rueckgabeAusschliessen.getIntegerArr()));

				textArea.append(rueckgabeZulassen.getString()); // Ungültig eingegebene Zeichen
				textArea.append(tippGenerator.unglueckszahlWiederZulassen(rueckgabeZulassen.getIntegerArr()));

				String stringZahlen = makeStringFromArrList(FileOperation.laden());
				txtrAktuellAusgeschlosseneZahlen.setText("Aktuell ausgeschlossene Unglückszahlen: " + stringZahlen);

				unglueckszahlenAusschliessenField.setText("");
				unglueckszahlenZulassenField.setText("");
				logger.info("Bestätigen Button gedrückt.");
			};
		};
		btnBestaetigenButton.addActionListener(unglueckszahlenAction);

		/**
		 * Panel für die Ausgabe von Informationen an den Nutzer. Enthält ein TextField
		 * sowie die Buttons zum generieren und beenden.
		 */
		JPanel panelTextFieldUnten = new JPanel();
		panelTextFieldUnten.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Informationen & Ausgabe", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelTextFieldUnten.setBounds(0, 104, 794, 267);
		frmLottoTippgenerator.getContentPane().add(panelTextFieldUnten);
		panelTextFieldUnten.setLayout(null);
		logger.info("Information und Ausgabe Panel durchgelaufen.");

		/**
		 * TextField zur Ausgabe von Informationen an den Nutzer
		 */
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);

		textArea.setBounds(10, 20, 775, 205);
		panelTextFieldUnten.add(textArea);
		logger.info("Ausgabe TextArea durchgelaufen");

		/**
		 * Generieren Button
		 */
		JButton btnGenerierenButton = new JButton("Generieren");
		btnGenerierenButton.setBounds(10, 230, 100, 25);
		panelTextFieldUnten.add(btnGenerierenButton);
		logger.info("Generieren Button durchgelaufen.");

		/**
		 * Die Action zum Generieren von Tipps
		 */
		generierenAction = new AbstractAction() {

			private static final long serialVersionUID = 2324741792897120552L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (formattedTextFieldAnzahlTipps.getValue() == null
						|| formattedTextFieldAnzahlTipps.getValue().equals("")) {
					anzahlTipps = 1;
					logger.info(
							"Generieren Button gedrückt. Keine Anzahl, wie viele Tipps generiert werden sollen, wurde übergeben. Anzahl wurde auf 1 gesetzt.");
				} else {
					anzahlTipps = (int) formattedTextFieldAnzahlTipps.getValue();
					logger.info("Generieren Button gedrückt. " + anzahlTipps
							+ " wurde als Anzahl, wie viele Tipps generiert werden sollen, übergeben");
				}
				if (anzahlTipps > 1000) {
					lblNewLabelUngueltig.setVisible(true);
					Timer t = new Timer(5000, new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							lblNewLabelUngueltig.setVisible(false);
							logger.info(
									"Ungültige Eingabe wurde getätigt (über 1000 Tipps sollten generiert werden). Ungültige Eingabe Label wird für 5 Sekunden gezeigt.");
						}
					});
					t.setRepeats(false);
					t.start();
				} else if (anzahlTipps > 10) {
					FileOperation.speichern(tippGenerator.generiereTipps(anzahlTipps),
							"\\GenerierteTipps" + tippGenerator.lottoModus() + ".txt");
					textArea.append(
							"Bei der Generierung von mehr als 10 Tipps werden diese in einer Datei gespeichert:\n");
					textArea.append(FileOperation.currentDirectory()
							.concat("\\LottoTippGenFiles\\GenerierteTipps" + tippGenerator.lottoModus() + ".txt")
							+ System.lineSeparator());
					logger.info(
							"Mehr als 10 Tipps sollten generiert werden, daher werden die Tipps in einer Datei gespeichert. Die Information hierzu wurde auf der TextArea dem Nutzer ausgegeben.");
				} else {
					textArea.append(tippGenerator.generiereTipps(anzahlTipps));
					logger.info("Die Generierten Tipps wurden dem Nutzer auf der TextArea ausgegeben.");
				}
			}
		};
		btnGenerierenButton.addActionListener(generierenAction);

		/**
		 * Beenden Button
		 */
		JButton btnBeendenButton = new JButton("Beenden");
		btnBeendenButton.setBounds(685, 230, 100, 25);
		panelTextFieldUnten.add(btnBeendenButton);
		logger.info("Beenden Button durchgelaufen.");
		btnBeendenButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				logger.info("Beenden Button gedrückt, das Logging und das Programm werden nun Beendet.");
				System.exit(0);
			}
		});

	}
}
