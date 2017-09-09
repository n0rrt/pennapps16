import java.awt.event.KeyEvent;<br>import java.awt.event.KeyListener;
public class Camera implements Keylistener {
  public double xPos, yPos, xDir, yDir, xPlane, yPlane;
  public boolean left, right, forward, back;
  public final double MOVE_SPEED = .08;
  public final double ROTATION_SPEED = .45;
  public Camera(double x, double y, double xd, double yd, double xp, double yp) {
    xPos = x;
    yPos = y;
    xDir = xd;
    yDir = yd;
    xPlane = xp;
    yPlane = yp;
    camera = new Camera(4.5, 4.5, 1, 0, 0, -.66);
    addKeyListener(camera);
  }
  public void keyPressed(KeyEvent key) {
    if((key.getKeyCode() == KeyEvent.VK_LEFT))
      left = true;
    if((key.getKeyCode() == KeyEvent.VK_RIGHT))
      right = true;
    if((key.getKeyCode() == KeyEvent.VK_UP))
      forward = true;
    if((key.getKeyCode() == KeyEvent.VK_DOWN))
      back = true;
  }
  public void keyReleased(KeyEvent key) {
    if((key.getKeyCode() == KeyEvent.VK_LEFT))
      left = false;
    if((key.getKeyCode() == KeyEvent.VK_RIGHT))
      right = false;
    if((key.getKeyCode() == KeyEvent.VK_UP))
      forward = false;
    if((key.getKeyCode() == KeyEvent.VK_DOWN))
      back = false;
  }
}
    
    
  