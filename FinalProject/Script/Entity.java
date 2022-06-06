import java.awt.Graphics;

interface Entity {
   public void movement(int pX, int pY, double delta);

   public void attack();

   public int checkHealth();

   public int getX();
   
   public int getY();

   public void setX(int x);
   
   public void setY(int y);

   public void draw(Graphics sceneBuffer);
}