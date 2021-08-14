abstract class Bullet{
  int damage;
  int damagePerEnemy;
  float speed;
  float x;
  float y;
  float dx;
  float dy;
  boolean repeatHits = false;
  ArrayList<Enemy> alreadyHit = new ArrayList<Enemy>();
  float radians;
  Tower parent;
  public Bullet(Tower parent, Enemy target){
    this.parent = parent;
    this.x=parent.getX()+0.5;
    this.y=parent.getY()+0.5;
    float ex = target.getX();
    float ey = target.getY();
    this.radians= atan2(ey-y,ex-x);
    this.dx = cos(radians);
    this.dy = sin(radians);
  }
  public float distFromHome(){
    return sqrt((x-parent.x)*(x-parent.x)+(y-parent.y)*(y-parent.y));
  }
  public void display(){
    x+=dx*speed;
    y+=dy*speed;
    damageCheck();
    if(damage<=0){
      world.bullets.remove(this);
      return;
    }
    if(x > WORLD_WIDTH || x < 0 || y > WORLD_HEIGHT || y < 0 || distFromHome()>7){
      world.bullets.remove(this);
      return;
    }
    int screenX = (int)(WIDTH/WORLD_WIDTH*x);
    int screenY = (int)(HEIGHT/WORLD_HEIGHT*y);
    pushMatrix();
    translate(screenX,screenY);
    rotate(radians);
    translate(-screenX,-screenY);
    fill(0);
    rect(screenX,screenY,(WIDTH/WORLD_WIDTH*0.2),(HEIGHT/WORLD_HEIGHT*0.2));
    popMatrix();
  }
  private void damageCheck(){
    float minSquareDist = Float.MAX_VALUE;
    Enemy closest=null;
    for(Enemy e : world.enemies){
      if(repeatHits || !alreadyHit.contains(e)){
        float squareDist=abs(pow((e.getX()-x),2)+pow((e.getY()-y),2));
        if(squareDist<minSquareDist){
           closest=e;
           minSquareDist=squareDist;
        }
      }
    }
    if(closest!=null&&minSquareDist<=pow(closest.getRadiusAsPercent(),2)){
       int toDamage = damagePerEnemy<damage?damagePerEnemy:damage;
       alreadyHit.add(closest);
       closest.damage(toDamage); 
       damage-=toDamage;
    }
  }
  
}
