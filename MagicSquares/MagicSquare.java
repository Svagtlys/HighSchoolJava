import java.awt.*;

public class MagicSquare implements Comparable<MagicSquare>{
  
  protected final String UID;
  protected final int SIZE;
  protected final int MAXGUESS;
  protected final int GOAL;
  protected int[][] myChoices;
  protected final boolean[] haveUsed; //0-indexed : subtract 1 from guesses
  
  public MagicSquare(int s){
    
    SIZE = s;
    MAXGUESS = (int)(Math.pow(SIZE, 2));
    GOAL = (SIZE * (SIZE*SIZE + 1))/2;
    UID = generateUID();
    haveUsed = new boolean[MAXGUESS];
    
  }
  
  public void paint(Graphics g, int x, int y){
    
    g.setColor(Color.BLACK);
    g.drawRect(x, y, SIZE*20, SIZE*20);
    
    for(int i = 0; i < SIZE; i++){
      
      g.drawLine(x, y+i*20, x+SIZE*20, y+i*20);
      g.drawLine(x+i*20, y, x+i*20, y+SIZE*20);
      
    }
    
    g.setColor(Color.RED);
    g.drawString(UID, x, y-1);
    
    for(int arrY = 0; arrY < SIZE; arrY++){
      for(int arrX = 0; arrX < SIZE; arrX++){
        g.drawString(""+myChoices[arrY][arrX], x+arrX*20+2, y+arrY*20+18);
      }
    }
    
    if(getDeviation() == 0){
      
      g.setColor(Color.BLUE);
      g.drawOval(x-15, y-15, SIZE*20 + 30, SIZE*20 + 30);
      
    }
    
  }
  
  private static String generateUID(){
    
    String result = "";
    for(int i = 0; i < 8; i++){
      int guess = (int)(Math.random()*26);
      result += (char)(guess+65);
    }
    return result;
  }
  
  public void callGuesses(){
    myChoices = makeGuesses();
  }
  
  //GuessMaker
  protected int[][] makeGuesses(){
    
    int[][] myGuesses = new int[SIZE][SIZE];
    
    for(int y = 0; y < SIZE; y++){
      for(int x = 0; x < SIZE; x++){
        int g = (int)(Math.random()*MAXGUESS) + 1;
        while(haveUsed[g-1] == true)
          g = (int)(Math.random()*MAXGUESS) + 1;
        haveUsed[g-1] = true;
        myGuesses[y][x] = g;
      }
    }
    
    return myGuesses;
    
  }
  
  public int getDeviation(){
    
    if(myChoices != null){
      int[] rows = new int[SIZE];
      int[] columns = new int[SIZE];
      int diagonal1 = 0;
      int diagonal2 = 0;
      for(int y = 0; y < SIZE; y++){
        for(int x = 0; x < SIZE; x++){
          rows[y] += myChoices[y][x];
          columns[x] += myChoices[y][x];
          if(x == y){
            diagonal1 += myChoices[y][x];
          }
          if(x == SIZE-y-1){
            diagonal2 += myChoices[y][x];
          }
        }
      }
      return calculateDeviation(rows, columns, diagonal1, diagonal2);
    }
    else
      throw new RuntimeException("No myGuesses made");
    
  }
  
  protected int calculateDeviation(int[] rows, int[] columns, int diag1, int diag2){
    
    int result = 0;
    
    for(int i : rows){
      result += Math.abs(i - GOAL);
    }
    
    for(int i : columns){
      result += Math.abs(i - GOAL);
    }
    
    result += Math.abs(diag1 - GOAL);
    result += Math.abs(diag2 - GOAL);
    
    result = result/(SIZE*SIZE+2);
    
    return result;
    
  }
  
  public int compareTo(MagicSquare them){
    int result = 0;
    
    try{
      result = Integer.compare(this.getDeviation(), them.getDeviation());
    }catch(Exception e){e.printStackTrace();}
    
    return result;
    
  }
  
  protected int[][] getArray(){
    return myChoices;
  }
  
}