package images;

import java.awt.Image;
import java.awt.Toolkit;


public class ResourceLoader {
    public static ResourceLoader rl = new ResourceLoader();
    
    public static Image getImage(String name) {
        return Toolkit.getDefaultToolkit().getImage(rl.getClass().getResource(name));
    }
}
