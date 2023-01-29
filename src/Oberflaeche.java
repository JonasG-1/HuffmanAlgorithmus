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

    /**
     * Konstruktor
     */
    public Oberflaeche(Controller pController) {
        //Initialisierung der Oberklasse
        super(800, 800);
        hatController = pController;

        hatEtikettBaum = new Etikett(29, 175, 134, 26, "Baum:");

        hatEtikettZuKodierenderText = new Etikett(29, 28, 134, 26, "Zu kodierender Text:");
        // Ausrichtung
        hatEtikettZuKodierenderText.setzeAusrichtung(0);
        hatZeichenbereichZuKodierenderText = new Zeichenbereich(25, 53, 750, 100, "");
        hatZeichenbereichCode = new Zeichenbereich(25, 575, 750, 100, "");
        hatEtikettCode = new Etikett(29, 550, 134, 26, "Code:");
        hatKnopfKodieren = new Knopf(29, 700, 110, 38, "Kodieren");
        hatKnopfKodieren.setzeBearbeiterGeklickt("hatKnopfKodierenGeklickt");
        hatKnopfBeenden = new Knopf(267, 700, 110, 38, "Beenden");
        hatKnopfBeenden.setzeBearbeiterGeklickt("beenden");
    }

    public String gibText() {
        return hatZeichenbereichZuKodierenderText.inhaltAlsText();
    }

    public void hatKnopfKodierenGeklickt() {
        hatController.kodiere();
    }

    public void setzeCode(String pCode) {
        hatZeichenbereichCode.setzeInhalt(pCode);
    }

    public void zeichneBaum(BinaryTree pBaum) {
        Baumdarsteller lDarsteller = new Baumdarsteller();
        lDarsteller.zeige(pBaum, 0, 800, 180);
    }

    public void zeichneSuchBaum(BinarySearchTree pBaum) {
        Baumdarsteller lDarsteller = new Baumdarsteller();
        lDarsteller.zeige(pBaum, 0, 800, 180);
    }
}
