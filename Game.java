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
  public ArrayList<Texture> textures;
  public Camera camera;
  public Screen screen;
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
  //constructor
  public Game() {
    thread = new Thread(this);
    image = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_RGB);
    pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    textures = new ArrayList<Texture>();
    textures.add(Texture.metal);
    textures.add(Texture.window);
    textures.add(Texture.vent);
    textures.add(Texture.console);
    camera = new Camera(4.5, 4.5, 1, 0, 0, -.95);
    screen = new Screen(map, mapWidth, mapHeight, textures, 1280, 720);
    addKeyListener(camera);

    setSize(1280, 720);
    setResizable(false);
    setTitle("Java 3D Engine");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBackground(Color.black);
    setLocationRelativeTo(null);
    setVisible(true);
    start();
  }
  //start and stop methods
  private synchronized void start() {
    running = true;
    thread.start();
  }
  public synchronized void stop() {
    running = false;
    try {
      thread.join();
    }
    catch(InterruptedException e) {
      e.printStackTrace();
    }
  }
  //generates graphics
  public void render() {
    BufferStrategy bs = getBufferStrategy();
    if(bs == null) {
      createBufferStrategy(3);
      return;
    }
    Graphics g = bs.getDrawGraphics();
    g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    bs.show();
  }
  public void run() {
    long lastTime = System.nanoTime();
    //60fps
    final double ns = 1000000000.0/60.0;
    double delta = 0;
    requestFocus();
    while(running) {
      long now = System.nanoTime();
      delta = delta + ((now-lastTime) / ns);
      lastTime = now;
      while(delta >= 1) {
        screen.update(camera, pixels);
        camera.update(map);
        delta--;
      }
      render();
    }
  }
  public static void main(String [] args) {
    Game game = new Game();
  }
}
