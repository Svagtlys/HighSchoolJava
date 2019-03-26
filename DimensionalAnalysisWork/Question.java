import java.awt.*;
import java.util.*;

public class Question{
  
  private final String ansVal;
  private final String question;
  private final String firstAns, ansUnit;
  private final ArrayList<DragEntry> myEntries;
  private String ansString = "";
  private int enderX = -1;
  
  public Question(String ques, String fAn, String aVal, String aU, ArrayList<DragEntry> ents){
    
    question = ques;
    firstAns = fAn;
    ansVal = aVal;
    ansUnit = aU;
    myEntries = ents;
    
  }
  
  public Point findMatch(int x, int y, DraggableAWT d){
    //System.err.println("Testing");
    for(DragEntry e : myEntries){
      if(! e.getNewPoint(x, y, d).equals(new Point(-1, -1))){
        return e.getNewPoint(x, y, d);
      }
    }
    return new Point(-1, -1);
  }
  
  public void paint(Graphics g){
    g.setColor(Color.WHITE);
    int xLoc = 423;
    int yLoc = 331;
    int width = xLoc + 75 + (myEntries.size()/2)*77;
    int height = yLoc;
    Graphics2D g2 = (Graphics2D)g;
    g2.setStroke(new BasicStroke(2));
    
    //First line
    //g2.drawLine(xLoc + 75, yLoc-25, xLoc + 75, yLoc + 27);
    
    g.drawString(question, xLoc, yLoc - 35);
    
    g.drawString(firstAns, xLoc + 8, yLoc - 7);
    
    for(int i = 0; i < myEntries.size()/2; i++){
      g2.drawLine(xLoc + (75*(i+1)) + (2*i), yLoc-25, xLoc + (75*(i+1)) + (2*i), yLoc + 27);
    }
    
    int endX = xLoc + (75*((myEntries.size()/2) + 1)) + (2*(myEntries.size()/2));
    
    g.drawString("=", endX + 15, yLoc+2);
    
    //Middle line
    g2.drawLine(xLoc, yLoc, width, height);
    for(DragEntry e : myEntries)
      e.paint(g);
    
    enderX = endX + 30;
    
    g.setColor(Color.GRAY);
    g.fillRect(enderX, 317, 75, 25);
    
    g.setColor(Color.WHITE);
    g.drawString(ansString,enderX + 7, 333);
    g.drawString(ansUnit, enderX + 82, 333);
    
  }
  
  public Rectangle getRect(){
    
    return new Rectangle(enderX, 317, 75, 25);
    
  }
  
  public void setAnswer(String s){
    ansString = s;
  }
  
  public void appendAnswer(char s){
    ansString = ansString.concat(Character.toString(s));
  }
  
  public void deleteOne(){
    ansString = ansString.substring(0, ansString.length()-1);
  }
  
  public boolean isCorrect(){
    
    for(DragEntry e : myEntries){
      if(! e.isAnswered())
        return false;
    }
    
    if(! ansString.equalsIgnoreCase(ansVal)){
      return false;
    }
    
    return true;
    
  }
  
  public void reset(){
    for(DragEntry e : myEntries)
      e.clear();
  }
  
}