import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.util.*;

public class AllSquaredAway extends Applet implements KeyListener, Runnable{
  
//  Hit ENTER to turn off auto-run
//  Hit SPACE to step-by-step with auto-run OFF
//  Blue numbers are mutations
//  Deviation means Math.abs(ActualSum-GoalSum)
  
  protected Image vMem;
  protected Graphics gBuffer;
  protected int generation = 0;
  protected ArrayList<MagicSquare> mySquares = new ArrayList<MagicSquare>();
  protected ArrayList<MagicSquare> myParents = new ArrayList<MagicSquare>();
  protected MagicSquare randomComp;
  protected boolean compRandom, useGenMax;
  protected int numPerGen, genMax, numParents, squareSize, averageDeviation, genPaint;
  protected boolean running = true;
  
  public void init(){
    
    useGenMax = true;           //SET USE GEN MAX
    numPerGen = 27;             //SET SQUARES PER GEN
    genMax = 5000;              //SET GEN MAX
    numParents = 8;             //SET NUM PARENTS
    squareSize = 4;             //SET SQUARE SIZE
    compRandom = false;         //SET USE RANDOM SQUARE
    genPaint = 50;              //SET WHICH %GEN TO PAINT
    
    if(compRandom)
      randomComp = new MagicSquare(squareSize);
    
    vMem = createImage(2000, 1000);
    gBuffer = vMem.getGraphics();
    
    addKeyListener(this);
    new Thread(this).start();
  }
  
  public void keyReleased(KeyEvent e){
    
    int code = e.getKeyCode();
    
    switch(code){
      case KeyEvent.VK_SPACE : if(! useGenMax || (useGenMax && generation < genMax))
                                 step(); break;
      case KeyEvent.VK_ENTER : running = !running; break;
    }
    repaint();
  }
  
  public void paint(Graphics g){
    
    gBuffer.setColor(Color.WHITE);
    gBuffer.fillRect(0, 0, 2000, 1000);
    
    gBuffer.setColor(Color.RED);
    gBuffer.drawString("Generation " + generation, 15, 45);
    gBuffer.drawString("Goal = " + ((squareSize*(squareSize*squareSize+1))/2), 175, 45);
    gBuffer.drawString("Average Deviation from goal = " + averageDeviation, 275, 45);
    
    gBuffer.setColor(Color.BLACK);
    
    int x = 15;
    int y = 65;
    
    for(int i = 0; i < mySquares.size(); i++){
      
      mySquares.get(i).paint(gBuffer, x, y);
      
      x += squareSize * 25;
      if(x + squareSize*25 > 1500){
        y += squareSize*25;
        x = 15;
      }
    }
    if(compRandom)
      randomComp.paint(gBuffer, 350, y);
    
    g.drawImage(vMem, 0, 0, this);
    
  }
  
  public void update(Graphics g){
    paint(g);
  }
  
  public void step(){
    
    generation++;
    
    ArrayList<MagicSquare> newSquares = new ArrayList<MagicSquare>();
    
    if(myParents.isEmpty()){
      
      for(int i = 0; i < numPerGen; i++){
        newSquares.add(new MagicSquare(squareSize));
      }
      
    }
    
    else{
      
      for(int i = 0; i < numPerGen; i++){
        int mom = 0;
        int dad = 0;
        
        do{
        mom = (int)(Math.random()*numParents);
        dad = (int)(Math.random()*numParents);
        }while(mom == dad);
        
        if(myParents.get(mom) == null || myParents.get(dad) == null)
          System.err.println("Something has gone horribly wrong");
        
        newSquares.add(new BabySquare(squareSize, myParents.get(mom), myParents.get(dad)));
        
      }
      
    }
    
    for(MagicSquare ms : newSquares){
      ms.callGuesses();
      if(! myParents.isEmpty())
        ((BabySquare)ms).callMutate();
    }
    
    if(compRandom){
      MagicSquare random = new MagicSquare(squareSize);
      random.callGuesses();
      
      randomComp = random;
    }
    mySquares = newSquares;
    
    findParents();
    
    getAverageDeviation();
    
    //repaint();
  }
  
  public void findParents(){
    
    if(! mySquares.isEmpty()){
      ArrayList<MagicSquare> myPar = mySquares;
      Collections.sort(myPar);
      
      myParents.clear();
      
      for(int i = 0; i < numParents; i++){
        myParents.add(myPar.get(i));
      }
    }
  }
  
  public void getAverageDeviation(){
    
    int result = 0;
    
    for(MagicSquare ms : mySquares){
      if(ms.getDeviation() == 0){
        running = false;
        repaint();
      }
      result += ms.getDeviation();
    }
    if(compRandom){
      if(randomComp.getDeviation() == 0){
        running = false;
        repaint();
      }
    }
    averageDeviation = result/mySquares.size();
    
  }
  
  public void run(){
    
    while(true){
      
      while(running){
        if(! useGenMax || (useGenMax && generation < genMax))
        step();
        
        if(generation%genPaint == 0)
          repaint();
        
        try{
          Thread.sleep(1);
        }catch(Exception e){}
        
        
        
      }
      try{
          Thread.sleep(30);
        }catch(Exception e){}
    }
    
  }
  
  public void keyPressed(KeyEvent e){}
  public void keyTyped(KeyEvent e){}
  
}