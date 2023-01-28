import baumstrukturen.BinarySearchTree;
import baumstrukturen.BinaryTree;
import linearestrukturen.List;
import sum.ereignis.EBAnwendung;
import sum.komponenten.Etikett;
import sum.komponenten.Knopf;
import sum.komponenten.Zeilenbereich;

import java.io.*;

public class SuchbaumInHuffmanbaumListe extends EBAnwendung {
    // Objekte
    Etikett hatEtikettHaeufigkeitsbaum, hatEtikettHuffmanbaumListe;
    Knopf hatKnopfInBaumliste;
    Knopf hatKnopfBeenden;
    Zeilenbereich hatZeilenbereichBaumliste;

    BinarySearchTree<ZeichenAnzahl> hatZeichenzaehlbaum;
    List<BinaryTree<ZeichenAnzahl>> hatHuffmanbaumListe;

    /**
     * Konstruktor
     */
    public SuchbaumInHuffmanbaumListe() {
        //Initialisierung der Oberklasse
        super(700, 647);

        hatEtikettHaeufigkeitsbaum = new Etikett(10, 10, 116, 25, "H�ufigkeitsbaum");
        // Ausrichtung
        hatEtikettHaeufigkeitsbaum.setzeAusrichtung(0);
        hatEtikettHuffmanbaumListe = new Etikett(550, 10, 130, 25, "Huffmanbaum-Liste");
        // Ausrichtung
        hatEtikettHuffmanbaumListe.setzeAusrichtung(0);
        hatZeilenbereichBaumliste = new Zeilenbereich(550, 30, 130, 500, "");        
        hatKnopfInBaumliste = new Knopf(29, 575, 110, 30, "In Baumliste");
        hatKnopfInBaumliste.setzeBearbeiterGeklickt("hatKnopfInBaumlisteGeklickt");
        hatKnopfBeenden = new Knopf(267, 575, 110, 30, "Beenden");
        hatKnopfBeenden.setzeBearbeiterGeklickt("beenden");

    }

    /**
     * Vorher: Ereignis GeklicktvonhatKnopfInBaumliste fand statt.
     * Nachher: Der Haeufigkeitsbaum wurde geladen und umgewandelt.
     */
    public void hatKnopfInBaumlisteGeklickt() {
        this.ladeZeichenzaehlbaum();
        Baumdarsteller lDarsteller = new Baumdarsteller();
        lDarsteller.zeige(hatZeichenzaehlbaum, 0, 550, 30);
        hatHuffmanbaumListe = this.huffmanbaumListeAus(hatZeichenzaehlbaum);
        this.zeigeHuffmanbaumListe();
        this.speichereHuffmanbaumListe();
    }

    /**
     * nachher: Mit Hilfe des Zeichenzaehlbaums wurde eine Liste aus Huffmanbaeumen mit je einem Knoten geliefert.
     */
    private List<BinaryTree<ZeichenAnzahl>> huffmanbaumListeAus(BinarySearchTree<ZeichenAnzahl> pZeichenzaehlbaum) {
        List<BinaryTree<ZeichenAnzahl>> lBaumListe = new List<>();
        lBaumListe.toFirst();
        // Und nun aus dem Zeichenzaehlbaum eine Liste plattklopfen... (rekursive Hilfsmethode noetig!)
        baumListeAus(pZeichenzaehlbaum, lBaumListe);
        return lBaumListe;
    }

    private void baumListeAus(BinarySearchTree<ZeichenAnzahl> pZeichenzaehlbaum, List<BinaryTree<ZeichenAnzahl>> pBaumListe) {
        // Hier kommt der Code rein, der die Liste aus dem Baum erzeugt.
        ZeichenAnzahl lZeichenAnzahl = pZeichenzaehlbaum.getContent();
        if (lZeichenAnzahl != null) {
            pBaumListe.append(new BinaryTree<>(lZeichenAnzahl));
            BinarySearchTree<ZeichenAnzahl> leftTree = pZeichenzaehlbaum.getLeftTree();
            BinarySearchTree<ZeichenAnzahl> rightTree = pZeichenzaehlbaum.getRightTree();
            baumListeAus(leftTree, pBaumListe);
            baumListeAus(rightTree, pBaumListe);
        }
    }

    private void zeigeHuffmanbaumListe() {
        hatZeilenbereichBaumliste.loescheAlles();
        hatHuffmanbaumListe.toFirst();
        while (hatHuffmanbaumListe.hasAccess()) {
            BinaryTree<ZeichenAnzahl> lHuffmanbaum = hatHuffmanbaumListe.getContent();
            ZeichenAnzahl lZeichenAnzahl = lHuffmanbaum.getContent();
            hatZeilenbereichBaumliste.haengeAn(lZeichenAnzahl.toString());
            hatHuffmanbaumListe.next();
        }

    }

    public void ladeZeichenzaehlbaum()
    {
        try
        {
            ObjectInputStream lDatei = new ObjectInputStream(new FileInputStream("Zeichenzaehlbaum.ser"));
            hatZeichenzaehlbaum = (BinarySearchTree<ZeichenAnzahl>) lDatei.readObject();
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

    public void speichereHuffmanbaumListe()
    {
        try
        {
            ObjectOutputStream lDatei = new ObjectOutputStream(new FileOutputStream("HuffmanbaumListe.ser"));
            lDatei.writeObject(hatHuffmanbaumListe);
            lDatei.close();
        }
        catch (IOException pFehler)
        {
            System.out.println(pFehler);
        }
    }

}