import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class DimensionalAnalysisFrameVersion extends JFrame implements MouseListener, MouseMotionListener, KeyListener{ //add keylistener
  
  //ADD WRITE IN SECTION
  
  private ArrayList<DraggableAWT> myOptions = new ArrayList<DraggableAWT>();
  private ArrayList<Question> myQuestions = new ArrayList<Question>();
  private int currentQuestion, paintedQuestion;
  private int currentDrag;
  protected BufferedImage vMem;
  protected Graphics gBuffer;
  private boolean typing = false;
  private String completeText = "Unsolved";
  private ArrayList<Boolean> completedQuestions = new ArrayList<Boolean>(Collections.nCopies(21, null));
  
  public DimensionalAnalysisFrameVersion(){
    
    super("Dimensional Analysis");
    
    currentQuestion = 20;
    paintedQuestion = 0;
    
//    completedQuestions.set(0, true);
//    completedQuestions.set(1, false);
//    completedQuestions.set(5, true);
//    completedQuestions.set(8, true);
//    completedQuestions.set(10, false);
//    completedQuestions.set(11, true);
//    completedQuestions.set(20, false);
    
    vMem = new BufferedImage(2000,1000, BufferedImage.TYPE_INT_RGB);
    gBuffer = vMem.getGraphics();
    
    addMouseListener(this);
    addMouseMotionListener(this);
    addKeyListener(this);
    
    //CREATING BASE CONVERSIONS
    myOptions.add(new DimensionalText(4, "qts", Color.WHITE, 10, 65,  0));    //0 4 qts
    myOptions.add(new DimensionalText(1, "gallon", Color.WHITE, 73, 65,  1)); //1 1 gallon
    
    myOptions.add(new DimensionalText(1, "qt", Color.WHITE, 10, 85,  2));     //2 qt
    myOptions.add(new DimensionalText(946, "mL", Color.WHITE, 73, 85,  3));   //3 mL
    
    myOptions.add(new DimensionalText(1, "L", Color.WHITE, 10, 105,  4));      //4 L
    myOptions.add(new DimensionalText(1000, "mL", Color.WHITE, 73, 105,  5));  //5 mL
    
    myOptions.add(new DimensionalText(1, "L", Color.WHITE, 10, 125,  6));      //6 L
    myOptions.add(new DimensionalText(100, "cL", Color.WHITE, 73, 125,  7));   //7 cL
    
    myOptions.add(new DimensionalText(1, "L", Color.WHITE, 10, 145,  8));     //8 L
    myOptions.add(new DimensionalText(10, "dL", Color.WHITE, 73, 145,  9));   //9 dL
    
    myOptions.add(new DimensionalText(1, "kL", Color.WHITE, 10, 165, 10));    //10 kL
    myOptions.add(new DimensionalText(1000, "L", Color.WHITE, 73, 165, 11));  //11 L
    
    myOptions.add(new DimensionalText(1, "lb", Color.WHITE, 10, 185, 12));    //12 lb
    myOptions.add(new DimensionalText(16, "oz", Color.WHITE, 73, 185, 13));   //13 oz
    
    myOptions.add(new DimensionalText(1, "lb", Color.WHITE, 10, 205, 14));    //14 lb
    myOptions.add(new DimensionalText(454, "g", Color.WHITE, 73, 205, 15));   //15 g
    
    myOptions.add(new DimensionalText(1, "g", Color.WHITE, 10, 225, 16));     //16 g
    myOptions.add(new DimensionalText(100, "cg", Color.WHITE, 73, 225, 17));  //17 cg
    
    myOptions.add(new DimensionalText(1, "g", Color.WHITE, 10, 245, 18));     //18 g
    myOptions.add(new DimensionalText(1000, "mg", Color.WHITE, 73, 245, 19)); //19 mg
    
    myOptions.add(new DimensionalText(1, "kg", Color.WHITE, 10, 265, 20));    //20 kg
    myOptions.add(new DimensionalText(1000, "g", Color.WHITE, 73, 265, 21));  //21 g
    
    myOptions.add(new DimensionalText(1, "yd", Color.WHITE, 10, 285, 22));    //22 yd
    myOptions.add(new DimensionalText(3, "ft", Color.WHITE, 73, 285, 23));    //23 ft
    
    myOptions.add(new DimensionalText(1, "ft", Color.WHITE, 10, 305, 24));    //24 ft
    myOptions.add(new DimensionalText(12, "in", Color.WHITE, 73, 305, 25));   //25 in
    
    myOptions.add(new DimensionalText(1, "in", Color.WHITE, 10, 325, 26));    //26 in
    myOptions.add(new DimensionalText(2.54, "cm", Color.WHITE, 73, 325, 27)); //27 cm
    
    myOptions.add(new DimensionalText(1, "m", Color.WHITE, 10, 345, 28));     //28 m
    myOptions.add(new DimensionalText(100, "cm", Color.WHITE, 73, 345, 29));  //29 cm
    
    myOptions.add(new DimensionalText(1, "m", Color.WHITE, 10, 365, 30));     //30 m
    myOptions.add(new DimensionalText(1000, "mm", Color.WHITE, 73, 365, 31)); //31 mm
    
    myOptions.add(new DimensionalText(1, "km", Color.WHITE, 10, 385, 32));    //32 km
    myOptions.add(new DimensionalText(1000, "m", Color.WHITE, 73, 385, 33));  //33 m
    
    myOptions.add(new DimensionalText(1, "mol", Color.WHITE, 10, 405, 34));    //34 mol
    myOptions.add(new DimensionalText(1000, "mmol", Color.WHITE, 73, 405, 35));  //35 mmol
    
    //Ten "One-Step"
    
    ArrayList<DragEntry> t1 = new ArrayList<DragEntry>();
    t1.addAll(Arrays.asList(new DragEntry(21, 500, 332), new DragEntry(20, 500, 305)));
    Question os1 = new Question("How many kg are in 1640g?", "1640 g", "1.64", "kg", t1);
    myQuestions.add(os1);
    
    ArrayList<DragEntry> t2 = new ArrayList<DragEntry>();
    t2.addAll(Arrays.asList(new DragEntry(26, 500, 332), new DragEntry(27, 500, 305)));
    Question os2 = new Question("How many cm are in 19 in?", "19 in", "48.26", "cm", t2);
    myQuestions.add(os2);
    
    ArrayList<DragEntry> t3 = new ArrayList<DragEntry>();
    t3.addAll(Arrays.asList(new DragEntry(13, 500, 332), new DragEntry(12, 500, 305)));
    Question os3 = new Question("How many lb are in 14 oz?", "14 oz", "0.875", "lb", t3);
    myQuestions.add(os3);
    
    ArrayList<DragEntry> t4 = new ArrayList<DragEntry>();
    t4.addAll(Arrays.asList(new DragEntry(19, 500, 332), new DragEntry(18, 500, 305)));
    Question os4 = new Question("How many g are in 16822 mg?", "16822 mg", "16.822", "g", t4);
    myQuestions.add(os4);
    
    ArrayList<DragEntry> t5 = new ArrayList<DragEntry>();
    t5.addAll(Arrays.asList(new DragEntry(2, 500, 332), new DragEntry(3, 500, 305)));
    Question os5 = new Question("4.8 qt is the same as how many mL?", "4.8 qt", "4540.8", "mL", t5);
    myQuestions.add(os5);
    
    ArrayList<DragEntry> t6 = new ArrayList<DragEntry>();
    t6.addAll(Arrays.asList(new DragEntry(34, 500, 332), new DragEntry(35, 500, 305)));
    Question os6 = new Question("How many mmol are in 8.34 mol?", "8.34 mol", "8340", "mmol", t6);
    myQuestions.add(os6);
    
    ArrayList<DragEntry> t7 = new ArrayList<DragEntry>();
    t7.addAll(Arrays.asList(new DragEntry(7, 500, 332), new DragEntry(6, 500, 305)));
    Question os7 = new Question("How many L are in 0.74 cL?", "0.74 cL", "0.0074", "cL", t7);
    myQuestions.add(os7);
    
    ArrayList<DragEntry> t8 = new ArrayList<DragEntry>();
    t8.addAll(Arrays.asList(new DragEntry(23, 500, 332), new DragEntry(22, 500, 305)));
    Question os8 = new Question("15 ft is the same as how many yd?", "15 ft", "5", "yd", t8);
    myQuestions.add(os8);
    
    ArrayList<DragEntry> t9 = new ArrayList<DragEntry>();
    t9.addAll(Arrays.asList(new DragEntry(33, 500, 332), new DragEntry(32, 500, 305)));
    Question os9 = new Question("How many km are in 230 m?", "230 m", "0.23", "km", t9);
    myQuestions.add(os9);
    
    ArrayList<DragEntry> t10 = new ArrayList<DragEntry>();
    t10.addAll(Arrays.asList(new DragEntry(14, 500, 332), new DragEntry(15, 500, 305)));
    Question os10 = new Question("How many g are in 160 lb?", "160 lb", "72640", "g", t10);
    myQuestions.add(os10);
    
    //Ten "Multi-Step" ADD MULTISTEPS
    
    ArrayList<DragEntry> t11 = new ArrayList<DragEntry>();
    t11.addAll(Arrays.asList(new DragEntry(14, 500, 332), new DragEntry(15, 500, 305), new DragEntry(21, 576, 332), new DragEntry(20, 577, 305)));
    Question ms1 = new Question("How many kg are in 18 lb?", "18 lb", "8.172", "kg", t11);
    myQuestions.add(ms1);
    
    ArrayList<DragEntry> t12 = new ArrayList<DragEntry>();
    t12.addAll(Arrays.asList(new DragEntry(24, 500, 332), new DragEntry(25, 500, 305), new DragEntry(26, 576, 332), new DragEntry(27, 576, 305)));
    Question ms2 = new Question("2.7 ft is the same as how many cm?", "2.7 ft", "82.296", "cm", t12);
    myQuestions.add(ms2);
    
    ArrayList<DragEntry> t13 = new ArrayList<DragEntry>();
    t13.addAll(Arrays.asList(new DragEntry(13, 500, 332), new DragEntry(12, 500, 305), new DragEntry(14, 576, 332), new DragEntry(15, 576, 305), new DragEntry(16, 652, 332), new DragEntry(17, 652, 305)));
    Question ms3 = new Question("How many cg are in 0.13 oz?", "0.13 oz", "368.875", "cg", t13);
    myQuestions.add(ms3);
    
    ArrayList<DragEntry> t14 = new ArrayList<DragEntry>();
    t14.addAll(Arrays.asList(new DragEntry(1, 500, 332), new DragEntry(0, 500, 305), new DragEntry(2, 576, 332), new DragEntry(3, 576, 305)));
    Question ms4 = new Question("How many mL are in 3.2 gallons?", "3.2 gallon", "12108.8", "mL", t14);
    myQuestions.add(ms4);
    
    ArrayList<DragEntry> t15 = new ArrayList<DragEntry>();
    t15.addAll(Arrays.asList(new DragEntry(10, 500, 332), new DragEntry(11, 500, 305), new DragEntry(8, 576, 332), new DragEntry(9, 576, 305)));
    Question ms5 = new Question("How many dL are in 2.2 kL?", "2.2 kL", "22000", "dL", t15);
    myQuestions.add(ms5);
    
    ArrayList<DragEntry> t16 = new ArrayList<DragEntry>();
    t16.addAll(Arrays.asList(new DragEntry(22, 500, 332), new DragEntry(23, 500, 305), new DragEntry(24, 576, 332), new DragEntry(25, 576, 305), new DragEntry(26, 652, 332), new DragEntry(27, 652, 305), new DragEntry(29, 728, 332), new DragEntry(28, 728, 305)));
    Question ms6 = new Question("19 yards is the same as how many meters (m)?", "19 yd", "17.3736", "m", t16);
    myQuestions.add(ms6);
    
    ArrayList<DragEntry> t17 = new ArrayList<DragEntry>();
    t17.addAll(Arrays.asList(new DragEntry(7, 500, 332), new DragEntry(6, 500, 305), new DragEntry(4, 576, 332), new DragEntry(5, 576, 305), new DragEntry(3, 652, 332), new DragEntry(2, 652, 305)));
    Question ms7 = new Question("Convert 1892 cL to quarts.", "1892 cL", "20", "qt", t17);
    myQuestions.add(ms7);
    
    ArrayList<DragEntry> t18 = new ArrayList<DragEntry>();
    t18.addAll(Arrays.asList(new DragEntry(19, 500, 332), new DragEntry(18, 500, 305), new DragEntry(16, 576, 332), new DragEntry(17, 576, 305)));
    Question ms8 = new Question("How many cg are in 0.488 mg?", "0.488 mg", "0.0488", "cg", t18);
    myQuestions.add(ms8);
    
    ArrayList<DragEntry> t19 = new ArrayList<DragEntry>();
    t19.addAll(Arrays.asList(new DragEntry(19, 500, 332), new DragEntry(18, 500, 305), new DragEntry(15, 576, 332), new DragEntry(14, 576, 305), new DragEntry(12, 652, 332), new DragEntry(13, 652, 305)));
    Question ms9 = new Question("How many oz are in 9080 mg?", "9080 mg", "0.32", "oz", t19);
    myQuestions.add(ms9);
    
    ArrayList<DragEntry> t20 = new ArrayList<DragEntry>();
    t20.addAll(Arrays.asList(new DragEntry(31, 500, 332), new DragEntry(30, 500, 305), new DragEntry(28, 576, 332), new DragEntry(29, 576, 305), new DragEntry(27, 652, 332), new DragEntry(26, 652, 305), new DragEntry(25, 728, 332), new DragEntry(24, 728, 305)));
    Question ms10 = new Question("How many feet are in 6.096 mm?", "6.096 mm", "0.02", "ft", t20);
    myQuestions.add(ms10);
    
    
    ArrayList<DragEntry> s1 = new ArrayList<DragEntry>();
    s1.addAll(Arrays.asList(new DragEntry(24, 500, 332), new DragEntry(25, 500, 305)));
    TutorialQuestion tq1 = new TutorialQuestion("How many inches are in 1 foot?", "1 ft", "12", "in", s1);
    myQuestions.add(tq1);
    
    setLayout(new FlowLayout());
    
    reset();
    
    setSize(Toolkit.getDefaultToolkit().getScreenSize());
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // if you want the X button to close the app
  }
  
  public void paint(Graphics g){
    
    if(currentQuestion != paintedQuestion){
      for(DraggableAWT d : myOptions)
        d.goBackToOrig(true);
      completeText = "Unsolved";
    }
    
    paintedQuestion = currentQuestion;
    
    gBuffer.setColor(Color.DARK_GRAY);
    gBuffer.fillRect(0, 30, 2000, 1000);
    
    gBuffer.setColor(Color.BLACK);
    gBuffer.drawRect(0, 30, 175, 415);
    gBuffer.setColor(Color.WHITE);
    gBuffer.drawString("Conversions", 10, 45);
    
    if(currentQuestion/10 < 1)
      gBuffer.drawString("One Step", 239, 45);
    else if(currentQuestion/10 < 2)
      gBuffer.drawString("Multi Step", 235, 45);
    else
      gBuffer.drawString("Tutorial", 250, 45);
    
    gBuffer.drawString("Question #" + (currentQuestion%10 + 1), 300, 45);
    
    //BASE CONVERSIONS (kg-lb, kg-g, lb-oz, g-ml, etc)
    
    gBuffer.drawString("=", 58, 65);
    gBuffer.drawString("=", 58, 85);
    gBuffer.drawString("=", 58, 105);
    gBuffer.drawString("=", 58, 125);
    gBuffer.drawString("=", 58, 145);
    gBuffer.drawString("=", 58, 165);
    gBuffer.drawString("=", 58, 185);
    gBuffer.drawString("=", 58, 205);
    gBuffer.drawString("=", 58, 225);
    gBuffer.drawString("=", 58, 245);
    gBuffer.drawString("=", 58, 265);
    gBuffer.drawString("=", 58, 285);
    gBuffer.drawString("=", 58, 305);
    gBuffer.drawString("=", 58, 325);
    gBuffer.drawString("=", 58, 345);
    gBuffer.drawString("=", 58, 365);
    gBuffer.drawString("=", 58, 385);
    gBuffer.drawString("=", 58, 405);
    
    gBuffer.drawString(completeText, 400, 170);
    
    myQuestions.get(currentQuestion).paint(gBuffer);
    
    for(DraggableAWT d : myOptions){
      
      d.paint(gBuffer);
      
    }
    
    //Buttons
    
    gBuffer.setColor(Color.RED);
    gBuffer.fillRect(400, 230, 75, 25); //Submit button
    gBuffer.setColor(Color.BLACK);
    gBuffer.drawString("Submit", 405, 248);
    
    //ONESTEPS
    
    gBuffer.fillRect(1100, 80, 75, 25);
    gBuffer.fillRect(1200, 80, 78, 25);
    gBuffer.fillRect(1100, 110, 75, 25);
    gBuffer.fillRect(1200, 110, 78, 25);
    gBuffer.fillRect(1100, 140, 75, 25);
    gBuffer.fillRect(1200, 140, 78, 25);
    gBuffer.fillRect(1100, 170, 75, 25);
    gBuffer.fillRect(1200, 170, 78, 25);
    gBuffer.fillRect(1100, 200, 75, 25);
    gBuffer.fillRect(1200, 200, 78, 25);
    
    //MULTISTEPS
    
    gBuffer.fillRect(1100, 260, 75, 25);
    gBuffer.fillRect(1200, 260, 78, 25);
    gBuffer.fillRect(1100, 290, 75, 25);
    gBuffer.fillRect(1200, 290, 78, 25);
    gBuffer.fillRect(1100, 320, 75, 25);
    gBuffer.fillRect(1200, 320, 78, 25);
    gBuffer.fillRect(1100, 350, 75, 25);
    gBuffer.fillRect(1200, 350, 78, 25);
    gBuffer.fillRect(1100, 380, 75, 25);
    gBuffer.fillRect(1200, 380, 78, 25);
    
    
    gBuffer.fillRect(1100, 440, 75, 25);
    
    //WORDS
    
    gBuffer.setColor(Color.WHITE);
    
    gBuffer.drawString("One Step Questions", 1125, 70);
    
    gBuffer.drawString("Question 1", 1104, 97);
    gBuffer.drawString("Question 2", 1204, 97);
    gBuffer.drawString("Question 3", 1104, 127);
    gBuffer.drawString("Question 4", 1204, 127);
    gBuffer.drawString("Question 5", 1104, 157);
    gBuffer.drawString("Question 6", 1204, 157);
    gBuffer.drawString("Question 7", 1104, 187);
    gBuffer.drawString("Question 8", 1204, 187);
    gBuffer.drawString("Question 9", 1104, 217);
    gBuffer.drawString("Question 10", 1201, 217);
    
    gBuffer.drawString("Multi Step Questions", 1120, 250);
    
    gBuffer.drawString("Question 1", 1104, 277);
    gBuffer.drawString("Question 2", 1204, 277);
    gBuffer.drawString("Question 3", 1104, 307);
    gBuffer.drawString("Question 4", 1204, 307);
    gBuffer.drawString("Question 5", 1104, 337);
    gBuffer.drawString("Question 6", 1204, 337);
    gBuffer.drawString("Question 7", 1104, 367);
    gBuffer.drawString("Question 8", 1204, 367);
    gBuffer.drawString("Question 9", 1104, 397);
    gBuffer.drawString("Question 10", 1201, 397);
    
    gBuffer.drawString("Tutorial Question", 1130, 430);
    gBuffer.drawString("Question 1", 1104, 457);
    
    if(typing == true){
      Rectangle myR = myQuestions.get(currentQuestion).getRect();
      gBuffer.setColor(Color.YELLOW);
      gBuffer.drawRect((int)(myR.getX()-1), (int)(myR.getY()-1), (int)(myR.getWidth()+2), (int)(myR.getHeight()+2));
    }
    
    for(int i = 0; i < myQuestions.size(); i++){ //CHECKING OFF COMPLETED QUESTIONS
      
      if(completedQuestions.get(i) == null){
        continue;
      }
      
      int x = (i%2)*102 + 1177;
      int y = (i/2)*30 + 50;
      if(i > 9)
        y += 30;
      if(i > 19)
        y += 30;
      
      if(completedQuestions.get(i).booleanValue() == true){
        gBuffer.setColor(Color.GREEN);
        gBuffer.drawLine(x+2, y+45, x+10, y+53);
        gBuffer.drawLine(x+10, y+53, x+22, y+30);
      }
      else if(completedQuestions.get(i).booleanValue() == false){
        gBuffer.setColor(Color.RED);
        gBuffer.drawLine(x+2, y+32, x+22, y+52);
        gBuffer.drawLine(x+2, y+52, x+22, y+32);
      }
      
    }
    
    g.drawImage(vMem, 0, 0, this);
    
  }
  
  
  
  public void mousePressed(MouseEvent e){//submit button
     
    int index = 0;
    
    //System.err.println("Clicked");
    boolean solved = false;
    for(DraggableAWT d : myOptions){//Find the VISIBLE draggable awt in list which is in that area
      //System.err.println("X: " + d.getRect().getX() + ", Y: " + d.getRect().getY() + ", W: "  + d.getRect().getWidth() +", H: "  + d.getRect().getHeight());
      //System.err.println("Does it contain " + e.getX() + ", " + e.getY() + "?");
      if(d.getRect().contains(e.getX(), e.getY()) && d.canMove()){
        //System.err.println("Found match");
        myOptions.get(index).startDrag(e.getX(), e.getY());
        currentDrag = index;
        solved = true;
        break;
      }
      else
        index++;
    }
    
    if(solved == false){
      Rectangle bounds = new Rectangle(400, 230, 75, 25);
      if(bounds.contains(e.getX(), e.getY())){
        solved = true;
        if(myQuestions.get(currentQuestion).isCorrect()){
          completeText = "Completed!";
          completedQuestions.set(currentQuestion, true);
        }
        else{
          completeText = "Incorrect";
          completedQuestions.set(currentQuestion, false);
        }
      }
    }
    
    if(solved == false){
      //ONESTEPS
      
      Rectangle os1 = new Rectangle(1100, 80, 75, 25);
      Rectangle os2 = new Rectangle(1200, 80, 78, 25);
      Rectangle os3 = new Rectangle(1100, 110, 75, 25);
      Rectangle os4 = new Rectangle(1200, 110, 78, 25);
      Rectangle os5 = new Rectangle(1100, 140, 75, 25);
      Rectangle os6 = new Rectangle(1200, 140, 78, 25);
      Rectangle os7 = new Rectangle(1100, 170, 75, 25);
      Rectangle os8 = new Rectangle(1200, 170, 78, 25);
      Rectangle os9 = new Rectangle(1100, 200, 75, 25);
      Rectangle os10 = new Rectangle(1200, 200, 78, 25);
      
      //MULTISTEPS
      
      Rectangle ms1 = new Rectangle(1100, 260, 75, 25);
      Rectangle ms2 = new Rectangle(1200, 260, 78, 25);
      Rectangle ms3 = new Rectangle(1100, 290, 75, 25);
      Rectangle ms4 = new Rectangle(1200, 290, 78, 25);
      Rectangle ms5 = new Rectangle(1100, 320, 75, 25);
      Rectangle ms6 = new Rectangle(1200, 320, 78, 25);
      Rectangle ms7 = new Rectangle(1100, 350, 75, 25);
      Rectangle ms8 = new Rectangle(1200, 350, 78, 25);
      Rectangle ms9 = new Rectangle(1100, 380, 75, 25);
      Rectangle ms10 = new Rectangle(1200, 380, 78, 25);
      
      Rectangle tq1 = new Rectangle(1100, 440, 75, 25);
      
      if(os1.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 0;
        solved = true;
      }
      else if(os2.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 1;
        solved = true;
      }
      else if(os3.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 2;
        solved = true;
      }
      else if(os4.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 3;
        solved = true;
      }
      else if(os5.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 4;
        solved = true;
      }
      else if(os6.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 5;
        solved = true;
      }
      else if(os7.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 6;
        solved = true;
      }
      else if(os8.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 7;
        solved = true;
      }
      else if(os9.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 8;
        solved = true;
      }
      else if(os10.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 9;
        solved = true;
      }
      else if(ms1.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 10;
        solved = true;
      }
      else if(ms2.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 11;
        solved = true;
      }
      else if(ms3.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 12;
        solved = true;
      }
      else if(ms4.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 13;
        solved = true;
      }
      else if(ms5.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 14;
        solved = true;
      }
      else if(ms6.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 15;
        solved = true;
      }
      else if(ms7.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 16;
        solved = true;
      }
      else if(ms8.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 17;
        solved = true;
      }
      else if(ms9.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 18;
        solved = true;
      }
      else if(ms10.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 19;
        solved = true;
      }
      else if(tq1.contains(e.getX(), e.getY())){
        myQuestions.get(currentQuestion).reset();
        currentQuestion = 20;
        solved = true;
      }
      
      if(solved){
        for(DraggableAWT w : myOptions)
          w.reset();
      }
    }
    
    repaint();
  } //See which unit is being dragged
  public void mouseReleased(MouseEvent e){
    
    if(currentDrag != -1){
      myOptions.get(currentDrag).newPos(e.getX(), e.getY());
      Point result = myQuestions.get(currentQuestion).findMatch(e.getX(), e.getY(), myOptions.get(currentDrag));
      if(! result.equals(new Point(-1, -1))){
        myOptions.get(currentDrag).goBackToOrig(false); //Change this to false if in the correct spot!
        myOptions.get(currentDrag).setPos(result.x, result.y);
      }
      else{
        myOptions.get(currentDrag).goBackToOrig(true);
      }
    }
    currentDrag = -1;
    repaint();
  } //See where unit is dropped
  public void mouseDragged(MouseEvent e){
    //System.err.println("You're dragging!");
    
    if(currentDrag != -1){
      //System.err.println("AND WE'RE MOVING!!!!");
      myOptions.get(currentDrag).newPos(e.getX(), e.getY());
    }
    repaint();
  } //See where mouse is during drag
  
  
  
  public void mouseClicked(MouseEvent e){}
  public void mouseMoved(MouseEvent e){
    Rectangle bounds = myQuestions.get(currentQuestion).getRect();
    if(bounds.contains(e.getX(), e.getY())){
      //System.err.println("Typing");
      typing = true;
    }
    else{
      typing = false;
    }
    repaint();
  }
  public void mouseEntered(MouseEvent e){}
  public void mouseExited(MouseEvent e){}
    
  public void update(Graphics g){
    paint(g);
  }
  
  public void keyPressed(KeyEvent e){
    //System.err.println("WHAT");
  if(typing == true){
    //System.err.println("I hear you");
    if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
      myQuestions.get(currentQuestion).deleteOne();
    else if(e.getKeyCode() == KeyEvent.VK_ENTER){
      if(myQuestions.get(currentQuestion).isCorrect()){
        completeText = "Completed!";
        completedQuestions.set(currentQuestion, true);
      }
      else{
        completeText = "Incorrect";
        completedQuestions.set(currentQuestion, false);
      }
    }
    else
      myQuestions.get(currentQuestion).appendAnswer(e.getKeyChar());
  }
  repaint();
  }
  public void keyReleased(KeyEvent e){}
  public void keyTyped(KeyEvent e){}
  
  public void reset(){
    
    removeAll();
    
    ImageIcon picture = new ImageIcon(vMem);
    JLabel picLabel = new JLabel(picture);
    
    picLabel.setPreferredSize(new Dimension((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-500)));
    
    add(picLabel);
    
  }
  
  public static void main(String[] args){
    DimensionalAnalysisFrameVersion dafv = new DimensionalAnalysisFrameVersion();
  }
  
}