import baumstrukturen.BinarySearchTree;
import baumstrukturen.BinaryTree;
import sum.ereignis.EBAnwendung;
import sum.komponenten.Etikett;
import sum.komponenten.Knopf;
import sum.komponenten.Zeichenbereich;

public class Oberflaeche extends EBAnwendung {

    // Objekte
    Etikett hatEtikettZuKodierenderText;
    Etikett hatEtikettBaum;
    Zeichenbereich hatZeichenbereichZuKodierenderText;
    Etikett hatEtikettCode;
    Zeichenbereich hatZeichenbereichCode;
    Knopf hatKnopfKodieren;
    Knopf hatKnopfBeenden;
    Controller hatController;
    Etikett hatEtikettBaumAnzeigen;
    Knopf hatZaehlbaumKnopf;
    Knopf hatHuffmanbaumKnopf;
    Knopf hatCodebaumKnopf;
    Etikett hatEtikettUerbersetzung;
    Zeichenbereich hatZeichenbereichUebersetzung;

    /**
     * Konstruktor
     */
    public Oberflaeche(Controller pController) {
        //Initialisierung der Oberklasse
        super(1000, 800);
        hatController = pController;

        hatEtikettBaum = new Etikett(29, 175, 134, 26, "Baum:");

        hatEtikettZuKodierenderText = new Etikett(29, 28, 134, 26, "Zu kodierender Text:");
        // Ausrichtung
        hatEtikettZuKodierenderText.setzeAusrichtung(0);
        hatZeichenbereichZuKodierenderText = new Zeichenbereich(25, 53, 950, 100, "");
        hatZeichenbereichCode = new Zeichenbereich(25, 575, 750, 100, "");
        hatEtikettCode = new Etikett(29, 550, 134, 26, "Code:");
        hatKnopfKodieren = new Knopf(29, 730, 100, 38, "Kodieren");
        hatKnopfKodieren.setzeBearbeiterGeklickt("hatKnopfKodierenGeklickt");
        hatKnopfBeenden = new Knopf(140, 730, 100, 38, "Beenden");
        hatKnopfBeenden.setzeBearbeiterGeklickt("beenden");
        hatEtikettBaumAnzeigen = new Etikett(280, 700, 134, 26, "Baum anzeigen:");
        hatZaehlbaumKnopf = new Knopf(280, 730, 100, 38, "Zaehlbaum");
        hatZaehlbaumKnopf.setzeBearbeiterGeklickt("hatZaehlbaumKnopfGeklickt");
        hatHuffmanbaumKnopf = new Knopf(390, 730, 100, 38, "Huffmanbaum");
        hatHuffmanbaumKnopf.setzeBearbeiterGeklickt("hatHuffmanbaumKnopfGeklickt");
        hatCodebaumKnopf = new Knopf(500, 730, 100, 38, "Codebaum");
        hatCodebaumKnopf.setzeBearbeiterGeklickt("hatCodebaumKnopfGeklickt");
        hatEtikettUerbersetzung = new Etikett(800, 175, 134, 26, "Übersetzung:");
        hatZeichenbereichUebersetzung = new Zeichenbereich(800, 200, 175, 475, "");
        hatZeichenbereichUebersetzung.deaktiviere();
    }

    public String gibText() {
        return hatZeichenbereichZuKodierenderText.inhaltAlsText();
    }

    public void hatKnopfKodierenGeklickt() {
        if (hatZeichenbereichZuKodierenderText.inhaltAlsText().length() > 0) {
            hatController.kodiere();
        }
    }

    public void hatZaehlbaumKnopfGeklickt() {
        hatController.zeigeZaehlbaum();
    }

    public void hatHuffmanbaumKnopfGeklickt() {
        hatController.zeigeHuffmanbaum();
    }

    public void hatCodebaumKnopfGeklickt() {
        hatController.zeigeCodebaum();
    }

    public void setzeCode(String pCode) {
        hatZeichenbereichCode.setzeInhalt(pCode);
    }

    public void zeichneBaum(BinaryTree pBaum) {
        Baumdarsteller lDarsteller = new Baumdarsteller();
        lDarsteller.zeige(pBaum, 0, 800, 200);
    }

    public void zeichneSuchBaum(BinarySearchTree pBaum) {
        Baumdarsteller lDarsteller = new Baumdarsteller();
        lDarsteller.zeige(pBaum, 0, 800, 200);
    }

    public void heangeUebersetzungAn(String pZeichen, String pCode) {
        hatZeichenbereichUebersetzung.haengeAn(pZeichen + ": " + pCode + "\n");
    }

    public void loescheUebersetzung() {
        hatZeichenbereichUebersetzung.setzeInhalt("");
    }
}
