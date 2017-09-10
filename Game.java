import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.DataBufferInt;
public class Game extends JFrame implements Runnable{
 BufferedImage img;
 BufferedImage img1;
 private static final long serialVersionUID = 1L;
 public int mapWidth = 15;
 public int mapHeight = 15;
 private Thread thread;
 private boolean running;
 private BufferedImage image;
 public int[] pixels;
 public ArrayList<Texture> textures;
 public Camera camera;
 public int blasterammolevel = 100;
 public Screen screen;
 //map layout
 public static int[][] map =
  {
   {1,1,1,1,1,1,1,1,2,2,2,2,2,2,2},
   {1,0,0,0,0,0,0,0,2,0,0,0,0,0,2},
   {1,0,3,3,3,3,3,0,0,0,0,0,0,0,2},
   {1,0,3,0,0,0,3,0,2,0,0,0,0,0,2},
   {1,0,3,0,0,0,3,0,2,2,2,0,2,2,2},
   {1,0,3,0,0,0,3,0,2,0,0,0,0,0,2},
   {1,0,3,3,0,3,3,0,2,0,0,0,0,0,2},
   {1,0,0,0,0,0,0,0,2,0,0,0,0,0,2},
   {1,1,1,1,1,1,1,1,4,4,4,0,4,4,4},
   {1,0,0,0,0,0,1,4,0,0,0,0,0,0,4},
   {1,0,0,0,0,0,1,4,0,0,0,0,0,0,4},
   {1,0,0,0,0,0,1,4,0,3,3,3,3,0,4},
   {1,0,0,0,0,0,1,4,0,3,3,3,3,0,4},
   {1,0,0,0,0,0,0,0,0,0,0,0,0,0,4},
   {1,1,1,1,1,1,1,4,4,4,4,4,4,4,4}
  };
 
 //make sure all resolution values are the same
 public Game() {
  thread = new Thread(this);
  image = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_RGB);
  pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
  textures = new ArrayList<Texture>();
  textures.add(Texture.metal);
  textures.add(Texture.window);
  textures.add(Texture.console);
  textures.add(Texture.vent);
  camera = new Camera(4.5, 4.5, 1, 0, 0, -.90);
  screen = new Screen(map, mapWidth, mapHeight, textures, 1280, 720);
  addKeyListener(camera);
  setSize(1280, 720);
  setResizable(false);
  setTitle("Java 3D Engine");
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBackground(Color.black);
  setLocationRelativeTo(null);
  setVisible(true);
  //loads blaster sprites
  try {
      img = ImageIO.read(new File("res/gun-basic.png"));
      img1 = ImageIO.read(new File("res/gun-shoot.png"));
    }
    catch (IOException e) {}
  start();
 }
 
 private synchronized void start() {
  running = true;
  thread.start();
 }
 
 public synchronized void stop() {
  running = false;
  try {
   thread.join();
  } catch(InterruptedException e) {
   e.printStackTrace();
  }
 }

 //main renderer
 public void render() {
  BufferStrategy bs = getBufferStrategy();
  if(bs == null) {
   createBufferStrategy(3);
   return;
  }
  Graphics g = bs.getDrawGraphics();
  g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
  //spaceblaster render
  g.drawImage(img, 478, 417, 325, 300, null);
  g.setColor(Color.BLUE);
  g.drawRect(1039, 624, 176, 31);
  g.setColor(Color.GREEN);
  g.fillRect(1040, 625, 175, 30);
  g.setColor(Color.BLUE);
  g.drawString("BLASTER AMMO", 1050, 645);
  bs.show();
 }

 //run method
 public void run() {
  long lastTime = System.nanoTime();
  //60fps
  final double ns = 1000000000.0 / 60.0;
  double delta = 0;
  requestFocus();
  while(running) {
   long now = System.nanoTime();
   delta = delta + ((now-lastTime) / ns);
   lastTime = now;
   while (delta >= 1)
   {
    //handles all of the logic restricted time
    screen.update(camera, pixels);
    camera.update(map);
    delta--;
   }
   render();//displays to the screen unrestricted time
  }
 }

 //main method
 public static void main(String [] args) {
    Game game = new Game();
}
}