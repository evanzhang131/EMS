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
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Evan Zhang
 */
public class Utils {
    private static final File CWD = new File(System.getProperty("user.dir"));
    private static final File EMS_DIR = join(CWD, ".ems");
    private static final File EMS = join(EMS_DIR, "ems");
    private static final File IMAGES_DIR = join(EMS_DIR, "images");
    private static final File DEFAULT = join(IMAGES_DIR, "default.jpg");
    
    
    public static void initFiles() {
        if (!EMS_DIR.exists()) {
            EMS_DIR.mkdirs();
            try {
                EMS.createNewFile();
            } catch (IOException e) {}
        }
        if (!IMAGES_DIR.exists()) {
            IMAGES_DIR.mkdirs();   
        }
    }
    
    public static void drawImage(JLabel label, ImageIcon icon) {
        label.setIcon(icon);
    }
    
    public static void drawImage(JLabel label, File path) {
        try {
            BufferedImage img = ImageIO.read(path);
            Image icon = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(icon));
        } catch (IOException e) {
            drawDefault(label);
        }
    }
    
    public static void drawDefault(JLabel label) {
        drawImage(label, DEFAULT);
    }
    
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
