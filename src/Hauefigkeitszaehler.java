import baumstrukturen.BinarySearchTree;
import sum.ereignis.EBAnwendung;
import sum.komponenten.Etikett;
import sum.komponenten.Knopf;
import sum.komponenten.Zeichenbereich;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Hauefigkeitszaehler {


    BinarySearchTree<ZeichenAnzahl> hatZeichenzaehlbaum;
    String zText;



    /**
     * Vorher: Ereignis GeklicktvonhatKnopfZaehlen fand statt.
     * Nachher: Ein Zeichenzaehlbaum wurde aus dem zu kodierenden Text erstellt und abgespeichert.
     */
    public Hauefigkeitszaehler(String pText) {
        zText = pText;
        this.speichereText();
        hatZeichenzaehlbaum = this.zeichenzaehlbaumAus(zText);
        Baumdarsteller lDarsteller = new Baumdarsteller();
        lDarsteller.zeige(hatZeichenzaehlbaum, 0, 600, 160);
        this.speichereZeichenzaehlbaum();
    }

    /**
     * Nachher: Diese Methode liefert einen Suchbaum, der die Zeichen von pText gezaehlt hat.
     */
    private BinarySearchTree<ZeichenAnzahl> zeichenzaehlbaumAus(String pText) {
        BinarySearchTree<ZeichenAnzahl> lZeichenzaehlbaum = new BinarySearchTree<>();
        // und nun wird gezaehlt
        for (int i = 0; i < pText.length(); i++) {
            char lZeichen = pText.charAt(i);
            ZeichenAnzahl lZeichenAnzahl = new ZeichenAnzahl(lZeichen, 1);
            ZeichenAnzahl lGefunden = lZeichenzaehlbaum.search(lZeichenAnzahl);
            if (lGefunden != null) {
                lGefunden.erhoeheAnzahl();
            } else {
                lZeichenzaehlbaum.insert(lZeichenAnzahl);
            }
        }
        return lZeichenzaehlbaum;
    }

    public void speichereText()
    {
        try
        {
            ObjectOutputStream lDatei = new ObjectOutputStream(new FileOutputStream("Text.ser"));
            lDatei.writeObject(zText);
            lDatei.close();
        }
        catch (IOException pFehler)
        {
            System.out.println(pFehler);
        }
    }

    public void speichereZeichenzaehlbaum()
    {
        try
        {
            ObjectOutputStream lDatei = new ObjectOutputStream(new FileOutputStream("Zeichenzaehlbaum.ser"));
            lDatei.writeObject(hatZeichenzaehlbaum);
            lDatei.close();
        }
        catch (IOException pFehler)
        {
            System.out.println(pFehler);
        }
    }

}
