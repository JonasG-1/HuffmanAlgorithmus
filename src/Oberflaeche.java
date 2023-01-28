import sum.ereignis.EBAnwendung;
import sum.komponenten.Etikett;
import sum.komponenten.Knopf;
import sum.komponenten.Zeichenbereich;

public class Oberflaeche extends EBAnwendung {

    // Objekte
    Etikett hatEtikettZuKodierenderText;
    Etikett hatEtikettHaeufigkeitsbaum;
    Zeichenbereich hatZeichenbereichZuKodierenderText;
    Knopf hatKnopfKodieren;
    Knopf hatKnopfBeenden;
    Controller hatController;

    /**
     * Konstruktor
     */
    public Oberflaeche(Controller pController) {
        //Initialisierung der Oberklasse
        super(600, 600);
        hatController = pController;

        hatEtikettZuKodierenderText = new Etikett(29, 28, 134, 26, "Zu kodierender Text");
        // Ausrichtung
        hatEtikettZuKodierenderText.setzeAusrichtung(0);
        hatZeichenbereichZuKodierenderText = new Zeichenbereich(25, 53, 550, 100, "");
        hatEtikettHaeufigkeitsbaum = new Etikett(29, 160, 116, 25, "Häufigkeitsbaum");
        // Ausrichtung
        hatEtikettHaeufigkeitsbaum.setzeAusrichtung(0);
        hatKnopfKodieren = new Knopf(29, 475, 110, 38, "Kodieren");
        hatKnopfKodieren.setzeBearbeiterGeklickt("hatKnopfKodierenGeklickt");
        hatKnopfBeenden = new Knopf(267, 475, 110, 38, "Beenden");
        hatKnopfBeenden.setzeBearbeiterGeklickt("beenden");
    }

    public String gibText() {
        return hatZeichenbereichZuKodierenderText.inhaltAlsText();
    }

    public void hatKnopfKodierenGeklickt() {
        new Hauefigkeitszaehler(gibText());
    }
}
