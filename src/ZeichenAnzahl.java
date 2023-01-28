import baumstrukturen.*;

import java.io.Serializable;

/**
 * Die Klasse ZeichenAnzahl speichert ein Zeichen mit einem Haeufigkeitszaehler.
 */
public class ZeichenAnzahl implements Serializable,ComparableContent<ZeichenAnzahl>
{
    static final long serialVersionUID = 7001;
    
    private char zZeichen;
    private int zAnzahl;
    
    /**
     * Eine ZeichenAnzahl mit dem angegebenen Zeichen und der angebenenen Anzahl wurde erzeugt.
     */
    public ZeichenAnzahl(char pZeichen, int pAnzahl)
    {
         zZeichen=pZeichen;
         zAnzahl=pAnzahl;
    }
    
    /**
     * Das Zeichen wurde geliefert,
     */
    public char zeichen()
    {
        return zZeichen;
    }
    
    /**
     * Die Anzahl wurde geliefert,
     */
    public int anzahl()
    {
    	return zAnzahl;
    }
    
    /**
     * Die Anzahl hat jetzt den angegebenen Wert.
     */
    public void setzeAnzahl(int pAnzahl)
    {
    	zAnzahl = pAnzahl;
    } 
  
    
    /**
     * Die Anzahl wurde um 1 erhoeht.
     */
    public void erhoeheAnzahl()
    {
    	zAnzahl++;
    }
    
    /**
     * Ist wahr, wenn diese ZeichenAnzahl und pZeichen dasselbe Zeichen besitzen.
     */
    public boolean isEqual(ZeichenAnzahl pZeichen)
    {
        return zZeichen == pZeichen.zeichen();
    }
        
    /**
     * Ist wahr, wenn diese ZeichenAnzahl ein kleineres Zeichen als pZeichen besitzt.
     */
    public boolean isLess(ZeichenAnzahl pZeichen)
    {
        return zZeichen < pZeichen.zeichen();
    }
        
    /**
     * Ist wahr, wenn diese ZeichenAnzahl ein groesseres Zeichen als pZeichen besitzt.
     */
    public boolean isGreater(ZeichenAnzahl pZeichen)
    {
        return zZeichen > pZeichen.zeichen();
    }
    
    /**
     * Die ZeichenAnzahl wurde als Zeichenkette geliefert.
     */
    public String toString()
    {
        if (zZeichen == '#')
            return zAnzahl + "";
        else
            return zZeichen + ":" + zAnzahl;
    }
        
}