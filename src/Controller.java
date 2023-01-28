import sun.security.krb5.internal.crypto.CksumType;

public class Controller {

    Oberflaeche oberflaeche;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        new Controller();
    }

    public Controller() {
        oberflaeche = new Oberflaeche(this);
    }

    public void kodiere() {
        new Hauefigkeitszaehler(oberflaeche.gibText());
        new SuchbaumInHuffmanbaumListe();
        new HuffmanbaumErsteller();
        new CodebaumAusHuffmanbaum();
    }
}
