import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import javax.swing.JFrame;
public class Game extends JFrame implements Runnable{
  private static final long serialVersionUID = 1L;
  //sets map size
  public int mapWidth = 15;
  public int mapHeight = 15;
  private Thread thread;
  private boolean running;
  private BufferedImage image;
  public int[] pixels;
  public static int[][] map = 
  {
    {1,1,1,1,1,1,1,1,2,2,2,2,2,2,2},
    {1,0,0,0,0,0,0,0,2,0,0,0,0,0,2},
    {1,0,3,3,3,3,3,0,0,0,0,0,0,0,2},
    {1,0,3,0,0,0,3,0,2,0,0,0,0,0,2},
    {1,0,3,0,0,0,3,0,2,2,2,0,2,2,2},
    {1,0,3,0,0,0,3,0,2,0,0,0,0,0,2},
    {1,0,3,0,0,0,3,0,2,0,0,0,0,0,2},
    {1,0,0,0,0,0,0,0,2,0,0,0,0,0,2},
    {1,1,1,1,1,1,1,1,4,4,4,0,4,4,4},
    {1,0,0,0,0,0,1,4,0,0,0,0,0,0,4},
    {1,0,0,0,0,0,1,4,0,0,0,0,0,0,4},
    {1,0,0,2,0,0,1,4,0,3,3,3,3,0,4},
    {1,0,0,2,0,0,1,4,0,3,3,3,3,0,4},
    {1,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
    {1,1,1,1,1,1,1,4,4,4,4,4,4,4,4}
  };
}
    