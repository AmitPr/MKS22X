class Tack extends Bullet{
  int tackNum;
  public Tack (Tower parent, Enemy target,int tacknum){
    super(parent,target);
    this.damage=1;
    this.damagePerEnemy=1;
    speed=0.3;
    this.dx=setDx(tacknum);
    this.dy=setDy(tacknum);
  }
  private float setDx(int tackNum){
    if(tackNum%4==0){
      return 0.0;
    }
    if(tackNum==1 || tackNum==3){
      return 0.707;
    }
    if(tackNum==5 || tackNum==7){
      return -0.707;
    }
    if(tackNum==2){
      return 1;
    }
    return -1;
  }
  private float setDy(int tackNum){
    if(tackNum%4==2){
      return 0.0;
    }
    if(tackNum==1 || tackNum==7){
      return 0.707;
    }
    if(tackNum==5 || tackNum==3){
      return -0.707;
    }
    if(tackNum==0){
      return 1;
    }
    return -1;
  }
  
  public void display(){
    super.display();
    if(distFromHome()>2){
      world.bullets.remove(this);
    }
  }
}
