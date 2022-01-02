import java.io.ByteArrayOutputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.Serializable;
import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 *
 * @author Evan Zhang
 */
public class Utils {
    private static final File CWD = new File(System.getProperty("user.dir"));
    private static final File EMS_DIR = join(CWD, ".ems");
    private static final File EMS = join(EMS_DIR, "ems");
    
    private static File join(File first, String... others) {
        return Paths.get(first.getPath(), others).toFile();
    }
    
    private static byte[] serialize(Serializable obj) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(stream);
            objectStream.writeObject(obj);
            objectStream.close();
            return stream.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }
    
    public static void writeHashTable(MyHashTable myHT) {
        if (!EMS_DIR.exists()) {
            EMS_DIR.mkdirs();
            try {
                EMS.createNewFile();
            } catch (IOException e) {}
        }
        byte[] contents = serialize(myHT);
        try {
            BufferedOutputStream str = new BufferedOutputStream(Files.newOutputStream(EMS.toPath()));
            str.write(contents);
            str.close();
        } catch (IOException | ClassCastException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    public static MyHashTable readHashTable() {
        if (!EMS.exists()) {
            return null;
        }
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(EMS));
            MyHashTable result = (MyHashTable) in.readObject();
            in.close();
            return result;
        } catch (IOException | ClassCastException | ClassNotFoundException e) {
            return null;
        }
    }
}
