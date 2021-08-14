abstract class Enemy{
  
  protected int health;
  private float x;
  private float y;
  private float speed;
  private float r,g,b;
  private int currentDirection;
  private float distanceMoved;
  private boolean isMoving = false;
  private float radiiAsPercent = 1;
  private ArrayList<Effect> effects = new ArrayList<Effect>();
  
  public Enemy(float x, float y, int health){
    this.x=x;
    this.y=y;
    this.health=health;
    this.distanceMoved=0;
    r=g=b=random(255);
  }
  
  public float getX(){return x;}
  public void setX(float x){this.x=x;}
  public float getY(){return y;}
  public void setY(float y){this.y=y;}
  public float getSpeed(){return speed;}
  public void setSpeed(float speed){this.speed=speed;}
  public int getHealth(){return health;}
  public void setHealth(int health){this.health=health;}
  public void setRGB(int r, int g, int b){this.r = r; this.g=g;this.b=b;}
  public void setRGB(int[] rgb){this.r = rgb[0]; this.g=rgb[1];this.b=rgb[2];}
  public void addEffect(Effect e){
   this.effects.add(e); 
  }
  public void setRadiusAsPercent(float radiusPer){this.radiiAsPercent=radiusPer;}
  public float getRadiusAsPercent(){return this.radiiAsPercent;}
  public boolean isValidCoord(int x, int y){
    if(0 <= x && x < BTD.WORLD_WIDTH && 0 <= y && y < BTD.WORLD_HEIGHT && pathFindingMap[y][x]>-1){
      return true;
    }
    return false;
  }
  public boolean hasEffect(String name){
    for(Effect e : effects){
      if(name==e.getName())
        return true;
    }
    return false;
  }
  public void damage(int damageAmount){
    this.health-=damageAmount; 
     if(health<=0){
       world.enemies.remove(this);
     }
  }
  public boolean isDead(){
    return getHealth()<=0;
  }
  public void move(int direction){
    //0 is up, 1 is right, 2 is down, 3 is left
    if(!isMoving){
      currentDirection=direction;
    }
    isMoving=true;
    float curSpeed = speed;
    for(Effect e : effects){
      curSpeed*=e.getMultiplier();
    }
    if(curSpeed!=0){
      distanceMoved+=1/(4*curSpeed);
      switch(currentDirection){
        case 0: //north
          y-=1/(4*curSpeed);
          break;
        case 1: //east
          x+=1/(4*curSpeed);
          break;
        case 2: //south
          y+=1/(4*curSpeed);
          break;
        case 3: //west
          x-=1/(4*curSpeed);
          break;
        default:
          break;
      } 
    }
    if(distanceMoved>=1){
      isMoving=false;
      distanceMoved=0;
    }
    if((round(x)==WORLD_WIDTH-1 || round(x)==WORLD_WIDTH-2)&&(round(y)==WORLD_HEIGHT-1 || round(y)==WORLD_HEIGHT-2)){
      player.health-=health;
      world.enemies.remove(this);
    }
  }
  
  public int getDirection(){
    int x_inted = round(x);
    int y_inted = round(y);
    if(x_inted==0 && y_inted==0){
      if(pathFindingMap[1][0]==pathFindingMap[0][1]){
        return (int) random(2)+1;  
      }else if(pathFindingMap[1][0]<pathFindingMap[0][1]){
        return 2;
      }else{
        return 1;
      }
    }
    if(y_inted == 1 && x_inted ==0 && pathFindingMap[y_inted+1][x_inted]<0){
      return 1;
    }
    ArrayList<Integer> possibleDirecs = new ArrayList<Integer>();
    if(y_inted == 0 && x_inted ==1 && pathFindingMap[y_inted][x_inted+1]<0){
      return 2;
    }
      int[] allowedMoves = new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};
    if(x_inted+y_inted ==1){
      if(isValidCoord(x_inted+1,y_inted)){
        allowedMoves[1]=pathFindingMap[y_inted][x_inted+1];
      }
      if(isValidCoord(x_inted-1,y_inted)){
        allowedMoves[3]=pathFindingMap[y_inted][x_inted-1];
      }
      if(isValidCoord(x_inted,y_inted+1)){
        allowedMoves[2]=pathFindingMap[y_inted+1][x_inted];
      }
      if(isValidCoord(x_inted,y_inted-1)){
        allowedMoves[0]=pathFindingMap[y_inted-1][x_inted];
      }
    }else{
      if(isValidCoord(x_inted+1,y_inted)){
        allowedMoves[1]=pathFindingMap[y_inted][x_inted+1];
      }
      if(isValidCoord(x_inted-1,y_inted)){
        allowedMoves[3]=pathFindingMap[y_inted][x_inted-1];
      }
      if(isValidCoord(x_inted,y_inted+1)){
        allowedMoves[2]=pathFindingMap[y_inted+1][x_inted];
      }
      if(isValidCoord(x_inted,y_inted-1)){
        allowedMoves[0]=pathFindingMap[y_inted-1][x_inted];
      }
    }
    int min = min(allowedMoves);
    for(int i = 0; i < 4;i++){
      if(allowedMoves[i]==min){
        possibleDirecs.add(i);
      }
    }
    return possibleDirecs.get((int)(random(possibleDirecs.size())));
  }
  public void display(){
    ellipseMode(RADIUS);
    fill(r,g,b);
    for(int i = 0; i < effects.size(); i++){
       Effect e = effects.get(i);
       e.reduceDuration(1/frameRate);
       if(e.isExpired()){
         effects.remove(e);
         i--;
       }
       if(e.name=="regen"){
         e.reduceTicksLeft(1/frameRate);
         if(e.getTicksLeft()<=0){
           //println("gonna regen");
           damage(e.getDamagePerEffect());
           
           e.setTicksLeft(e.getTicksTillNextEffect());
         }
       }
    }
    move(getDirection());
    enemy.setFill(color(r,g,b));
    shape(enemy,WIDTH/WORLD_WIDTH*(x+0.5),HEIGHT/WORLD_HEIGHT*(y+0.5));
    //ellipse(WIDTH/WORLD_WIDTH*(x+0.5),HEIGHT/WORLD_HEIGHT*(y+0.5),radiiAsPercent*WIDTH/WORLD_WIDTH/2,radiiAsPercent*HEIGHT/WORLD_HEIGHT/2);
  }
}
