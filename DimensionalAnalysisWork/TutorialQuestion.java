import java.awt.*;
import java.util.*;

public class TutorialQuestion extends Question{
  
  public TutorialQuestion(String ques, String fAn, String aVal, String aU, ArrayList<DragEntry> ents){
    super(ques, fAn, aVal, aU, ents);
  }
  
  public void paint(Graphics g){
    super.paint(g);
    //Paint arrows and directions too
    
    g.setColor(Color.ORANGE);
    g.drawOval(4, 288, 45, 25);
    g.drawLine(32, 310, 510, 345);
    g.drawLine(510, 345, 500, 335);
    g.drawLine(510, 345, 500, 355);
    g.drawString("Step 1: Drag to bottom", 290, 350);
    
    g.setColor(Color.RED);
    g.drawOval(72, 288, 45, 25);
    g.drawLine(100, 310, 510, 320);
    g.drawLine(510, 320, 500, 310);
    g.drawLine(510, 320, 500, 330);
    g.drawString("Step 2: Drag to top", 290, 310);
    
    g.setColor(Color.YELLOW);
    g.drawString("Step 3: Calculate answer", 350, 430);
    
    g.setColor(Color.GREEN);
    g.drawString("Step 4: Hover mouse over box and type answer", 600, 280);
    g.drawString("If the answer is a decimal, type", 710, 305);
    g.drawString("a 0 before the decimal point", 710, 330);
    g.drawLine(650, 280, 650, 320);
    g.drawLine(650, 320, 640, 310);
    g.drawLine(650, 320, 660, 310);
    
    g.setColor(Color.CYAN);
    g.drawString("Step 5: Hit \"Submit\"", 500, 205);
    g.drawLine(500, 205, 475, 225);
    g.drawLine(475, 225, 477, 214);
    g.drawLine(475, 225, 485, 227);
    
  }
  
}