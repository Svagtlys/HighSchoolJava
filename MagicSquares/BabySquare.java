import java.awt.*;

public class BabySquare extends MagicSquare{
  
  private final MagicSquare MOM, DAD;
  private boolean[][] mutated;
  
  public BabySquare(int s, MagicSquare m, MagicSquare d){
    
    super(s);
    
    MOM = m;
    DAD = d;
    mutated = new boolean[SIZE][SIZE];
    
  }
  
  public void paint(Graphics g, int x, int y){
    
    super.paint(g, x, y);
    
    g.setColor(Color.blue);
    for(int arrY = 0; arrY < SIZE; arrY++){
      for(int arrX = 0; arrX < SIZE; arrX++){
        if(mutated[arrY][arrX] == true)
          g.drawString(""+myChoices[arrY][arrX], x+arrX*20+2, y+arrY*20+18);
      }
    }
    
  }
  
  protected int[][] makeGuesses(){
    
    int[][] myGuesses = new int[SIZE][SIZE];
    
    myGuesses = useMom(myGuesses);
    
    myGuesses = useDad(myGuesses);
    
    return myGuesses;
    
  }
  
  private int[][] useMom(int[][] base){
    
    //System.err.println("Using MOM");
    
    if(MOM == null)
      System.err.println("No MOM here");
    if(MOM.getArray() == null)
      System.err.println("No Array here");
    
    double guess = Math.random();
    
    if(guess < .25){ //TOP HALF
      for(int y = 0; y <= SIZE/2; y++){
        for(int x = 0; x < SIZE; x++){
          int g = MOM.getArray()[y][x];
          base[y][x] = g;
          haveUsed[g - 1] = true;
        }
      }
    }
    
    if(guess < .5){  //LEFT HALF
      for(int y = 0; y < SIZE; y++){
        for(int x = 0; x <= SIZE/2; x++){
          int g = MOM.getArray()[y][x];
          base[y][x] = g;
          haveUsed[g - 1] = true;
        }
      }
    }
    
    if(guess < .75){ //BOTTOM HALF
      for(int y = SIZE/2+1; y < SIZE; y++){
        for(int x = 0; x < SIZE; x++){
          int g = MOM.getArray()[y][x];
          base[y][x] = g;
          haveUsed[g - 1] = true;
        }
      }
    }
      
    else{            //RIGHT HALF
      for(int y = 0; y < SIZE; y++){
        for(int x = SIZE/2+1; x < SIZE; x++){
          int g = MOM.getArray()[y][x];
          base[y][x] = g;
          haveUsed[g - 1] = true;
        }
      }
    }
    return base;
  }
  
  private void checkThis(int[][] checkMe, String str){
    
    boolean bad = false;
    
    for(int y = 0; y < SIZE; y++){
      for(int x = 0; x < SIZE; x++){
        if(checkMe[y][x] == 0){
          bad = true;
          break;
        }
      }
    }
    
    if(bad)
      System.err.println("ISSUES in " + str);
    
  }
  
  private int[][] useDad(int[][] base){
    
    //System.err.println("Using DAD");
    
    for(int y = 0; y < SIZE; y++){
      for(int x = 0; x < SIZE; x++){
        if(base[y][x] == 0){
          base[y][x] = findUnused();
          haveUsed[base[y][x]-1] = true;
        }
      }
    }
    
    return base;
  }
  
  private int findUnused(){
    
    for(int y = 0; y < SIZE; y++){
      for(int x = 0; x < SIZE; x++){
        if(haveUsed[DAD.getArray()[y][x]-1] == false)
          return DAD.getArray()[y][x];
      }
    }
    
    System.err.println("I found no unused guys!");
    return 0;
    
  }
  
  protected int[][] mutate(int[][] base){
    
    if(getDeviation() != 0){
      int mutationNum = (int)(Math.random()*SIZE+1);
      while(mutationNum > 0){
        int x1 = (int)(Math.random()*SIZE);
        int y1 = (int)(Math.random()*SIZE);
        int x2 = (int)(Math.random()*SIZE);
        int y2 = (int)(Math.random()*SIZE);
        
        mutated[x1][y1] = true;
        mutated[x2][y2] = true;
        
        int temp = base[y1][x1];
        base[y1][x1] = base[y2][x2];
        base[y2][x2] = temp;
        
        mutationNum--;
      }
    }
    
    return base;
  }
  
  public void callMutate(){
    
    if(getDeviation() != 0)// && getDeviation() <= MOM.getDeviation())
      myChoices = mutate(myChoices);
    
  }
  
}