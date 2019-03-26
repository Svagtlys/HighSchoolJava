import java.awt.*;
import java.util.*;

public class DimensionalText implements DraggableAWT, Comparable<DimensionalText>{
  
  public static final String KGRAM = "kg", GRAM = "g", CGRAM = "cg", MGRAM = "mg", POUND = "lb", OUNCE = "oz"; //weights
  public static final String KLITER = "kL", LITER = "L", DLITER = "dL", CLITER = "cL", MLITER = "mL", GALLON = "gallon", QUART = "qt", QUARTS = "qts"; //liquids
  public static final String KMETER = "km", METER = "m", CMETER = "cm", MMETER = "mm", YARD = "yd", FEET = "ft", INCH = "in"; //distances
  public static final String MOLE = "mol", MMOLE = "mmol"; //OTHER
  public static final ArrayList<String> allUnits = new ArrayList<String>();
  private int xLoc, yLoc, width, height;
  private final int origX, origY, ind;
  private String myUnit;
  private double myValue;
  private int myXOff, myYOff;
  private Color myColor;
  private boolean moveable = true;
  
  {
    allUnits.addAll(Arrays.asList(/*BEGIN WEIGHT*/KGRAM, GRAM, CGRAM, MGRAM, POUND, OUNCE, /*BEGIN  LIQUID*/ KLITER, LITER, DLITER, CLITER, MLITER, GALLON, QUART, QUARTS, /*BEGIN DISTANCE*/ KMETER, METER, CMETER, MMETER, YARD, FEET, INCH, /*BEGIN OTHER*/ MOLE, MMOLE));
  }
  
  public DimensionalText(double value, String unit, Color c, int x, int y, int i){
    
    myValue = value;
    myColor = c;
    origX = x;
    origY = y;
    xLoc = x;
    yLoc = y;
    ind =  i;
    
    height = 12;
    width = (value + " " + unit).length()*7;
    
    for(String s : allUnits){
      if(unit.equalsIgnoreCase(s)){
        myUnit = unit;
        break;
      }
    }
    if(myUnit == null)
      throw new RuntimeException("Invalid Unit");
  }
  
  public void paint(Graphics g){
    width = g.getFontMetrics().stringWidth(myValue + " " + myUnit) + 2;
//    g.setColor(Color.GRAY);
//    g.fillRect((int)(getRect().getX()), (int)(getRect().getY()), (int)(getRect().getWidth()), (int)(getRect().getHeight()));
    g.setColor(myColor);
    g.drawString(myValue + " " + myUnit, xLoc, yLoc);
  }
  
  public void startDrag(int pointerX, int pointerY){
    myXOff = xLoc-pointerX;
    myYOff = yLoc-pointerY;
    //System.err.println(myXOff + ", " + myYOff);
  }
  
  public void newPos(int pointerX, int pointerY){
    
    xLoc = pointerX + myXOff;
    yLoc = pointerY + myYOff;
    //System.err.println("THEM: " + pointerX + ", " + pointerY);
    //System.err.println("ME: " + xLoc + ", " + yLoc);
    
  }
  
  public Rectangle getRect(){
    return new Rectangle(xLoc, yLoc-10, width, height);
  }
  
  public void goBackToOrig(boolean b){
    if(b == true){
      xLoc = origX;
      yLoc = origY;
    }
  }
  
  public int compareTo(DimensionalText that){
    if(this.myUnit.equalsIgnoreCase(that.myUnit)){
      if(this.myValue == that.myValue)
        return 0;
      else return -1;
    }
    else return -1;
  }
  
  public int getInd(){
    return ind;
  }
  
  public boolean canMove(){
    
    return moveable;
    
  }
  
  public void setPos(int x, int y){
    xLoc = x;
    yLoc = y;
    moveable = false;
  }
  
  public void reset(){
    moveable = true;
    goBackToOrig(true);
  }
  
}