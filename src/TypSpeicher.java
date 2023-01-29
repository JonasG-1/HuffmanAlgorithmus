import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class TypSpeicher<ContentType> {

    private final String zDateiname;

    public TypSpeicher(String pDateiname) {
        zDateiname = pDateiname;
    }

    public String gibDateiname() {
        return zDateiname;
    }

    public void speichere(ContentType pInhalt) {
        try {
            ObjectOutputStream lDatei = new ObjectOutputStream(Files.newOutputStream(Paths.get(zDateiname)));
            lDatei.writeObject(pInhalt);
            lDatei.close();
        } catch (IOException pFehler) {
            pFehler.printStackTrace();
        }
    }

    public Optional<ContentType> lade() {
        Optional<ContentType> lInhalt = Optional.empty();
        try {
            ObjectInputStream lDatei = new ObjectInputStream(Files.newInputStream(Paths.get(zDateiname)));
            lInhalt = Optional.of((ContentType) lDatei.readObject());
            lDatei.close();
        } catch (ClassNotFoundException | IOException pFehler) {
            pFehler.printStackTrace();
        }
        return lInhalt;
    }
}
