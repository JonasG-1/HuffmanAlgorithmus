import baumstrukturen.BinarySearchTree;
import baumstrukturen.BinaryTree;
import linearestrukturen.List;

import java.util.Optional;

public class Controller {

    Oberflaeche oberflaeche;
    private final String zDateinameText = "Text.ser";
    private final String zDateinameZaehlbaum = "Zeichenzaehlbaum.ser";
    private final String zDateinameHuffmanbaumListe = "HuffmanbaumListe.ser";
    private final String zDateinameHuffmanbaum = "Huffmanbaum.ser";
    private final String zDateinameZeichenCodebaum = "ZeichenCodeBaum.ser";
    private final String zDateinameCode = "Code.ser";

    private final TypSpeicher<BinarySearchTree<ZeichenCode>> hatZeichenCodeBaum;
    private TypSpeicher<String> hatCodeSpeicher;
    private TypSpeicher<String> hatTextSpeicher;
    private final TypSpeicher<BinarySearchTree<ZeichenAnzahl>> hatZaehlbaumSpeicher;
    private TypSpeicher<List<BinaryTree<ZeichenAnzahl>>> hatHuffmanbaumListeSpeicher;
    private final TypSpeicher<BinaryTree<ZeichenAnzahl>> hatHuffmanbaumSpeicher;

    public static void main(String[] args) {
        System.out.println("Hello world!");
        new Controller();
    }

    public Controller() {
        oberflaeche = new Oberflaeche(this);
        hatZeichenCodeBaum = new TypSpeicher<>(zDateinameZeichenCodebaum);
        hatCodeSpeicher = new TypSpeicher<>(zDateinameCode);
        hatTextSpeicher = new TypSpeicher<>(zDateinameText);
        hatZaehlbaumSpeicher = new TypSpeicher<>(zDateinameZaehlbaum);
        hatHuffmanbaumListeSpeicher = new TypSpeicher<>(zDateinameHuffmanbaumListe);
        hatHuffmanbaumSpeicher = new TypSpeicher<>(zDateinameHuffmanbaum);
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

    public void zeigeZaehlbaum() {
        Optional<BinarySearchTree<ZeichenAnzahl>> optional = hatZaehlbaumSpeicher.lade();
        optional.ifPresent(oberflaeche::zeichneSuchBaum);
    }

    public void zeigeHuffmanbaum() {
        Optional<BinaryTree<ZeichenAnzahl>> optional = hatHuffmanbaumSpeicher.lade();
        optional.ifPresent(oberflaeche::zeichneBaum);
    }

    public void zeigeCodebaum() {
        Optional<BinarySearchTree<ZeichenCode>> optional = hatZeichenCodeBaum.lade();
        optional.ifPresent(oberflaeche::zeichneSuchBaum);
    }
}
