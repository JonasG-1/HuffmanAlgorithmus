import baumstrukturen.BinarySearchTree;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Hauefigkeitszaehler {


    BinarySearchTree<ZeichenAnzahl> hatZeichenzaehlbaum;
    String zText;
    String zDateinameText;
    String zDateinameZaehlbaum;



    /**
     * Vorher: Ereignis GeklicktvonhatKnopfZaehlen fand statt.
     * Nachher: Ein Zeichenzaehlbaum wurde aus dem zu kodierenden Text erstellt und abgespeichert.
     */
    public Hauefigkeitszaehler(String pDateinameText, String pDateinameZaehlbaum, String pText) {
        zDateinameText = pDateinameText;
        zDateinameZaehlbaum = pDateinameZaehlbaum;
        zText = pText;
        this.speichereText();
        hatZeichenzaehlbaum = this.zeichenzaehlbaumAus(zText);
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
            ObjectOutputStream lDatei = new ObjectOutputStream(new FileOutputStream(zDateinameText));
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
            ObjectOutputStream lDatei = new ObjectOutputStream(new FileOutputStream(zDateinameZaehlbaum));
            lDatei.writeObject(hatZeichenzaehlbaum);
            lDatei.close();
        }
        catch (IOException pFehler)
        {
            System.out.println(pFehler);
        }
    }

}
