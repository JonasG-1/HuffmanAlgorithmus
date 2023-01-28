import baumstrukturen.ComparableContent;

import java.io.Serializable;

/**
 * Die Klasse ZeichenCode speichert ein Zeichen mit seinem Huffman-Code.
 */
public class ZeichenCode implements Serializable,ComparableContent<ZeichenCode>
{
    static final long serialVersionUID = 7002;
    
    private char zZeichen;
    private String zCode;
    
    /**
     * Ein ZeichenCode mit dem angegebenen Zeichen und de angebenenen Code wurde erzeugt.
     */
    public ZeichenCode(char pZeichen, String pCode)
    {
         zZeichen = pZeichen;
         zCode = pCode;
    }
    
    /**
     * Das Zeichen wurde geliefert,
     */
    public char zeichen()
    {
        return zZeichen;
    }
    
    /**
     * Der Code wurde geliefert,
     */
    public String code()
    {
    	return zCode;
    }  
 
    /**
     * Ist wahr, wenn dieser ZeichenCode und pZeichen dasselbe Zeichen besitzen.
     */
    public boolean isEqual(ZeichenCode pZeichen)
    {
        return zZeichen == pZeichen.zeichen();
    }
        
    /**
     * Ist wahr, wenn dieser ZeichenCode ein kleineres Zeichen als pZeichen besitzt.
     */
    public boolean isLess(ZeichenCode pZeichen)
    {
        return zZeichen < pZeichen.zeichen();
    }
        
    /**
     * Ist wahr, wenn dieser ZeichenCode ein groesseres Zeichen als pZeichen besitzt.
     */
    public boolean isGreater(ZeichenCode pZeichen)
    {
        return zZeichen > pZeichen.zeichen();
    }
    
    /**
     * Die ZeichenAnzahl wurde als Zeichenkette geliefert.
     */
    public String toString()
    {
        return zZeichen + ":" + zCode;
    }

}