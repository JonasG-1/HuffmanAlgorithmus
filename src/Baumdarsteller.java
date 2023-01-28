import baumstrukturen.BinaryTree;
import sum.ereignis.Buntstift;
import sum.ereignis.Farbe;
import sum.ereignis.Muster;

/**
 * Ein Baumdarsteller kann einen beliebigen bin&auml;ren Baum grafisch darstellen.
 * @author Horst Hildebrecht
 * @version 1
 */
public class Baumdarsteller 
{

    final static int ASCENT = 11;
    final static int DESCENT = 2;
    final static int RAND = 2;
    final static int HOEHE = ASCENT + DESCENT + 2 * RAND;
    
    // Bezugsobjekte
    Buntstift hatStift;
    
    // Attribute

    // Konstruktor
    /**
     * Der Baumdarsteller hat einen Stift, mit dem er einen Baum darstellen kann.
     */
    public Baumdarsteller()
    {
        hatStift = new Buntstift();
    }

    // Dienste
    /**
     * Der angegebene Baum wurde im durch links, rechts und oben begrenzten Bereich angezeigt. Der untere Rand ergibt sich durch die Tiefe des Baums.
     */
    public void zeige(BinaryTree pBaum, int pLinks, int pRechts, int pOben)
    {
        if (pBaum != null && !pBaum.isEmpty())
        {
            int lMitte = (pLinks + pRechts) / 2;
            int lBreite = hatStift.textBreite(pBaum.getContent().toString()) + 2 * RAND;
            int lLinks = lMitte - lBreite / 2;
            int lUnten = pOben + HOEHE;
            hatStift.bewegeBis(lLinks, pOben); hatStift.zeichneRechteck(lBreite, HOEHE);
            hatStift.bewegeBis(lLinks + RAND, pOben + RAND + ASCENT); hatStift.schreibeText(pBaum.getContent().toString());
            this.zeigeTeilbaum(pBaum.getLeftTree(), lMitte, lUnten, pLinks, lMitte);
            this.zeigeTeilbaum(pBaum.getRightTree(), lMitte, lUnten, lMitte, pRechts);
        }
    }

    private void zeigeTeilbaum(BinaryTree pTeilbaum, int pVaterMitte, int pVaterUnten, int pLinks, int pRechts)
    {
        if (!pTeilbaum.isEmpty())
        {
            hatStift.bewegeBis(pVaterMitte, pVaterUnten);
            int lOben = pVaterUnten + HOEHE;
            int lMitte = (pLinks + pRechts) / 2;
            hatStift.runter(); hatStift.bewegeBis(lMitte,lOben); hatStift.hoch();
            this.zeige(pTeilbaum, pLinks, pRechts, lOben);
        }
    }
        
   /**
     * Der angegebene Bereich wurde gel&ouml;scht. Danach wurde der Baum in diesem Bereich angezeigt.
     */
    public void zeige(BinaryTree pBaum, int pLinks, int pRechts, int pOben, int pUnten)
    {
        hatStift.setzeFarbe(Farbe.WEISS); hatStift.setzeFuellmuster(Muster.GEFUELLT);
        hatStift.bewegeBis(pLinks, pOben); hatStift.zeichneRechteck(pRechts - pLinks, pUnten - pOben);
        hatStift.setzeFarbe(Farbe.SCHWARZ);  hatStift.setzeFuellmuster(Muster.DURCHSICHTIG);
        this.zeige(pBaum, pLinks, pRechts, pOben);
    }

}
