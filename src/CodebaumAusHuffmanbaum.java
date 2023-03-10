import baumstrukturen.BinarySearchTree;
import baumstrukturen.BinaryTree;

import java.io.*;

public class CodebaumAusHuffmanbaum {

    BinaryTree<ZeichenAnzahl> hatHuffmanbaum;
    BinarySearchTree<ZeichenCode> hatZeichenCodeBaum;
    String zDateinameHuffmanbaum;
    String zDateinameZeichenCodebaum;

    /**
     * Konstruktor
     */
    public CodebaumAusHuffmanbaum(String pDateinameHuffmanbaum, String pDateinameZeichenCodeBaum) {
        zDateinameHuffmanbaum = pDateinameHuffmanbaum;
        zDateinameZeichenCodebaum = pDateinameZeichenCodeBaum;
        this.ladeHuffmanbaum();
        this.erzeugeZeichenCodeBaum();
        this.speichereZeichenCodeBaum();
    }

    /**
     * Nachher: Ein ZeichenCodeBaum, der alle Zeichen und ihre Codes aus dem Huffmanbaum enthaelt, wurde erstellt.
     */
    private void erzeugeZeichenCodeBaum() {
        hatZeichenCodeBaum = new BinarySearchTree<>();
        this.erstelleCodeBaumAus(hatZeichenCodeBaum, hatHuffmanbaum, "");
    }

    /**
     * Nachher: Der pZeichenCodeBaum wurde um alle Codes aus dem pHuffmanbaum, beginnend mit pPfad ergaenzt. 
     */
    private void erstelleCodeBaumAus(
            BinarySearchTree<ZeichenCode> pZeichenCodeBaum,
            BinaryTree<ZeichenAnzahl> pHuffmanbaum,
            String pPfad
    ) {
        // Hinweis: nach links = 0, nach rechts = 1
        // Diese rekursive Methode muss erstellt werden.
        ZeichenAnzahl lWurzel = pHuffmanbaum.getContent();
        if (lWurzel != null) {
            if (lWurzel.zeichen() != '#') {
                if (pPfad.equals("")) {
                    pPfad = "1";
                }
                pZeichenCodeBaum.insert(new ZeichenCode(lWurzel.zeichen(), pPfad));
            } else {
                erstelleCodeBaumAus(pZeichenCodeBaum, pHuffmanbaum.getLeftTree(), pPfad + "0");
                erstelleCodeBaumAus(pZeichenCodeBaum, pHuffmanbaum.getRightTree(), pPfad + "1");
            }
        }
    }

    public void ladeHuffmanbaum()
    {
        try
        {
            ObjectInputStream lDatei = new ObjectInputStream(new FileInputStream(zDateinameHuffmanbaum));
            hatHuffmanbaum = (BinaryTree<ZeichenAnzahl>) lDatei.readObject();
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

    public void speichereZeichenCodeBaum()
    {
        try
        {
            ObjectOutputStream lDatei = new ObjectOutputStream(new FileOutputStream(zDateinameZeichenCodebaum));
            lDatei.writeObject(hatZeichenCodeBaum);
            lDatei.close();
        }
        catch (IOException pFehler)
        {
            System.out.println(pFehler);
        }
    }

}