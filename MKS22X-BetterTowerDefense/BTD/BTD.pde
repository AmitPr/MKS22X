World world;
Player player;
public static final int WORLD_WIDTH=20;
public static final int WORLD_HEIGHT=20;
public static int HEIGHT=0;
public int waveNum = 1;
public static int WIDTH=0;
public static final float SELL_RATIO=0.8;
public static final int STARTING_MONEY=1000;
public static final int STARTING_HEALTH=100;
public static int[][] pathFindingMap;
public static final String[] towerNames = new String[]{"Dart Tower","Wall","Tack Tower","Freeze Tower"};
public static final int[] towerPrices = new int[]{200,20,250,300};
public static PImage[] towerImages = new PImage[6];
public static final int[] balloonHealths = new int[]{1,2,3,4,5,150};
public static final int[] balloonSpeeds = new int[]{8,6,5,4,3,16};
public static final int[][] balloonCols = new int[][]{{255,0,0},{0,0,255},{0,255,0},{255,211,0},{255,0,90},{255,0,255}};
public static final int MOAB_START_INDEX=5;
public static final float[] balloonRadiiAsPercent = new float[]{0.7,0.7,0.7,0.7,0.7,1};
public static final int REGEN_TICKCOUNT =120;
public static PShape enemy;
public String currentSelection;
public int currentIndexOfSelection;
public ArrayList<Button> uiButtons=new ArrayList<Button>();
private float uiWidth = width-height;
int indexOfTower(String towerName){
  for(int i = 0; i < towerNames.length;i++){
    if(towerNames[i].equals(towerName)){
      return i;
    }
  }
  return -1;
}
void setup(){
  fullScreen(P2D);
  towerImages[0]=new PImage();
  towerImages[0]=loadImage("dart.png");
  towerImages[1]=new PImage();
  towerImages[1]=loadImage("wall.png");
  towerImages[2]=new PImage();
  towerImages[2]=loadImage("freeze.png");
  towerImages[3]=new PImage();
  towerImages[3]=loadImage("tack.png");
  towerImages[4]=new PImage();
  towerImages[4]=loadImage("play.png");
  towerImages[5]=new PImage();
  towerImages[5]=loadImage("pause.png");
  uiWidth = width-height;
  frameRate(60);
  HEIGHT=height>width?width:height;
  WIDTH=height>width?width:height;
  enemy = createShape(ELLIPSE,0,0,WIDTH/WORLD_WIDTH/2,HEIGHT/WORLD_HEIGHT/2);
  enemy.setStroke(false);
  world = new World();
  currentSelection = "Dart Tower";
  
  float halfway = height/2;
  uiButtons.add(new Button(WIDTH+(uiWidth/10),halfway-height/20,3.5*uiWidth/10,height/20,"Dart Tower",towerImages[0]));
  uiButtons.add(new Button(WIDTH+(5.5*uiWidth/10),halfway-height/20,3.5*uiWidth/10,height/20,"Freeze Tower",towerImages[2]));
  uiButtons.add(new Button(WIDTH+(uiWidth/10),halfway+height/40,3.5*uiWidth/10,height/20,"Tack Tower",towerImages[3]));
  uiButtons.add(new Button(WIDTH+(5.5*uiWidth/10),halfway+height/40,3.5*uiWidth/10,height/20,"Wall",towerImages[1]));
  uiButtons.add(new Button(WIDTH+(uiWidth/10),halfway+height/10,3.5*uiWidth/10,height/20,"FF",loadImage("fast.png")));
  uiButtons.add(new Button(WIDTH+(5.5*uiWidth/10),halfway+height/10,3.5*uiWidth/10,height/20,"PP",towerImages[4]));
}
void draw(){
  if(!world.isDead){
    world.tick();
    if(world.fast){
      world.tick();
    }
  }
  currentIndexOfSelection = indexOfTower(currentSelection); 
  if(height > width){
    fill(0);
    rect(0,width,height-width,width);
  }else{
    fill(0);
    rect(height,0,width-height,height);
    fill(255);
    textSize(uiWidth/16);
    text("Tower Info",WIDTH+(uiWidth/2)-(textWidth("Tower Info")/2),height/20);
    stroke(255,255,255);
    line(WIDTH+(uiWidth/10),height/15,width-(uiWidth/10),height/15);
    line(WIDTH+(uiWidth/10),height/15,WIDTH+(uiWidth/10),height/2.5);
    line(width-(uiWidth/10),height/15,width-(uiWidth/10),height/2.5);
    line(WIDTH+(uiWidth/10),height/2.5,width-(uiWidth/10),height/2.5);
    text("Buy Price: $"+towerPrices[currentIndexOfSelection],WIDTH+(uiWidth/2)-(textWidth("Buy Price: $"+towerPrices[currentIndexOfSelection])/2),height/5);
    textSize(uiWidth/18);
    text("Sell Price: $"+int(towerPrices[currentIndexOfSelection]*SELL_RATIO),WIDTH+(uiWidth/2)-(textWidth("Sell Price: $"+towerPrices[currentIndexOfSelection])/2),height/4);
    textSize(uiWidth/20);
    text(currentSelection,WIDTH+(uiWidth/2)-(textWidth(currentSelection)/2),height/10);
    textSize(uiWidth/25);
    text("Wave Number: " + str(waveNum-1),WIDTH+(uiWidth/10),height-(3*height/20));
    text("Money: " + player.money,WIDTH+(uiWidth/10),height-(height/20));
    text("Lives: " + player.health,WIDTH+(uiWidth/10),height-(2*height/20));
    noStroke();
    for(Button b : uiButtons){
      if(b.mouseOver()){
        b.setRGB(255,0,0);
      }else{
        b.setRGB(255,255,255);
      }
      b.display();
    }
  }
}
void mouseClicked(){
  if(mouseX<WIDTH&&mouseY<HEIGHT){
    world.onMouseClick();
  }
  for(Button b : uiButtons){
    if(b.mouseOver()){
      if(!b.name.equalsIgnoreCase("FF")&&!b.name.equalsIgnoreCase("PP")){
        currentSelection=b.name;
      }else{
        if(b.name=="FF"){
           world.fast=!world.fast; 
        }else{
           if(b.icon==towerImages[4]){
             b.icon=towerImages[5];
             world.pause=false;
           }
        }
      }
    }
  }
}

void keyPressed() {
  if(key == 'm'){
    player.money+=1000;
  }
  if(key == 'l'){
    waveNum+=1;
  }
}
