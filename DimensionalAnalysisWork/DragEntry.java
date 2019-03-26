import java.awt.*;

public class DragEntry{
  
  private final int ansInd;
  private final int xLoc, yLoc, width = 75, height = 25;
  private boolean isFilled;
  
  public DragEntry(int aI, int x, int y){
    ansInd = aI;
    xLoc = x;
    yLoc = y;
  }
  
  private boolean isMatch(DraggableAWT b){
    if(this.ansInd == b.getInd())
      return true;
    else
      return false;
  }
  
  private boolean contains(int x, int y){
    if(getRect().contains(x, y))
      return true;
    return false;
  }
  
  public Point getNewPoint(int x, int y, DraggableAWT d){
    if(contains(x, y) && isMatch(d)){
      isFilled = true;
      return new Point(xLoc+2, yLoc+18);
    }
    else return new Point(-1, -1);
  }
  
  public Rectangle getRect(){
    return new Rectangle(xLoc, yLoc, width, height);
  }
  
  public void paint(Graphics g){
    g.setColor(Color.GRAY);
    g.fillRect(xLoc, yLoc, width, height);
  }
  
  public boolean isAnswered(){
    return isFilled;
  }
  
  public void clear(){
    isFilled = false;
  }
  
}