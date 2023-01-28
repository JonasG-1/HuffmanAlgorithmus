import baumstrukturen.BinarySearchTree;
import baumstrukturen.BinaryTree;
import linearestrukturen.List;
import sum.ereignis.EBAnwendung;
import sum.komponenten.Etikett;
import sum.komponenten.Knopf;
import sum.komponenten.Zeilenbereich;

import java.io.*;

public class SuchbaumInHuffmanbaumListe {

    BinarySearchTree<ZeichenAnzahl> hatZeichenzaehlbaum;
    List<BinaryTree<ZeichenAnzahl>> hatHuffmanbaumListe;

    /**
     * Vorher: Ereignis GeklicktvonhatKnopfInBaumliste fand statt.
     * Nachher: Der Haeufigkeitsbaum wurde geladen und umgewandelt.
     */
    public SuchbaumInHuffmanbaumListe() {
        this.ladeZeichenzaehlbaum();
        hatHuffmanbaumListe = this.huffmanbaumListeAus(hatZeichenzaehlbaum);
        this.speichereHuffmanbaumListe();
    }

    /**
     * nachher: Mithilfe des Zeichenzaehlbaums wurde eine Liste aus Huffmanbaeumen mit je einem Knoten geliefert.
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

    public void ladeZeichenzaehlbaum() {
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