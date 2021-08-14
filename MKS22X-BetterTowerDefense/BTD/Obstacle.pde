abstract class Obstacle{
  int health;
  int maxHealth;
  int x;
  int y;
  int price;
  public Obstacle(int x, int y, int maxHealth){
    this.x=x;
    this.y=y;
    this.maxHealth=maxHealth;
    this.price=0;
    this.health=this.maxHealth;
  }
  public void display(){
  }
  public int getX(){return x;}
  public void setX(int x){this.x=x;}
  public int getY(){return y;}
  public void setY(int y){this.y=y;}
  public float getHealth(){return health;}
  public void setHealth(int health){this.health=health;}
  public float getMaxHealth(){return maxHealth;}
  public void setMaxHealth(int maxHealth){this.maxHealth=maxHealth;}
  public void damage(float damageAmount){
   this.health-=damageAmount; 
  }
}
