public class Controller {

    Oberflaeche oberflaeche;
    private final String zDateinameText = "Text.ser";
    private final String zDateinameZaehlbaum = "Zeichenzaehlbaum.ser";
    private final String zDateinameHuffmanbaumListe = "HuffmanbaumListe.ser";
    private final String zDateinameHuffmanbaum = "Huffmanbaum.ser";
    private final String zDateinameZeichenCodebaum = "ZeichenCodeBaum.ser";
    private final String zDateinameCode = "Code.ser";


    public static void main(String[] args) {
        System.out.println("Hello world!");
        new Controller();
    }

    public Controller() {
        oberflaeche = new Oberflaeche(this);
    }

    public void kodiere() {
        new Hauefigkeitszaehler(zDateinameText, zDateinameZaehlbaum, oberflaeche.gibText());
        new SuchbaumInHuffmanbaumListe(zDateinameZaehlbaum, zDateinameHuffmanbaumListe);
        new HuffmanbaumErsteller(zDateinameHuffmanbaumListe, zDateinameHuffmanbaum);
        new CodebaumAusHuffmanbaum(zDateinameHuffmanbaum, zDateinameZeichenCodebaum);
        CodebaumKodierer codebaumKodierer =
                new CodebaumKodierer(zDateinameText, zDateinameCode, zDateinameZeichenCodebaum);
        String lCode = codebaumKodierer.gibCode();
        oberflaeche.setzeCode(lCode);
    }
}
