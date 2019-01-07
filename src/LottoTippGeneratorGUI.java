import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.ButtonGroup;

import javax.swing.text.NumberFormatter;
import javax.swing.JRadioButton;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
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

	private JFrame frmLottoTippgenerator;
	public TippGenerator tippGenerator;
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private int anzahlTipps = 1;
	private final TextArea textArea = new TextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// new Logging();
		logger.setUseParentHandlers(false); // kann ersetzt werden sobald Logging läuft.
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
		String stringZahlen = makeStringFromArrList(Laden.laden());
		tippGenerator = new TippGenerator(new SechsAusNeunundvierzig());
		initialize(stringZahlen);
		textArea.append(StringSammlung.begruessungGUI());

	}

	private String makeStringFromArrList(ArrayList<Integer> arrayList) {
		if (arrayList.isEmpty()) {
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
		logger.log(Level.INFO,
				"Es wurde der String: " + stringZahlen + " aus dem Array der gesperrten Zahlen generiert.");
		return stringZahlen;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String stringZahlen) {
		frmLottoTippgenerator = new JFrame();
		frmLottoTippgenerator.setSize(628, 384);
		frmLottoTippgenerator.setTitle("Lotto Tipp-Generator");
		frmLottoTippgenerator.getContentPane().setBackground(Color.GRAY);
		frmLottoTippgenerator.getContentPane().setLayout(null);
		ButtonGroup spielmodusRadio = new ButtonGroup();

		/**
		 * Menü Leiste
		 */
		JMenuBar menuBar = new JMenuBar();
		frmLottoTippgenerator.setJMenuBar(menuBar);

		/**
		 * Menü Punkt Datei
		 */
		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);

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
		txtrAktuellAusgeschlosseneZahlen.setBounds(0, 0, 526, 25);
		txtrAktuellAusgeschlosseneZahlen.setText(" Aktuell ausgeschlossene Ungl\u00FCckszahlen: " + stringZahlen);
		frmLottoTippgenerator.getContentPane().add(txtrAktuellAusgeschlosseneZahlen);

		/**
		 * Reset Button + Option Pane zur Bestätigung
		 */
		JButton btnResetButton = new JButton("Reset");
		btnResetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showOptionDialog(new JFrame(),
						"Durch einen Reset werden die aktuell gesperrten Zahlen wieder erlaubt! Bitte bestätigen.",
						"Reset bitte bestätigen", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);

				if (n == JOptionPane.YES_OPTION) {
					tippGenerator.reset();
					String stringZahlen = makeStringFromArrList(Laden.laden());
					txtrAktuellAusgeschlosseneZahlen.setText("Aktuell ausgeschlossene Zahlen: " + stringZahlen);
				} else if (n == JOptionPane.NO_OPTION) {
					System.out.println("No");
				} else if (n == JOptionPane.CLOSED_OPTION) {
					System.out.println("Closed by hitting the cross");
				}
			}
		});
		btnResetButton.setBounds(525, 0, 92, 25);
		frmLottoTippgenerator.getContentPane().add(btnResetButton);

		/**
		 * Panel in welchem die Optionen für den Generator zu finden sind (Modus,
		 * Textfeld zur Anzahl an Tipps, welche Generiert werden sollen).
		 */
		JPanel panelGeneratorOprionen = new JPanel();
		panelGeneratorOprionen.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Generator Optionen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelGeneratorOprionen.setBounds(0, 25, 225, 80);
		frmLottoTippgenerator.getContentPane().add(panelGeneratorOprionen);
		panelGeneratorOprionen.setLayout(null);

		/**
		 * Einer der beiden Radio Buttons zur Modus Auswahl 6aus49
		 */
		JRadioButton rdbtnaus = new JRadioButton("6aus49");
		rdbtnaus.setBounds(5, 15, 110, 30);
		panelGeneratorOprionen.add(rdbtnaus);
		rdbtnaus.setToolTipText("Setzt den gewählten Spielmodus auf 6aus49");
		rdbtnaus.setSelected(true);
		spielmodusRadio.add(rdbtnaus);
		rdbtnaus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tippGenerator = new TippGenerator(new SechsAusNeunundvierzig());
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
		rdbtnEurojackpot.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tippGenerator = new TippGenerator(new Eurojackpot());
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
		JFormattedTextField formattedTextField = new JFormattedTextField(formatter);
		formattedTextField.setBounds(121, 35, 45, 20);
		panelGeneratorOprionen.add(formattedTextField);

		/**
		 * Label welches unter dem Textfeld zur Tippanzahl-Eingabe erscheint, wenn eine
		 * ungültige eingabe getätigt wurde (>1.000).
		 */
		JLabel lblNewLabelUngueltig = new JLabel("Ungültige Eingabe.");
		lblNewLabelUngueltig.setBounds(121, 55, 100, 20);
		panelGeneratorOprionen.add(lblNewLabelUngueltig);
		lblNewLabelUngueltig.setVisible(false);
		lblNewLabelUngueltig.setForeground(Color.RED);
		lblNewLabelUngueltig.setToolTipText("Nur Zahlen zwischen 0 und 1.000 sind erlaubt!");

		/**
		 * Panel oben Rechts. Für auschließen/wieder erlauben von zahlen zuständig
		 */
		JPanel panelObenRechts = new JPanel();
		panelObenRechts.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Unglückszahlen bearbeiten", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelObenRechts.setBounds(225, 25, 391, 80);
		frmLottoTippgenerator.getContentPane().add(panelObenRechts);
		panelObenRechts.setLayout(null);

		JTextField unglueckszahlenAusschliessenField = new JTextField();
		unglueckszahlenAusschliessenField.setToolTipText(
				"Eine oder mehrere Zahlen durch ein Leerzeichen getrennt eingeben. Die eingegebenen Zahlen werden von der Tippgenerierung ausgeschlossen.\n"
						+ " Ung\u00FCltige Zahlen und Zeichen werden Ignoriert. Es k\u00F6nnen Maximal 6 Zahlen von der Tippgenerierung ausgeschlossen sein.");
		unglueckszahlenAusschliessenField.setBounds(150, 15, 77, 20);
		panelObenRechts.add(unglueckszahlenAusschliessenField);

		JTextField unglueckszahlenZulassenField = new JTextField();
		unglueckszahlenZulassenField.setToolTipText(
				"Eine oder mehrere Zahlen durch ein Leerzeichen getrennt eingeben. Die eingegebenen Zahlen werden zur Tippgenerierung wieder zugelassen.\n"
						+ " Ung\u00FCltige Zahlen und Zeichen werden Ignoriert. Es k\u00F6nnen nur Zahlen verwendet werden, welche aktuell unter den ausgeschlossenen"
						+ " Zahlen sind.");
		unglueckszahlenZulassenField.setBounds(150, 45, 77, 20);
		panelObenRechts.add(unglueckszahlenZulassenField);
		unglueckszahlenZulassenField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Zahl(en) festlegen");
		lblNewLabel.setBounds(10, 20, 136, 15);
		panelObenRechts.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Zahl(en) l\u00F6schen");
		lblNewLabel_1.setBounds(10, 50, 136, 15);
		panelObenRechts.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Best\u00E4tigen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StringReader unglueckszahlenAusschliessen = new StringReader(
						unglueckszahlenAusschliessenField.getText().toString());
				StringReader unglueckszahlenZulassen = new StringReader(
						unglueckszahlenZulassenField.getText().toString());

				Tuple rueckgabeAusschliessen = Benutzereingabe.erfrageLottoZahlen(unglueckszahlenAusschliessen);
				Tuple rueckgabeZulassen = Benutzereingabe.erfrageLottoZahlen(unglueckszahlenZulassen);

				textArea.append(rueckgabeAusschliessen.getString()); // Ungültig eingegebene Zeichen
				textArea.append(tippGenerator.neueUnglueckszahlAusschliessen(rueckgabeAusschliessen.getIntegerArr()));

				textArea.append(rueckgabeZulassen.getString()); // Ungültig eingegebene Zeichen
				textArea.append(tippGenerator.unglueckszahlWiederZulassen(rueckgabeZulassen.getIntegerArr()));

				String stringZahlen = makeStringFromArrList(Laden.laden());
				txtrAktuellAusgeschlosseneZahlen.setText("Aktuell ausgeschlossene Unglückszahlen: " + stringZahlen);

				unglueckszahlenAusschliessenField.setText("");
				unglueckszahlenZulassenField.setText("");
			}
		});
		btnNewButton.setToolTipText(
				"Zun\u00E4cht werden die Zahlen, wenn vorhanden, aus dem \"festlegen\" Feld behandelt. Danach werden die Zahlen, wenn vorhanden, aus dem \"l\u00F6schen\" Feld behandelt.");
		btnNewButton.setBounds(292, 44, 89, 23);
		panelObenRechts.add(btnNewButton);

		/**
		 * Panel für die Ausgabe von Informationen an den Nutzer. Enthält ein TextField
		 * sowie die Buttons zum generieren und beenden.
		 */
		JPanel panelTextFieldUnten = new JPanel();
		panelTextFieldUnten.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Informationen & Ausgabe", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelTextFieldUnten.setBounds(0, 104, 616, 224);
		frmLottoTippgenerator.getContentPane().add(panelTextFieldUnten);
		panelTextFieldUnten.setLayout(null);

		/**
		 * TextField zur Ausgabe von Informationen an den Nutzer
		 */
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);

		textArea.setBounds(10, 20, 420, 195);
		panelTextFieldUnten.add(textArea);

		/**
		 * Generieren Button
		 */

		// TODO unterscheidung einführen. Bei <10 tipps diese im textfeld ausgeben. Bei
		// mehr die Tipps in eine Datei speichern und die Info dazu über das textfeld
		// ausgeben.
		JButton btnGenerierenButton = new JButton("Generieren");
		btnGenerierenButton.setBounds(520, 190, 85, 25);
		panelTextFieldUnten.add(btnGenerierenButton);
		btnGenerierenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (formattedTextField.getValue() == null) {
					anzahlTipps = 1;
				} else {
					anzahlTipps = (int) formattedTextField.getValue();
				}
				if (anzahlTipps > 1000) {
					lblNewLabelUngueltig.setVisible(true);
					Timer t = new Timer(5000, new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							lblNewLabelUngueltig.setVisible(false);
						}
					});
					t.setRepeats(false);
					t.start();
				} else {
					textArea.append(tippGenerator.generiereTipps(anzahlTipps));
				}
			}
		});

		/**
		 * Beenden Button
		 */
		JButton btnBeendenButton = new JButton("Beenden");
		btnBeendenButton.setBounds(435, 190, 75, 25);
		panelTextFieldUnten.add(btnBeendenButton);
		btnBeendenButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Logging.quit();
				System.exit(0);
			}
		});

	}
}
