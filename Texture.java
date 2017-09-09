import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Texture{
  public static Texture metal = new Texture("res/metal.png", 64);
  public static Texture window = new Texture("res/window.png", 64);
  public static Texture vent = new Texture("res/vent.png", 64);
  public static Texture console = new Texture("res/console.png", 64);
  public int[] pixels;
  private String loc;
  public final int SIZE;
  public Texture(String location, int size){
    loc = location;
    SIZE = size;
    pixels = new int[SIZE * SIZE];
    load();
  }
  public void load(){
    try{
      BufferedImage image = ImageIO.read(new File(loc));
      int w = image.getWidth();
      int h = image.getHeight();
      image.getRGB(0, 0, w, h, pixels, 0, w);
    }
    catch (IOException e) {
        e.printStackTrace();
    }
  }
}
