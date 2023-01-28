import baumstrukturen.*;
import linearestrukturen.*;
import sum.ereignis.*;
import sum.komponenten.*;

import java.io.*;

public class HuffmanbaumErsteller extends EBAnwendung {
    // Objekte
    Etikett hatEtikettHuffmanbaum;
    Knopf hatKnopfHuffmanbaumErzeugen;
    Knopf hatKnopfBeenden;
    Zeilenbereich hatZeilenbereichBaumliste;
    Etikett hatEtikettHuffmanbaumListe;

    BinaryTree<ZeichenAnzahl> hatHuffmanbaum;
    List<BinaryTree<ZeichenAnzahl>> hatHuffmanbaumListe;

    /**
     * Konstruktor
     */
    public HuffmanbaumErsteller()
    {
        //Initialisierung der Oberklasse
        super(900, 637);

        hatEtikettHuffmanbaumListe = new Etikett(29, 13, 130, 25, "Huffmanbaum-Liste");
        // Ausrichtung
        hatEtikettHuffmanbaumListe.setzeAusrichtung(0);
        hatEtikettHuffmanbaum = new Etikett(256, 13, 116, 25, "Huffmanbaum");
        // Ausrichtung
        hatEtikettHuffmanbaum.setzeAusrichtung(0);
        hatZeilenbereichBaumliste = new Zeilenbereich(29, 37, 200, 500, "");
        hatKnopfHuffmanbaumErzeugen = new Knopf(29, 575, 185, 38, "Huffmanbaum erzeugen");
        hatKnopfHuffmanbaumErzeugen.setzeBearbeiterGeklickt("hatKnopfHuffmanbaumErzeugenGeklickt");
        hatKnopfBeenden = new Knopf(256, 575, 110, 38, "Beenden");
        hatKnopfBeenden.setzeBearbeiterGeklickt("beenden");
    }

    /**
     * Vorher: Ereignis GeklicktvonhatKnopfHuffmanbaumErzeugen fand statt.
     * Nachher: Aus der Liste von Huffmanbaeumen wurde der zum Kodieren benoetigte Huffmanbaum erzeugt
     */
    public void ladeListeUndErstelleBaum() {
        this.ladeHuffmanbaumListe();
        while (hatHuffmanbaumListe.length() > 1) {
            this.reduziereHuffmanbaumListe(hatHuffmanbaumListe);
        }
        hatHuffmanbaumListe.toFirst();
        hatHuffmanbaum = hatHuffmanbaumListe.getContent();
        Baumdarsteller lDarsteller = new Baumdarsteller();
        lDarsteller.zeige(hatHuffmanbaum, 256, 900, 37);
        this.speichereHuffmanbaum();
    }

    /**
     * Nachher: Die minimalen Huffmanbaeume der Liste wurden zu einem mit summiertem Gewicht zusammengefasst.
     */
    private void reduziereHuffmanbaumListe(List<BinaryTree<ZeichenAnzahl>> pBaumliste) {
        pBaumliste.toFirst();
        BinaryTree<ZeichenAnzahl> lBaum1 = holeKleinstenBaumUndEntferne(pBaumliste);
        BinaryTree<ZeichenAnzahl> lBaum2 = holeKleinstenBaumUndEntferne(pBaumliste);
        int lAnzahl1 = lBaum1.getContent().anzahl();
        int lAnzahl2 = lBaum2.getContent().anzahl();
        BinaryTree<ZeichenAnzahl> lNeuerBaum = new BinaryTree<>(
                new ZeichenAnzahl('#', lAnzahl1 + lAnzahl2),
                lBaum1,
                lBaum2
        );
        pBaumliste.append(lNeuerBaum);

    }

    private BinaryTree<ZeichenAnzahl> holeKleinstenBaumUndEntferne(List<BinaryTree<ZeichenAnzahl>> pBaumliste) {
        geheZurMinimalenWurzel(pBaumliste);
        BinaryTree<ZeichenAnzahl> lBaum = pBaumliste.getContent();
        pBaumliste.remove();
        return lBaum;
    }

    // Tipp: Die folgende Methode ist hilfreich fuer die darueber!
    private void geheZurMinimalenWurzel(List<BinaryTree<ZeichenAnzahl>> pBaumListe) {
        int lAnzahl = -1;
        int lZeiger = 0;
        int lZeigerJetzt = 0;
        pBaumListe.toFirst();
        while (pBaumListe.hasAccess()) {
            int lAnzahlNeu = pBaumListe.getContent().getContent().anzahl();
            if (lAnzahl < 0 || lAnzahlNeu < lAnzahl) {
                lAnzahl = lAnzahlNeu;
                lZeiger = lZeigerJetzt;
            }
            lZeigerJetzt++;
            pBaumListe.next();
        }
        lZeigerJetzt = 0;
        pBaumListe.toFirst();
        while (lZeigerJetzt < lZeiger) {
            pBaumListe.next();
            lZeigerJetzt++;
        }
    }

    public void ladeHuffmanbaumListe()
    {
        try
        {
            ObjectInputStream lDatei = new ObjectInputStream(new FileInputStream("HuffmanbaumListe.ser"));
            hatHuffmanbaumListe = (List<BinaryTree<ZeichenAnzahl>>) lDatei.readObject();
            lDatei.close();
        }
        catch (ClassNotFoundException pFehler)
        {
            System.out.println(pFehler);
        }
        catch (IOException pFehler)
        {
            System.out.println(pFehler);
        }
    }

    public void speichereHuffmanbaum()
    {
        try
        {
            ObjectOutputStream lDatei = new ObjectOutputStream(new FileOutputStream("Huffmanbaum.ser"));
            lDatei.writeObject(hatHuffmanbaum);
            lDatei.close();
        }
        catch (IOException pFehler)
        {
            System.out.println(pFehler);
        }
    }

}