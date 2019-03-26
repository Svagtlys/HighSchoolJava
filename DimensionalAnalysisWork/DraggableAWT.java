import java.awt.*;

public interface DraggableAWT{
  
  //Get pointer's x, y
  //Keep relative distance the same by sending the object new x, y according to pointer x, y
  
  public void startDrag(int pointerX, int pointerY);
  
  public void newPos(int pointerX, int pointerY);
  
  public void setPos(int x, int y);
  
  public Rectangle getRect();
  
  public void goBackToOrig(boolean b);
  
  public void paint(Graphics g);
  
  public boolean canMove();
  
  public int getInd();
  
  public void reset();
}