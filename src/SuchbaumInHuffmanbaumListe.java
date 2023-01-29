import baumstrukturen.BinarySearchTree;
import baumstrukturen.BinaryTree;
import linearestrukturen.List;

import java.io.*;

public class SuchbaumInHuffmanbaumListe {

    BinarySearchTree<ZeichenAnzahl> hatZeichenzaehlbaum;
    List<BinaryTree<ZeichenAnzahl>> hatHuffmanbaumListe;
    String zDateinameZaehlbaum;
    String zDateinameHuffmanbaumListe;

    /**
     * Vorher: Ereignis GeklicktvonhatKnopfInBaumliste fand statt.
     * Nachher: Der Haeufigkeitsbaum wurde geladen und umgewandelt.
     */
    public SuchbaumInHuffmanbaumListe(String pDateinameZaehlbaum, String pDateinameHuffmanbaumListe) {
        zDateinameZaehlbaum = pDateinameZaehlbaum;
        zDateinameHuffmanbaumListe = pDateinameHuffmanbaumListe;
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
            ObjectInputStream lDatei = new ObjectInputStream(new FileInputStream(zDateinameZaehlbaum));
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
            ObjectOutputStream lDatei = new ObjectOutputStream(new FileOutputStream(zDateinameHuffmanbaumListe));
            lDatei.writeObject(hatHuffmanbaumListe);
            lDatei.close();
        }
        catch (IOException pFehler)
        {
            System.out.println(pFehler);
        }
    }

}