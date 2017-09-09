import java.util.ArrayList;
import java.awt.Color;

public class Screen{
  public int[][] map;
  public int mapWidth, mapHeight, width, height;
  public ArrayList textures;
  public Screen(int[][] m, ArrayList tex, int w, int h){
    map = m;
    textures = tex;
    width = w;
    height = h;
  }
  public int[] update(Camera camera, int[] pixels){
    for(int n=0; n<pixels.length/2; n++){
      if(pixels[n] != Color.DARK_GRAY.getRGB()){
        pixels[n] = Color.DARK_GRAY.getRGB();
      }
    }
    for(int i=pixels.length/2; i<pixels.length; i++){
      if(pixels[i] != Color.gray.getRGB()){
        pixels[i] = Color.gray.getRGB();
      }
    }
    for(int x=0; x<width; x=x+1){
      double cameraX = 2 * x / (double)(width) -1;
      double rayDirX = camera.xDir + camera.xPlane * cameraX;
      double rayDirY = camera.yDir + camera.yPlane * cameraX;
      //Map position
      int mapX = (int)camera.xPos;
      int mapY = (int)camera.yPos;
      //length of ray from current position to next x or y-side
      double sideDistX;
      double sideDistY;
      //length of ray from one side to next in map
      double deltaDistX = Math.sqrt(1 + (rayDirY*rayDiry) / (rayDirX*rayDirX));
      double deltaDisty = Math.sqrt(1 + (rayDirX*rayDirX) / (rayDirY*rayDirY));
      double perpWallDist;
      //direction to go in x and y
      int stepX, stepY;
      boolean hit = false;
      int side=0;
      if (rayDirY < 0){
        stepX = -1;
        sideDistX = (camera.xPos - mapX) * deltaDistX;
      }
      else{
        stepX = -1;
        
      }
      }
    }
  }
}
