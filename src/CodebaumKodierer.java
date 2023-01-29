import baumstrukturen.BinarySearchTree;

import java.io.*;

public class CodebaumKodierer {

    String zText, zCode;
    BinarySearchTree<ZeichenCode> hatZeichenCodeBaum;
    String zDateinameText;
    String zDateinameCode;
    String zDateinameZeichenCodebaum;

    /**
     * Konstruktor
     */
    public CodebaumKodierer(String pDateinameText, String pDateinameCode, String pDateinameZeichenCodebaum) {
        zDateinameText = pDateinameText;
        zDateinameCode = pDateinameCode;
        zDateinameZeichenCodebaum = pDateinameZeichenCodebaum;
        this.ladeText();
        this.ladeZeichenCodeBaum();
        zCode = this.kodiert(zText, hatZeichenCodeBaum);
        this.speichereCode();
    }

    /**
     *  nachher: Der mit Hilfe des ZeichenCodeBaums kodierte Text wurde geliefert.
     */
    private String kodiert(String pText, BinarySearchTree<ZeichenCode> pZeichenCodeBaum) {
        String lCode = "";
        // Und wir laufen einfach durch...
        // Und suchen den Zeichencode im Baum
        for (int i = 0; i < pText.length(); i++) {
            char lZeichen = pText.charAt(i);
            ZeichenCode lZeichenCode = new ZeichenCode(lZeichen, "");
            lZeichenCode = pZeichenCodeBaum.search(lZeichenCode);
            lCode = lCode + lZeichenCode.code();
        }
        return lCode;
    }

    public String gibCode() {
        return zCode;
    }

    public void ladeText()
    {
        try
        {
            ObjectInputStream lDatei = new ObjectInputStream(new FileInputStream(zDateinameText));
            zText = (String) lDatei.readObject();
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

    public void ladeZeichenCodeBaum()
    {
        try
        {
            ObjectInputStream lDatei = new ObjectInputStream(new FileInputStream(zDateinameZeichenCodebaum));
            hatZeichenCodeBaum = (BinarySearchTree<ZeichenCode>) lDatei.readObject();
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

    public void speichereCode()
    {
        try
        {
            ObjectOutputStream lDatei = new ObjectOutputStream(new FileOutputStream(zDateinameCode));
            lDatei.writeObject(zCode);
            lDatei.close();
        }
        catch (IOException pFehler)
        {
            System.out.println(pFehler);
        }
    }

}